package com.gary.user.api;

import com.gary.dto.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
public interface UserApi {
    @GetMapping("/{id}")
    AjaxResult getUserById(@PathVariable("id")String id);
}
