/*
 * Copyright(c) 2018 优居科技 All rights reserved.
 * distributed with this file and available online at
 * http://www.yjyz.com/
 */
package com.syriana.sso.oidc.api.dao;
import com.syriana.sso.oidc.api.entity.ClientDetailsEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
* 接入客户信息
 *
 * @author zh
 * @version v1.0
 * @CreationTime: - 2020-07-03 15:34:57
 * @Description:
 */
@Mapper
public interface ClientDetailsMapper  {

	/**
	 * 获取接入客户信息详情
	 */
	ClientDetailsEntity getById(Long id);

	/**
	 * 获取接入客户信息详情
	 */
	ClientDetailsEntity getByClientId(String clientId);

	/**
	 * 接入客户信息添加
	 */
	int insert(ClientDetailsEntity clientDetails);

	/**
	 * 接入客户信息删除
	 */
	int delete(Long clientDetailsId);



}