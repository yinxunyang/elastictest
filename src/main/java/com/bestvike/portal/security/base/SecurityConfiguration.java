package com.bestvike.portal.security.base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@Order(2)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    protected Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;
    @Autowired
    private CustomAuthenticationFilter customAuthenticationFilter;
    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatcher(request -> {
                    String auth = request.getHeader(HttpHeaders.AUTHORIZATION);
                    return (auth == null || auth.startsWith("Basic"));
                }).authenticationProvider(customAuthenticationProvider)
                // .addFilterBefore(customOauth2AuthenticationFilter, SecurityContextPersistenceFilter.class)
                .httpBasic()
                .and().addFilterBefore(customAuthenticationFilter, BasicAuthenticationFilter.class)
                .logout().logoutUrl("/api/authority/logout").logoutSuccessHandler(customLogoutSuccessHandler)
                //.and()
                //.formLogin().loginPage("/")
                .and()
                .authorizeRequests()
                .antMatchers("/", "/api/cache/config", "/api/authority/check", "/api/authority/userRegister", "/api/authority/login", "/api/authority/logout", "/api/authority/generateCode", "/api/authority/register", "/api/authority/menus", "/oauth/token", "/api/authority/token").permitAll()
                .antMatchers("/public/**", "/custom/**", "/modules/**", "/index.html", "/companyList.html", "/modules/bvch/applyVitaRegister.html", "/modules/bvch/tenementCompanyRegister.html","/main.html", "/config.js").permitAll()
                .antMatchers("/pub/**", "/app/**", "/api/demo/**").permitAll()
                .antMatchers( "/api/regiInfo/**").permitAll()
                .antMatchers( "/api/register/**").permitAll()
                .antMatchers( "/api/common/**").permitAll()
                .antMatchers( "/api/file/**").permitAll()
                .antMatchers( "/api/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .headers().defaultsDisabled().cacheControl();
    }
}
