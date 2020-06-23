package com.syriana.sso.oidc.api.response.common;

import lombok.Data;

/**
 * @author syriana.zh
 * @date 2020/06/23
 */
@Data
public class RestResult<T> {
    private String msg;
    private Boolean succeed = true;
    private T data;
    private String code = "200";
    private String requestId;
}
