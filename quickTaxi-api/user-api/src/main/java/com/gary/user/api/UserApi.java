package com.gary.user.api;

import com.gary.common.core.dto.AjaxResult;
import com.gary.user.dto.request.LoginUser;
import com.gary.user.dto.response.LoginUserVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
    AjaxResult<LoginUserVo> login(@RequestBody LoginUser loginUser);
}
