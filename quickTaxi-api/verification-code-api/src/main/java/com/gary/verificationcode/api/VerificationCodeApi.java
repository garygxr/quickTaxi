package com.gary.verificationcode.api;

import com.gary.dto.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/verify-code")
public interface VerificationCodeApi {

    @GetMapping("/generate/{identity}/{phoneNumber}")
    public AjaxResult generate(@PathVariable("identity")int identity,@PathVariable("phoneNumber")String phoneNumber);
}
