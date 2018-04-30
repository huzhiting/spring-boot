package com.uqiauto.cloud.controller;

import com.uqiauto.cloud.entity.User;
import com.uqiauto.cloud.repository.UserRepository;
import com.uqiauto.cloud.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/uplus")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id){
        return this.userService.findById(id);
    }

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 操作结果
     * @throws AuthenticationException 错误信息
     */
    @PostMapping(value = "/user/login", params = {"username", "password"})
    public String getToken(String username, String password) throws Exception {
        return userService.login(username, password);
    }

    /**
     * 用户注册
     *
     * @param user          用户信息
     * @return 操作结果
     * @throws AuthenticationException 错误信息
     */
    @PostMapping(value = "/user/register")
    public String register(User user) throws AuthenticationException {
        return userService.register(user);
    }

    /**
     * 刷新密钥
     *
     * @param authorization 原密钥
     * @return 新密钥
     * @throws AuthenticationException 错误信息
     */
    @GetMapping(value = "/user/refreshToken")
    public String refreshToken(@RequestHeader String authorization) throws AuthenticationException {
        return userService.refreshToken(authorization);
    }

}
