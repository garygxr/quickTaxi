package com.gary.common.security.util;

import com.gary.common.core.exception.auth.NotLoginException;
import com.gary.common.core.util.ServletUtil;
import com.gary.common.security.annotion.RequiresPermissions;
import com.gary.common.security.annotion.RequiresRoles;
import com.gary.common.security.auth.AuthLogic;
import com.gary.common.security.constant.SecurityConstants;

/**
 * @Classname AuthUtil
 * @Description 鉴权工具
 * @Date 2022/1/25 17:03
 * @Auth gary
 */
public class AuthUtil {
    /**
     * 底层的 AuthLogic 对象
     */
    public static AuthLogic authLogic = new AuthLogic();

    public static void checkLogin() {
        String username = ServletUtil.getHeader(ServletUtil.getRequest(),SecurityConstants.DETAILS_USERNAME);
        if (username == null)
        {
            throw new NotLoginException("未登录");
        }
    }

    public static void checkRole(RequiresRoles requiresRoles) {
        authLogic.checkRole(requiresRoles);
    }

    public static void checkPermi(RequiresPermissions requiresPermissions) {
        authLogic.checkPermi(requiresPermissions);
    }
}
