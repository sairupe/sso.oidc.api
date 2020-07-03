/*
 * Copyright(c) 2018 优居科技 All rights reserved.
 * distributed with this file and available online at
 * http://www.yjyz.com/
 */
package com.syriana.sso.oidc.api.dto;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

/**
* 接入客户信息
 *
 * @author zh
 * @version v1.0
 * @CreationTime: - 2020-07-03 15:34:57
 * @Description:
 */
@Data
public class ClientDetailsDto implements Serializable {

    /** 主键ID */
    
    @ApiModelProperty(value = "主键ID", notes = "")
    private Long clientDetailsId;

    /** 客户端标识 */
    @NotBlank @Length(max=255)
    @ApiModelProperty(value = "客户端标识", notes = "@NotBlank @Length(max=255)")
    private String clientId;

    /** 接入资源列表 */
    @Length(max=255)
    @ApiModelProperty(value = "接入资源列表", notes = "@Length(max=255)")
    private String resourceIds;

    /** 客户端秘钥 */
    @Length(max=255)
    @ApiModelProperty(value = "客户端秘钥", notes = "@Length(max=255)")
    private String clientSecret;

    /** scope */
    @Length(max=255)
    @ApiModelProperty(value = "scope", notes = "@Length(max=255)")
    private String scope;

    /** authorizedGrantTypes */
    @Length(max=255)
    @ApiModelProperty(value = "authorizedGrantTypes", notes = "@Length(max=255)")
    private String authorizedGrantTypes;

    /** webServerRedirectUri */
    @Length(max=255)
    @ApiModelProperty(value = "webServerRedirectUri", notes = "@Length(max=255)")
    private String webServerRedirectUri;

    /** authorities */
    @Length(max=255)
    @ApiModelProperty(value = "authorities", notes = "@Length(max=255)")
    private String authorities;

    /** accessTokenValidity */
    
    @ApiModelProperty(value = "accessTokenValidity", notes = "")
    private Integer accessTokenValidity;

    /** refreshTokenValidity */
    
    @ApiModelProperty(value = "refreshTokenValidity", notes = "")
    private Integer refreshTokenValidity;

    /** additionalInformation */
    @Length(max=2147483647)
    @ApiModelProperty(value = "additionalInformation", notes = "@Length(max=2147483647)")
    private String additionalInformation;

    /** archived */
    @Max(127)
    @ApiModelProperty(value = "archived", notes = "@Max(127)")
    private Integer archived;

    /** trusted */
    @Max(127)
    @ApiModelProperty(value = "trusted", notes = "@Max(127)")
    private Integer trusted;

    /** autoapprove */
    @Length(max=255)
    @ApiModelProperty(value = "autoapprove", notes = "@Length(max=255)")
    private String autoapprove;

    /** 创建人ID */
    
    @ApiModelProperty(value = "创建人ID", notes = "")
    private Long createdBy;

    /** 创建时间 */
    
    @ApiModelProperty(value = "创建时间", notes = "")
    private Long createdTm;

    /** 修改或删除人ID */
    
    @ApiModelProperty(value = "修改或删除人ID", notes = "")
    private Long updatedBy;

    /** 修改或删除时间 */
    
    @ApiModelProperty(value = "修改或删除时间", notes = "")
    private Long updatedTm;

    /** 逻辑删除is_flag tinyint,1:正常|0:删除，默认值为1 */
    @Max(127)
    @ApiModelProperty(value = "逻辑删除is_flag tinyint,1:正常|0:删除，默认值为1", notes = "@Max(127)")
    private Integer isFlag;
}
