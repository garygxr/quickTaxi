package com.gary.util;

import com.gary.constant.SecurityConstants;
import com.gary.model.AuthUser;

import java.util.HashMap;
import java.util.Map;

public class TokenUtil {
    private static final long TOKENTTL = 24*60*60*1000;

    /**
     * 创建令牌
     */
    public static Map<String, Object> createToken(AuthUser authUser)
    {
        String userId = authUser.getUid();
        String userName = authUser.getUsername();
        authUser.setIpaddr(IpUtil.getIpAddr(ServletUtil.getRequest()));

        // Jwt存储信息
        Map<String, Object> claimsMap = new HashMap<String, Object>();
        claimsMap.put(SecurityConstants.IP_ADDR, authUser.getIpaddr());
        claimsMap.put(SecurityConstants.DETAILS_USER_ID, userId);
        claimsMap.put(SecurityConstants.DETAILS_USERNAME, userName);
        claimsMap.put(SecurityConstants.EXPIRE_TIME, System.currentTimeMillis()+TOKENTTL);
        claimsMap.put(SecurityConstants.PERMISSIONS, authUser.getPermissions());
        claimsMap.put(SecurityConstants.ROLES, authUser.getRoles());

        //创建Token
        // 接口返回信息
        Map<String, Object> rspMap = new HashMap<String, Object>();
        rspMap.put("access_token", JwtUtil.createToken(claimsMap));

        return rspMap;
    }
}
