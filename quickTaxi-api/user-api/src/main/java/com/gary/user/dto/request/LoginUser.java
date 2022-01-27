package com.gary.user.dto.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel
public class LoginUser {
    /**
     * 账号
     */
    @ApiModelProperty(value = "用户名",required = true,example = "gan")
    @NotEmpty(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码",required = true,example = "123")
    @NotEmpty(message = "密码不能为空")
    private String password;


}
