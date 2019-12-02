package com.bestvike.portal.controller;

import com.bestvike.portal.data.SysDict;
import com.bestvike.portal.service.CacheService;
import com.bestvike.portal.support.CacheGlobal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cache")
public class CacheController extends BaseController {
    @Autowired
    private CacheGlobal cacheGlobal;

    public CacheController( ) {
        super("09");
    }

    @RequestMapping(value = "/dicts", method = RequestMethod.GET)
    public Map<String, List<SysDict>> saveSysDict() {
        return cacheGlobal.cacheSysDict();
    }
}
