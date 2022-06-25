package com.syriana.sso.oidc.api.config.oauth2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthenticationFailureHandler implements org.springframework.security.web.authentication.AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        httpServletResponse.setStatus(401);
        Map<String, Object> map = new HashMap();
        map.put("status", 401);
        if (e instanceof LockedException) {
            map.put("msg", "账户被锁定，登录失败");
        } else if (e instanceof BadCredentialsException) {
            map.put("msg", "登录失败,请确认账号密码及组织");
        } else if (e instanceof DisabledException) {
            map.put("msg", "账户被禁用，登录失败");
        } else if (e instanceof AccountExpiredException) {
            map.put("msg", "账户已过期，登录失败");
        } else if (e instanceof CredentialsExpiredException) {
            map.put("msg", "密码已过期，登录失败");
        } else {
            map.put("msg", "钉钉登录失败，组织内不存在该用户");
        }
        ObjectMapper om = new ObjectMapper();
        out.write(om.writeValueAsString(map));
        out.flush();
        out.close();

        //        httpServletResponse.sendRedirect("http://www.jd.com");
    }
}
