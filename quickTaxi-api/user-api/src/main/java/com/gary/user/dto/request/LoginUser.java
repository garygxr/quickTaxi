package com.gary.user.dto.request;


import lombok.Data;

@Data
public class LoginUser {
    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;


}
