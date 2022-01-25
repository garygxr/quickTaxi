package com.gary.user.domain;

import com.gary.common.security.util.SecurityUtil;
import lombok.Data;


@Data
public class User {
    public User() {
        this.uid = "1sadfn2";
        this.useranme = "haha";
        this.password = SecurityUtil.encryptPassword("123");
    }

    private String uid;

    private String useranme;

    private String password;
}
