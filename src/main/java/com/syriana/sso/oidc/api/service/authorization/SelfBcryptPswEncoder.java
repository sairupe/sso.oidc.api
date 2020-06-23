package com.syriana.sso.oidc.api.service.authorization;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author syriana.zh
 * @date 2020/06/23
 */
@Component
public class SelfBcryptPswEncoder extends BCryptPasswordEncoder {
}
