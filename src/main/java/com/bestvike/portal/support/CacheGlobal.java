package com.bestvike.portal.support;

import com.bestvike.commons.redis.Cache;
import com.bestvike.portal.data.SysDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class CacheGlobal {
    @Autowired
    private Cache cache;
    // public static Map<String, List<SysDict>> sysDictMap = null;

    public Map<String, List<SysDict>> cacheSysDict() {
        // return CacheGlobal.sysDictMap;
        return cache.get("sysDictMap", Map.class);
    }
    public void cacheSysDict(List<SysDict> sysDictList) {
        Map<String, List<SysDict>> sysDictMap = new LinkedHashMap<>();
        for (SysDict sysDict : sysDictList) {
            String dictCode = sysDict.getDictCode();
            String parentCode = sysDict.getParentCode();
            if (StringUtils.isEmpty(parentCode)) {
                List<SysDict> list = sysDictMap.get(dictCode);
                if (list == null) {
                    sysDictMap.put(dictCode, new ArrayList<SysDict>());
                }
            } else {
                List<SysDict> list = sysDictMap.get(parentCode);
                if (list == null) {
                    list = new ArrayList<SysDict>();
                }
                list.add(sysDict);
                sysDictMap.put(parentCode, list);
            }
        }
        cache.set("sysDictMap", sysDictMap);
    }
}
