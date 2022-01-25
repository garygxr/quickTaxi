package com.gary.user.api;

import com.gary.common.core.dto.AjaxResult;
import com.gary.user.dto.request.LoginUser;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
public interface UserApi {
    @GetMapping("/roleAnd")
    AjaxResult roleAnd();

    @GetMapping("/roleOr")
    AjaxResult roleOr();

    @GetMapping("/permAnd")
    AjaxResult permAnd();

    @GetMapping("/permOr")
    AjaxResult permOr();

    @PostMapping("/login")
    AjaxResult login(@RequestBody LoginUser loginUser);
}
