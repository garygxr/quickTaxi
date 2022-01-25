package com.gary.user.service;

import com.gary.common.security.model.AuthUser;

public interface UserService {
    /**
     * 更具username 查询user
     * @param usernaem
     * @return
     */
    AuthUser loadUserByUserName(String usernaem);

}
