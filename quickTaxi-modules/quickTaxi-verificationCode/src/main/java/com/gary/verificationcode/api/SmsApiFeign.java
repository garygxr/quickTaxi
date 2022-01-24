package com.gary.verificationcode.api;

import com.gary.sms.api.SmsApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@FeignClient(value = "quickTaxi-sms")
@Component
public interface SmsApiFeign extends SmsApi {
}
