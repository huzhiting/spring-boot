package com.uqiauto.cloud.service;

import com.uqiauto.cloud.entity.User;

public interface IUserService {

    /**
     * 根据用户id查询用户
     * @param id
     * @return
     */
    public User findById(Long id);

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 操作结果
     */
    String login(String username, String password) throws Exception;

    /**
     * 用户注册
     *
     * @param user 用户信息
     * @return 操作结果
     */
    String register(User user);

    /**
     * 刷新密钥
     *
     * @param oldToken 原密钥
     * @return 新密钥
     */
    String refreshToken(String oldToken);

}
