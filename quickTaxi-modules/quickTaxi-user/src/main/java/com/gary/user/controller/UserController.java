package com.gary.user.controller;


import com.gary.constant.HttpStatus;
import com.gary.dto.AjaxResult;
import com.gary.model.AuthUser;
import com.gary.user.api.UserApi;

import com.gary.user.dto.request.LoginUser;
import com.gary.user.service.UserService;
import com.gary.util.SecurityUtil;
import com.gary.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController implements UserApi {
    @Autowired
    UserService userService;

    @Override
    public AjaxResult getUserById(String id) {
        return AjaxResult.success("用户");
    }

    @Override
    public AjaxResult login(LoginUser loginUser) {
        //从数据库查询user
        AuthUser authUser = userService.loadUserByUserName(loginUser.getUsername());
        String orignPassword = authUser.getPassword();
        String password = loginUser.getPassword();
        boolean pass = SecurityUtil.matchesPassword(password, orignPassword);
        if (pass){
            Map<String, Object> token = TokenUtil.createToken(authUser);
            return AjaxResult.success("登陆成功",token);
        }else {
            return AjaxResult.error(HttpStatus.UNAUTHORIZED,"登陆失败");
        }
    }
}
