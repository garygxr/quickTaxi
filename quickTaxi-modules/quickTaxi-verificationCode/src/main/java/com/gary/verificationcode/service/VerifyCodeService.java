package com.gary.verificationcode.service;

public interface VerifyCodeService {
    Boolean generate(int identity, String phoneNumber);
}
