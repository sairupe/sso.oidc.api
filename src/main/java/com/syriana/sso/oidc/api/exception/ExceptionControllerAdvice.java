package com.syriana.sso.oidc.api.exception;

import com.syriana.sso.oidc.api.response.common.RestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    @ExceptionHandler({Exception.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public RestResult handle(Exception e) {
        log.error(e.getMessage(), e);
        RestResult result = new RestResult();
        RestResult restResult = new RestResult();
        restResult.setCode(ErpSSOOidcExStatus.ILLEGAL_PARAM.getCode());
        restResult.setMsg(e.getMessage());
        restResult.setSucceed(false);
        return result;
    }

}