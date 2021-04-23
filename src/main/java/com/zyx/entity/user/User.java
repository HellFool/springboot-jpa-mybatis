package com.zyx.entity.user;

import com.zyx.entity.AbstractPojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Entity;


/**
 * 用户表
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@ApiModel(value = "用户表")
public class User extends AbstractPojo {

    @ApiModelProperty("用户唯一标识")
    private String userId;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("盐")
    private String salt;
}
