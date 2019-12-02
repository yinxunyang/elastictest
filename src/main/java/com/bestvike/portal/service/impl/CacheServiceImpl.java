package com.bestvike.portal.service.impl;

import com.bestvike.portal.dao.SysCatalogDao;
import com.bestvike.portal.data.SysCatalog;
import com.bestvike.portal.service.BaseService;
import com.bestvike.portal.service.CacheService;
import com.bestvike.portal.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class CacheServiceImpl extends BaseService implements CacheService {
    @Autowired
    private SysCatalogDao sysCatalogDao;
    @Autowired
    private CatalogService catalogService;

    @Override
    public void cacheCatalog() {
        List<SysCatalog> sysCatalogList = sysCatalogDao.selectAll();
        Map<String, String> dbprefixMap = new LinkedHashMap<>();
        for (SysCatalog sysCatalog : sysCatalogList) {
            dbprefixMap.put(sysCatalog.getAreaCode() + sysCatalog.getAppCode() + sysCatalog.getCatalogType(), sysCatalog.getCatalogName());
        }
        catalogService.cacheCatalog(dbprefixMap);
    }
}
