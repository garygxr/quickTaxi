package com.gary.sms.controller;


import com.gary.dto.AjaxResult;
import com.gary.sms.api.SmsApi;
import com.gary.sms.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmsController implements SmsApi {
    @Autowired
    SmsService smsService;


    @Override
    public AjaxResult sendMessage(int identity, String phoneNumber, String code) {
        smsService.sendMessage(identity,phoneNumber,code);
        return AjaxResult.success();
    }
}
