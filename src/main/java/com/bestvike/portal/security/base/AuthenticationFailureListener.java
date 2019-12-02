package com.bestvike.portal.security.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * Created by lihua on 2016/9/13.
 * 登录失败调用
 */
@Component
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
    @Autowired
    private HttpSession httpSession;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        // WebAuthenticationDetails auth = (WebAuthenticationDetails) event.getAuthentication().getDetails();
        Integer failTimes = (Integer) httpSession.getAttribute("failTimes");
        if (failTimes == null) {
            httpSession.setAttribute("failTimes", 0);
        } else {
            httpSession.setAttribute("failTimes", failTimes + 1);
        }
    }
}
