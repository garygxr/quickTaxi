package com.gary.user.service.impl;

import com.gary.model.AuthUser;
import com.gary.user.service.UserService;
import com.gary.util.SecurityUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {


    @Override
    public AuthUser loadUserByUserName(String usernaem) {
        /**
         * 省略查数据库
         */
        AuthUser authUser = new AuthUser();
        authUser.setUsername(usernaem);
        authUser.setPassword(SecurityUtil.encryptPassword("123"));

        Set<String> roles = new HashSet<>();
        roles.add("admin");
        authUser.setRoles(roles);

        HashSet<String> permissions = new HashSet<>();
        authUser.setPermissions(permissions);

        return authUser;
    }
}
