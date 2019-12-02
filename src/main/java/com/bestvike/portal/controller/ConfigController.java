package com.bestvike.portal.controller;

import com.bestvike.commons.mybatis.Entity;
import com.bestvike.portal.data.SysCatalog;
import com.bestvike.portal.data.SysDept;
import com.bestvike.portal.data.SysDict;
import com.bestvike.portal.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ConfigController extends BaseController {
    @Autowired
    private ConfigService configService;

    public ConfigController( ) {
        super("01");
    }

    @RequestMapping(value = "/dict/save", method = RequestMethod.POST)
    public int saveSysDict(@RequestBody SysDict sysDict) {
        return configService.saveSysDict(sysDict);
    }

    @RequestMapping(value = "/dict/{dictId}", method = RequestMethod.DELETE)
    public int deleteSysDict(@PathVariable("dictId") String dictId) {
        return configService.deleteSysDict(dictId);
    }

    /*@RequestMapping(value = "/dept/insert", method = RequestMethod.POST)
    public int insertSysDept(@RequestBody SysDept sysDept) {
        return configService.insertSysDept(sysDept);
    }

    @RequestMapping(value = "/dept/update", method = RequestMethod.POST)
    public int updateSysDept(@RequestBody SysDept sysDept) {
        return configService.updateSysDept(sysDept);
    }*/

    @RequestMapping(value = "/area/{areaCode}", method = RequestMethod.DELETE)
    public int deleteSysArea(@PathVariable("areaCode") String areaCode) {
        return configService.deleteSysArea(areaCode);
    }

    @RequestMapping(value = "/dept/selectByAreaCode", method = RequestMethod.POST)
    public List<SysDept> selectSysDeptByAreaCode(@RequestBody SysDept sysDept) {
        return configService.selectSysDeptByAreaCode(sysDept.getAreaCode());
    }

    @RequestMapping(value = "/dept/insert", method = RequestMethod.POST)
    public int insertSysDept(@RequestBody SysDept sysDept) {
        return configService.insertSysDept(sysDept, super.getUser());
    }
    @RequestMapping(value = "/dept/update", method = RequestMethod.POST)
    public int updateSysDept(@RequestBody SysDept sysDept) {
        return configService.updateSysDept(sysDept, super.getUser());
    }

    @RequestMapping(value = "/dept/{deptId}", method = RequestMethod.DELETE)
    public int deleteSysDept(@PathVariable("deptId") String deptId) {
        return configService.deleteSysDept(deptId);
    }

    @RequestMapping(value = "/catalog/save", method = RequestMethod.POST)
    public int saveSysCatalog(@RequestBody SysCatalog sysCatalog) {
        return configService.saveSysCatalog(sysCatalog);
    }

    @RequestMapping(value = "/catalog/deleteByIds", method = RequestMethod.POST)
    public int deleteSysCatalog(@RequestBody Entity entity) {
        return configService.deleteSysCatalog(entity);
    }
}
