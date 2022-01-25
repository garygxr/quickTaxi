package com.gary.common.security.util;

import com.gary.common.core.util.ServletUtil;
import com.gary.common.security.auth.AuthLogic;
import com.gary.common.security.constant.SecurityConstants;
import com.gary.common.security.constant.TokenConstants;
import com.gary.common.security.context.SecurityContextHolder;
import com.gary.common.security.model.AuthUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname SecurityUtil
 * @Description 安全认证工具
 * @Date 2022/1/25 17:03
 * @Auth gary
 */
public class SecurityUtil {
    /**
     * 底层的 AuthLogic 对象
     */
    public static AuthLogic authLogic = new AuthLogic();

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword 真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    /**
     * 获取token
     * @return
     */
    public static String getToken() {
        return getToken(ServletUtil.getRequest());
    }

    public static String replaceTokenPrefix(String token)
    {
        if (StringUtils.isEmpty(token) && token.startsWith(TokenConstants.PREFIX)){
            token = token.replaceFirst(TokenConstants.PREFIX,"").trim();
        }
        return token;
    }

    /**
     * 根据request获取请求token
     */
    public static String getToken(HttpServletRequest request)
    {
        // 从header获取token标识
        String token = request.getHeader(TokenConstants.AUTHENTICATION);
        return replaceTokenPrefix(token);
    }

    /**
     * 获取登录用户信息
     */
    public static AuthUser getLoginUser(String uid)
    {
        return authLogic.getLoginUser(uid);
    }

    /**
     * 获取登录用户信息
     */
    public static AuthUser getLoginUser()
    {
        return SecurityContextHolder.get(SecurityConstants.LOGIN_USER, AuthUser.class);
    }
}
