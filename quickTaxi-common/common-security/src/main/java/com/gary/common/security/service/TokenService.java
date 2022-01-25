package com.gary.common.security.service;

import com.gary.common.core.util.IpUtil;
import com.gary.common.core.util.ServletUtil;
import com.gary.common.redis.constant.RedisKey;
import com.gary.common.redis.service.RedisService;
import com.gary.common.security.constant.SecurityConstants;
import com.gary.common.security.model.AuthUser;
import com.gary.common.security.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class TokenService {
    private static final long TOKENTTL = 24 * 60 * 60 * 1000;
    private static final long REDISTTL = 30 * 60 * 60 * 1000;

    @Autowired
    RedisService redisService;


    /**
     * 创建令牌
     */
    public Map<String, Object> createToken(AuthUser authUser) {
        String userId = authUser.getUid();
        String userName = authUser.getUsername();
        authUser.setIpaddr(IpUtil.getIpAddr(ServletUtil.getRequest()));

        // Jwt存储信息
        Map<String, Object> claimsMap = new HashMap<String, Object>();
        claimsMap.put(SecurityConstants.IP_ADDR, authUser.getIpaddr());
        claimsMap.put(SecurityConstants.DETAILS_USER_ID, userId);
        claimsMap.put(SecurityConstants.DETAILS_USERNAME, userName);
        claimsMap.put(SecurityConstants.EXPIRE_TIME, System.currentTimeMillis() + TOKENTTL);
        claimsMap.put(SecurityConstants.ROLES, authUser.getRoles());

        //创建Token
        // 接口返回信息
        Map<String, Object> rspMap = new HashMap<String, Object>();

        redisService.setCacheObject(RedisKey.getAuthUserKey(userId), authUser, REDISTTL, TimeUnit.MILLISECONDS);

        rspMap.put("access_token", JwtUtil.createToken(claimsMap));

        return rspMap;
    }

    public void delLoginUser(String token) {
    }


    /**
     * 验证令牌 并刷新redis缓存时间
     *
     * @param loginUser
     */
    public void verifyToken(AuthUser loginUser) {
        //todo
    }

    public AuthUser getAuthUserByUid(String uid) {
        return redisService.getCacheObject(RedisKey.getAuthUserKey(uid));
    }
}
