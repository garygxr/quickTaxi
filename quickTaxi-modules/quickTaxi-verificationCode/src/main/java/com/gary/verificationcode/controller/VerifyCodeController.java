package com.gary.verificationcode.controller;


import com.gary.dto.AjaxResult;
import com.gary.verificationcode.api.VerificationCodeApi;
import com.gary.verificationcode.service.VerifyCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerifyCodeController implements VerificationCodeApi {
    @Autowired
    VerifyCodeService verifyCodeService;

    @Override
    public AjaxResult generate(int identity, String phoneNumber) {
        Boolean flag = verifyCodeService.generate(identity,phoneNumber);
        return AjaxResult.success();
    }
}
