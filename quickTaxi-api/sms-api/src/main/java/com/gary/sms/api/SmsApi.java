package com.gary.sms.api;

import com.gary.common.core.dto.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/sms")
public interface SmsApi {

    @GetMapping("/sendCode/{identity}/{phoneNumber}/{code}")
    AjaxResult sendMessage(@PathVariable("identity")int identity,@PathVariable("phoneNumber")String phoneNumber,@PathVariable("code") String code);
}
