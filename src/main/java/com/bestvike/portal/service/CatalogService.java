package com.bestvike.portal.service;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by lihua on 2016/7/26.
 */

@Service
public interface CatalogService {
    public void cacheCatalog(Map<String, String> dbprefixMap);
    public void cacheCatalog();
}
