package com.gary.sms.service.impl;

import com.gary.sms.service.SmsService;
import org.springframework.stereotype.Service;


@Service
public class SmsServiceImpl implements SmsService {

    @Override
    public Boolean sendMessage(int identity, String phoneNumber, String code) {
        System.out.println(String.format("%d,手机号: %s,验证码：%s",identity,phoneNumber,code));
        return true;
    }
}
