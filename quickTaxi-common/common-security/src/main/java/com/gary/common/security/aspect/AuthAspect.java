package com.gary.common.security.aspect;

import com.gary.common.security.annotion.RequiresLogin;
import com.gary.common.security.annotion.RequiresPermissions;
import com.gary.common.security.annotion.RequiresRoles;
import com.gary.common.security.util.AuthUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Classname AuthAspect
 * @Description TODO
 * @Date 2022/1/25 16:41
 * @Auth gary
 */
@Aspect
@Component
public class AuthAspect {
    /**
     * 定义AOP签名
     */
    public static final String POINTCUT_SIGN = " @annotation(com.ruoyi.common.security.annotation.RequiresLogin) || "
            + "@annotation(com.ruoyi.common.security.annotation.RequiresPermissions) || "
            + "@annotation(com.ruoyi.common.security.annotation.RequiresRoles)";

    @Pointcut(value = POINTCUT_SIGN)
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 注解鉴权
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        checkMethodAnnotation(signature.getMethod());
        try {
            // 执行原有逻辑
            Object obj = joinPoint.proceed();
            return obj;
        } catch (Throwable e) {
            throw e;
        }
    }

    /**
     * 对一个Method对象进行注解检查
     *
     * @return
     */
    public void checkMethodAnnotation(Method method) {
        // 校验 @RequiresLogin 注解
        RequiresLogin requiresLogin = method.getAnnotation(RequiresLogin.class);
        if (requiresLogin != null) {
            AuthUtil.checkLogin();
        }

        // 校验 @RequiresRoles 注解
        RequiresRoles requiresRoles = method.getAnnotation(RequiresRoles.class);
        if (requiresRoles != null) {
            AuthUtil.checkRole(requiresRoles);
        }

        // 校验 @RequiresPermissions 注解
        RequiresPermissions requiresPermissions = method.getAnnotation(RequiresPermissions.class);
        if (requiresPermissions != null) {
            AuthUtil.checkPermi(requiresPermissions);
        }

    }


}
