package com.bestvike.portal.security.base;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by lihua on 2016/9/14.
 */
@Component
public class CustomAuthenticationFilter extends BasicAuthenticationFilter {
    @Value("${app.authority.login.allow-retry-times:-1}")
    private Integer allowRetryTimes;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
        super(authenticationManager, customAuthenticationEntryPoint);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        /*String clientId = request.getParameter("clientId");
        if (!StringUtils.isEmpty(clientId)) {
            request.setAttribute("client_id", clientId);
        }*/
        super.doFilterInternal(request, response, chain);
    }

    @Override
    public void onUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        // 用户名或密码错
        HttpSession httpSession = request.getSession(false);
        Integer retryTimes = (Integer) httpSession.getAttribute("retryTimes");
        if (retryTimes == null) {
            retryTimes = 1;
        } else {
            retryTimes++;
        }
        httpSession.setAttribute("retryTimes", retryTimes);
        int statusCode = 401;
        if (allowRetryTimes > 0 && retryTimes > allowRetryTimes) {
            statusCode = 403;
        }
        if (exception instanceof UsernameNotFoundException) {
//            if (exception instanceof ValidateCodeInvalidException) {
//                response.sendError(statusCode, "请输入正确的验证码");
//            } else if (exception instanceof UsernameEmptyException) {
//                response.sendError(statusCode, "用户名或密码错");
//            } else {
//                // 用户名错
//                response.sendError(statusCode, "用户不存在");
//            }
            // modify by jiankai 直接取异常中的message
            response.sendError(statusCode, exception.getMessage());
        } else if (exception instanceof BadCredentialsException) {
            response.sendError(statusCode, "用户名或密码错误");
        }
    }
}