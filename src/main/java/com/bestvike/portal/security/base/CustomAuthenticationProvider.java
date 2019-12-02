package com.bestvike.portal.security.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.stereotype.Component;

/**
 * Created by lihua on 2016/9/18.
 */
@Component
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    public CustomAuthenticationProvider() {
        super();
        super.setHideUserNotFoundExceptions(false);
    }

    protected void doAfterPropertiesSet() throws Exception {
        super.setUserDetailsService(userDetailsService);
        super.setPasswordEncoder(new CustomEncoder());
    }
}
