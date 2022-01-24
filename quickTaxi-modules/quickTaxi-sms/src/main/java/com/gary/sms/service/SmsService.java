package com.gary.sms.service;

public interface SmsService {
    Boolean sendMessage(int identity, String phoneNumber, String code);
}
