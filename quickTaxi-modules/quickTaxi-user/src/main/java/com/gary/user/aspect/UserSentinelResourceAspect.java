package com.gary.user.aspect;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.alibaba.csp.sentinel.util.StringUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

/**
 * @Classname SentinelResourceAspect
 * @Description 统一异常降级处理
 * @Date 2022/1/27 14:44
 * @Auth gary
 */
@Component
public class UserSentinelResourceAspect extends SentinelResourceAspect {

    @Override
    protected Object handleDefaultFallback(ProceedingJoinPoint pjp, String defaultFallback, Class<?>[] fallbackClass, Throwable ex) throws Throwable {
        if (StringUtil.isBlank(defaultFallback)){
            // 默认处理逻辑
            defaultFallback = "当前系统异常，稍后再试....";
        }
        return super.handleDefaultFallback(pjp,defaultFallback,fallbackClass,ex);
    }


}
