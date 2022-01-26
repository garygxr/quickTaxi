package com.gary.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname LoginUserVo
 * @Description TODO
 * @Date 2022/1/26 13:47
 * @Auth gary
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserVo {

    private String access_token;
}
