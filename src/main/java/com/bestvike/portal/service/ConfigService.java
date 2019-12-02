package com.bestvike.portal.service;

import com.bestvike.commons.mybatis.Entity;
import com.bestvike.datacenter.entity.Version;
import com.bestvike.portal.data.SysCatalog;
import com.bestvike.portal.data.SysDept;
import com.bestvike.portal.data.SysDict;

import java.util.List;
import java.util.Map;

public interface ConfigService {
    public List<SysDict> selectSysDict();
    public int saveSysDict(SysDict sysDict);
    public int deleteSysDict(String dictId);
    /*public int insertSysDept(SysDept sysDept);
    public int updateSysDept(SysDept sysDept);*/
    public int deleteSysArea(String areaCode);
    public List<SysDept> selectSysDeptByAreaCode(String areaCode);
    public int insertSysDept(SysDept sysDept, Map<String, Object> sessionMap);
    public int insertSysDeptFromDatacenter(SysDept sysDept);
    public int updateSysDept(SysDept sysDept, Map<String, Object> sessionMap);
    public int updateSysDeptVersion(Version version);
    public int updateSysDeptFromDatacenter(SysDept sysDept);
    public int deleteSysDept(String deptId);
    public int deleteSysDeptFromDatacenter(SysDept sysDept);

    public int saveSysCatalog(SysCatalog sysCatalog);
    public int deleteSysCatalog(Entity entity);
    public String initDeptId(String parentDept);
}
