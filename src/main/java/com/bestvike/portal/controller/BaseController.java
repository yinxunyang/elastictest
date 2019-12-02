package com.bestvike.portal.controller;

import com.bestvike.commons.controller.AbstractController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

/**
 * Created by lihua on 2016/8/27.
 */
public class BaseController extends AbstractController {

    @Value("${app.file.template-path:}")
    protected String templatePath;

    public BaseController(String moduleNo) {
        super(moduleNo);
    }

    public String remoteAddress() {
        String ip = httpServletRequest.getHeader("X-Real-IP");
        if (StringUtils.isEmpty(ip) || ip.equalsIgnoreCase("unknown")) {
            return httpServletRequest.getRemoteAddr();
        }
        return ip;
    }
}
