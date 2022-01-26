package com.gary.user.service.impl;

import com.gary.common.security.model.AuthUser;
import com.gary.user.service.UserService;
import com.gary.common.security.util.SecurityUtil;
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
        authUser.setUid("asd3xd");
        authUser.setUsername(usernaem);
        authUser.setPassword(SecurityUtil.encryptPassword("123"));

        Set<String> roles = new HashSet<>();
        roles.add("admin");
        roles.add("user");
        authUser.setRoles(roles);

        HashSet<String> permissions = new HashSet<>();
        permissions.add("user:list");
        permissions.add("user:select");
        authUser.setPermissions(permissions);

        return authUser;
    }
}
