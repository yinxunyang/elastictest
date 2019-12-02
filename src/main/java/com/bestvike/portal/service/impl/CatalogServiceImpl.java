package com.bestvike.portal.service.impl;

import com.bestvike.commons.redis.Cache;
import com.bestvike.commons.service.AbstractService;
import com.bestvike.commons.util.StringUtil;
import com.bestvike.portal.service.CatalogService;
import org.apache.ibatis.cache.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Map;

@Service
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class CatalogServiceImpl extends AbstractService implements CatalogService {
    @Autowired
    private Cache cache;

    @Override
    public void cacheCatalog(Map<String, String> dbprefixMap) {
        // 往缓存刷新数据
        Global.dbprefixVersion = StringUtil.serial();
        Global.dbprefixMap = dbprefixMap;
        cache.set("_catalog_version", Global.dbprefixVersion);
        cache.set("_catalog", Global.dbprefixMap);
    }

    @Override
    public void cacheCatalog() {
        // 从redis缓存更新全局变量
        String version = cache.get("_catalog_version");
        if (!StringUtils.isEmpty(version) && !version.equals(Global.dbprefixVersion)) {
            Global.dbprefixVersion = version;
            Global.dbprefixMap = cache.get("_catalog", Map.class);
        }
    }
}
