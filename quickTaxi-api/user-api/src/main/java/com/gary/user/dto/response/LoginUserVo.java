package com.gary.user.dto.response;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "token 将该token放入请求头的Authorization")
    private String access_token;
}
