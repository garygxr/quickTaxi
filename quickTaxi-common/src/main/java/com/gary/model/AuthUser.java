package com.gary.model;

import lombok.Data;

import java.util.Set;

@Data
public class AuthUser {
    /**
     * 用户 id
     */
    private String uid;

    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 权限列表
     */
    private Set<String> permissions;

    /**
     * 角色列表
     */
    private Set<String> roles;
}
