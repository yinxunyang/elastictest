package com.bestvike.portal.config;

import com.bestvike.portal.service.ConfigService;
import com.bestvike.portal.support.CacheGlobal;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.cache.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitListener implements ServletContextListener {
    protected Log logger = LogFactory.getLog(this.getClass());

    @Value("${app.instance.code}")
    private String appCode;

    /*@Autowired
    private CacheService cacheService;*/
    @Autowired
    private CacheGlobal cacheGlobal;
    @Autowired
    private ConfigService configService;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext()).getAutowireCapableBeanFactory().autowireBean(this);

        Global.appCode = appCode;
        /// cacheService.cacheCatalog();
        cacheGlobal.cacheSysDict(configService.selectSysDict());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
