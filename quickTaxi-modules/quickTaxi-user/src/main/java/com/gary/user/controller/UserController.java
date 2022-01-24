package com.gary.user.controller;

import com.gary.dto.AjaxResult;
import com.gary.user.api.UserApi;

public class UserController implements UserApi {

    @Override
    public AjaxResult getUserById(String id) {
        return AjaxResult.success("用户");
    }
}
