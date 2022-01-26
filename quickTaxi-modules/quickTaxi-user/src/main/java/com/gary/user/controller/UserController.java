package com.gary.user.controller;


import com.gary.common.core.constant.HttpStatus;
import com.gary.common.core.dto.AjaxResult;
import com.gary.common.security.annotation.Logical;
import com.gary.common.security.annotation.RequiresPermissions;
import com.gary.common.security.annotation.RequiresRoles;
import com.gary.common.security.model.AuthUser;
import com.gary.common.security.service.TokenService;
import com.gary.common.security.util.SecurityUtil;
import com.gary.user.api.UserApi;
import com.gary.user.dto.request.LoginUser;
import com.gary.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController implements UserApi {
    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;


    @Override
    @RequiresRoles({"admin","user"})
    public AjaxResult roleAnd() {
        return AjaxResult.success("admin角色和user角色");
    }

    @Override
    @RequiresRoles(value = {"admin","user"},logical = Logical.OR)
    public AjaxResult roleOr() {
        return AjaxResult.success("admin角色或user角色");
    }

    @Override
    @RequiresPermissions(value = {"user:select","user:list"},logical = Logical.AND)
    public AjaxResult permAnd() {
        return AjaxResult.success("user:select 和 user:list 权限");
    }

    @Override
    @RequiresPermissions(value = {"user:select","user:list"},logical = Logical.OR)
    public AjaxResult permOr() {
        return AjaxResult.success("user:select 或 user:list 权限");
    }

    @Override
    public AjaxResult login(LoginUser loginUser) {
        //从数据库查询user
        AuthUser authUser = userService.loadUserByUserName(loginUser.getUsername());
        String orignPassword = authUser.getPassword();
        String password = loginUser.getPassword();
        boolean pass = SecurityUtil.matchesPassword(password, orignPassword);
        if (pass){
            Map<String, Object> token = tokenService.createToken(authUser);
            return AjaxResult.success("登陆成功",token);
        }else {
            return AjaxResult.error(HttpStatus.UNAUTHORIZED,"登陆失败");
        }
    }
}
