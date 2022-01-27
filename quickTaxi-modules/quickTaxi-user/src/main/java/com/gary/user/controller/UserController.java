package com.gary.user.controller;


import com.gary.common.core.constant.HttpStatus;
import com.gary.common.core.dto.AjaxResult;
import com.gary.common.security.annotation.Logical;
import com.gary.common.security.annotation.RequiresPermissions;
import com.gary.common.security.annotation.RequiresRoles;
import com.gary.common.security.model.AuthUser;
import com.gary.common.security.service.TokenService;
import com.gary.user.api.UserApi;
import com.gary.user.dto.request.LoginUser;
import com.gary.user.dto.response.LoginUserVo;
import com.gary.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserApi {
    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;


    @Override
    @RequiresRoles({"admin", "user"})
    public AjaxResult roleAnd() {
        return AjaxResult.success("admin角色和user角色");
    }

    @Override
    @RequiresRoles(value = {"admin", "user"}, logical = Logical.OR)
    public AjaxResult roleOr() {
        return AjaxResult.success("admin角色或user角色");
    }

    @Override
    @RequiresPermissions(value = {"user:select", "user:list"}, logical = Logical.AND)
    public AjaxResult permAnd() {
        return AjaxResult.success("user:select 和 user:list 权限");
    }

    @Override
    @RequiresPermissions(value = {"user:select", "user:list"}, logical = Logical.OR)
    public AjaxResult permOr() {
        return AjaxResult.success("user:select 或 user:list 权限");
    }


    @Override
    public AjaxResult<LoginUserVo> login(@Validated() LoginUser loginUser) {
        String password = loginUser.getPassword();
        if (password == null) {
            return AjaxResult.error("密码不能为空");
        }
        //从数据库查询user
        AuthUser authUser = userService.loadUserByUserName(loginUser.getUsername());
        String orignPassword = authUser.getPassword();
        Boolean pass = userService.checkUserPassword(password, orignPassword);
        if (pass) {
            String token = tokenService.createToken(authUser);
            LoginUserVo loginUserVo = new LoginUserVo(token);
            return AjaxResult.success("登陆成功", loginUserVo);
        } else {
            return AjaxResult.error(HttpStatus.UNAUTHORIZED, "登陆失败");
        }
    }
}
