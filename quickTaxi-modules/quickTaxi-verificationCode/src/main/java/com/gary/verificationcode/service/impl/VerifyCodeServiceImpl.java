package com.gary.verificationcode.service.impl;

import com.gary.verificationcode.api.SmsApiFeign;
import com.gary.verificationcode.service.VerifyCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VerifyCodeServiceImpl implements VerifyCodeService {
    @Autowired
    SmsApiFeign smsApiFeign;

    @Override
    public Boolean generate(int identity, String phoneNumber) {
        String code = String.valueOf((int) ((Math.random() * 9 + 1) * (Math.pow(10, 5))));
        System.out.println(code);
        smsApiFeign.sendMessage(identity,phoneNumber,code);
        return true;
    }
}
