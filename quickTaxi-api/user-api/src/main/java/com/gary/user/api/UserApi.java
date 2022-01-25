package com.gary.user.api;

import com.gary.dto.AjaxResult;
import com.gary.user.dto.request.LoginUser;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
public interface UserApi {
    @GetMapping("/{id}")
    AjaxResult getUserById(@PathVariable("id")String id);

    @PostMapping("/login")
    AjaxResult login(@RequestBody LoginUser loginUser);
}
