package com.bestvike.portal.service;

import com.bestvike.commons.service.AbstractService;

import java.util.Map;

/**
 * Created by lihua on 2016/8/27.
 */
public class BaseService extends AbstractService {
    protected String getUserIdAndName(Map<String, Object> sessionMap) {
        String userId = (String) sessionMap.get("userId");
        if (userId == null) {
            return null;
        } else {
            String userName = (String) sessionMap.get("userName");
            if (userName == null) {
                return null;
            }
            return userId + "_" + userName;
        }
    }
}
