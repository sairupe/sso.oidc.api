package com.syriana.sso.oidc.api.response;

import lombok.Data;

/**
 * @author syriana.zh
 * @date 2020/06/23
 */
@Data
public class UserInfoResVo {


    private String accountId;

    private String userName;

    private String password;

}
