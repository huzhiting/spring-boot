package com.uqiauto.cloud.service.impl;

import com.uqiauto.cloud.entity.User;
import com.uqiauto.cloud.repository.UserRepository;
import com.uqiauto.cloud.service.IUserService;
import com.uqiauto.cloud.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private AuthenticationManager authenticationManager;
    private JwtUserDetailsServiceImpl jwtUserDetailsService;
    private JwtTokenUtil jwtTokenUtil;
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl( JwtUserDetailsServiceImpl jwtUserDetailsService, JwtTokenUtil jwtTokenUtil, UserRepository userRepository) {
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public String login(String username, String password)  throws Exception {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
        return jwtTokenUtil.generateToken(userDetails);
    }

    @Override
    public String register(User user) {
        String username = user.getUsername();
        if (userRepository.findByUsername(username) != null) {
            return "用户已存在";
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = user.getPassword();
        user.setPassword(encoder.encode(rawPassword));
        user.setDeleted(0);
        user.setState(0);
        userRepository.save(user);
        return "success";
    }

    @Override
    public String refreshToken(String oldToken) {
        String token = oldToken.substring("Bearer ".length());
        if (!jwtTokenUtil.isTokenExpired(token)) {
            return jwtTokenUtil.refreshToken(token);
        }
        return "error";
    }
}
