package com.uqiauto.cloud.service.impl;

import com.uqiauto.cloud.entity.User;
import com.uqiauto.cloud.repository.UserRepository;
import com.uqiauto.cloud.security.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =userRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("用户不存在");
        }else{
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            return new JwtUser(user.getUsername(), user.getPassword(), authorities);
        }
    }
}
