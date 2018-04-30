package com.uqiauto.cloud.controller;

import com.uqiauto.cloud.entity.User;
import com.uqiauto.cloud.repository.UserRepository;
import com.uqiauto.cloud.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/uplus")
public class TestController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/test/getInfoById", params = {"id"} )
    public User findById(Long id){
        User userInfo=this.userService.findById(id);
        return userInfo;
    }

    @PostMapping(value = "/test/findByUsername", params = {"username"} )
    public User findByUsername(String username){
        User userInfo=this.userRepository.findByUsername(username);
        return userInfo;
    }
}
