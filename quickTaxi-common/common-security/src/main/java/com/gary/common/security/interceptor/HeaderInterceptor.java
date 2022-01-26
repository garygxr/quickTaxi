package com.gary.common.security.interceptor;

import com.gary.common.core.util.ServletUtil;
import com.gary.common.core.constant.SecurityConstants;
import com.gary.common.security.context.SecurityContextHolder;
import com.gary.common.security.model.AuthUser;
import com.gary.common.security.util.SecurityUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Classname HeaderInterceptor
 * @Description TODO
 * @Date 2022/1/25 17:46
 * @Auth gary
 */

public class HeaderInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        SecurityContextHolder.setUserId(ServletUtil.getHeader(request, SecurityConstants.DETAILS_USER_ID));
        SecurityContextHolder.setUserName(ServletUtil.getHeader(request, SecurityConstants.DETAILS_USERNAME));

        String token = SecurityUtil.getToken();
        if (StringUtils.isNotEmpty(token)) {
            AuthUser loginUser = SecurityUtil.getLoginUser(ServletUtil.getHeader(request, SecurityConstants.DETAILS_USER_ID));
            if (loginUser!=null) {
                SecurityContextHolder.set(SecurityConstants.PERMISSIONS, loginUser.getPermissions());
                SecurityContextHolder.set(SecurityConstants.ROLES,loginUser.getRoles());
            }
        }
        return true;
    }
}
