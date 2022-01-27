package com.gary.user.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.gary.common.security.model.AuthUser;
import com.gary.user.service.UserService;
import com.gary.common.security.util.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {


    @Override
    @SentinelResource(value = "loadUserByUserName",blockHandler = "fallBack")
    public AuthUser loadUserByUserName(String usernaem) {
        /**
         * 省略查数据库
         */
        AuthUser authUser = new AuthUser();
        authUser.setUid("asd3xd");
        authUser.setUsername(usernaem);
        authUser.setPassword(SecurityUtil.encryptPassword("123"));

        Set<String> roles = new HashSet<>();
        roles.add("*");
        authUser.setRoles(roles);

        HashSet<String> permissions = new HashSet<>();
        permissions.add("*");
        authUser.setPermissions(permissions);

        return authUser;
    }


    @Override
    public Boolean checkUserPassword(String currntPassword,String orignPassword){
        return SecurityUtil.matchesPassword(currntPassword, orignPassword);
    }


    public AuthUser fallBack(String usernaem,BlockException exception){
        System.out.println(exception);
        return new AuthUser();
    }
}
