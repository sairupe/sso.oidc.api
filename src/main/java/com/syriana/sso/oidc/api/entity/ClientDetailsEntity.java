/*
 * Copyright(c) 2018 优居科技 All rights reserved.
 * distributed with this file and available online at
 * http://www.yjyz.com/
 */
package com.syriana.sso.oidc.api.entity;

import lombok.Data;

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
public class ClientDetailsEntity implements Serializable {

    /** 主键ID */
    private Long clientDetailsId;

    /** 客户端标识 */
    private String clientId;

    /** 接入资源列表 */
    private String resourceIds;

    /** 客户端秘钥 */
    private String clientSecret;

    /** scope */
    private String scope;

    /** authorizedGrantTypes */
    private String authorizedGrantTypes;

    /** webServerRedirectUri */
    private String webServerRedirectUri;

    /** authorities */
    private String authorities;

    /** accessTokenValidity */
    private Integer accessTokenValidity;

    /** refreshTokenValidity */
    private Integer refreshTokenValidity;

    /** additionalInformation */
    private String additionalInformation;

    /** archived */
    private Integer archived;

    /** trusted */
    private Integer trusted;

    /** autoapprove */
    private String autoapprove;

    /** 创建人ID */
    private Long createdBy;

    /** 创建时间 */
    private Long createdTm;

    /** 修改或删除人ID */
    private Long updatedBy;

    /** 修改或删除时间 */
    private Long updatedTm;

    /** 逻辑删除is_flag tinyint,1:正常|0:删除，默认值为1 */
    private Integer isFlag;
}
