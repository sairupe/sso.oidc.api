package com.syriana.sso.oidc.api.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author syriana.zh
 * @date 2020/06/23
 */
public class IncorrectPasswordException extends AuthenticationException {

    public IncorrectPasswordException(String msg) {
        super(msg);
    }

    public IncorrectPasswordException(String msg, Throwable t) {
        super(msg, t);
    }
}
