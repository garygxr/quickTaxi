package com.gary.user.dto.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class LoginUser {
    /**
     * 账号
     */
    @ApiModelProperty(value = "用户名",required = true)
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码",required = true)
    private String password;


}
