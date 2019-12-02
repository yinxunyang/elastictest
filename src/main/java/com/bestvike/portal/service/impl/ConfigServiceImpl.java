package com.bestvike.portal.service.impl;

import com.bestvike.commons.mybatis.Entity;
import com.bestvike.commons.service.CommonService;
import com.bestvike.commons.util.DateUtil;
import com.bestvike.commons.util.StringUtil;
import com.bestvike.datacenter.entity.Version;
import com.bestvike.portal.dao.SysAreaDao;
import com.bestvike.portal.dao.SysCatalogDao;
import com.bestvike.portal.dao.SysDeptDao;
import com.bestvike.portal.dao.SysDictDao;
import com.bestvike.portal.data.SysCatalog;
import com.bestvike.portal.data.SysDept;
import com.bestvike.portal.data.SysDict;
import com.bestvike.portal.mq.Publisher;
import com.bestvike.portal.service.BaseService;
import com.bestvike.portal.service.CacheService;
import com.bestvike.portal.service.ConfigService;
import com.bestvike.portal.support.CacheGlobal;
import org.mybatis.mapper.entity.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class ConfigServiceImpl extends BaseService implements ConfigService {
    @Autowired
    private SysDictDao sysDictDao;
    @Autowired
    private SysAreaDao sysAreaDao;
    @Autowired
    private SysDeptDao sysDeptDao;
    @Autowired
    private SysCatalogDao sysCatalogDao;
    @Autowired
    private CacheService cacheService;
    @Autowired
    private CommonService commonService;

    @Autowired
    private CacheGlobal cacheGlobal;
    @Autowired
    private Publisher publisher;

    @Override
    public List<SysDict> selectSysDict() {
        return sysDictDao.selectAll();
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int saveSysDict(SysDict sysDict) {
        if (StringUtils.isEmpty(sysDict.getDictId())) {
            sysDict.setDictId(StringUtil.guid());
            sysDictDao.insert(sysDict);
        } else {
            sysDictDao.updateByPrimaryKey(sysDict);
        }
        cacheGlobal.cacheSysDict(sysDictDao.selectAll());

        return 0;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int deleteSysDict(String dictId) {
        sysDictDao.deleteByPrimaryKey(dictId);
        cacheGlobal.cacheSysDict(sysDictDao.selectAll());
        return 0;
    }

    /*@Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int insertSysDept(SysDept sysDept) {
        return sysDeptDao.insert(sysDept);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int updateSysDept(SysDept sysDept) {
        return sysDeptDao.updateByPrimaryKey(sysDept);
    }*/

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int deleteSysArea(String areaCode) {
        return sysAreaDao.deleteByPrimaryKey(areaCode);
    }

    @Override
    public List<SysDept> selectSysDeptByAreaCode(String areaCode) {
        return sysDeptDao.selectByAreaCode(areaCode);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int insertSysDept(SysDept sysDept, Map<String, Object> sessionMap) {

        if (StringUtils.isEmpty(sysDept.getDeptId())) {
            sysDept.setDeptId(initDeptId(sysDept.getParentDept()));
        }
        sysDept.setManageTime(DateUtil.getDateDate());
        sysDept.setManageUser(super.getUserIdAndName(sessionMap));
        sysDept.setDatacenterId(StringUtil.guid());
        sysDept.setDeptState("0000");
        sysDeptDao.insert(sysDept);

        Integer deptLevel = sysDept.getDeptLevel();
        // 1级企业类型2级-企业
        if (deptLevel != null && deptLevel == 2) {
            publisher.send("insert", sysDept);
        }

        return 0;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int insertSysDeptFromDatacenter(SysDept sysDept) {
        if (StringUtils.isEmpty(sysDept.getDeptId())) {
            // TODO:
            sysDept.setDeptId(initDeptId("01"));
        }
        sysDeptDao.insertFromDatacenter(sysDept);
        return 0;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int updateSysDept(SysDept sysDept, Map<String, Object> sessionMap) {
        sysDept.setManageTime(DateUtil.getDateDate());
        sysDept.setManageUser(super.getUserIdAndName(sessionMap));
        sysDept.setDeptState("0000");
        //sysDept.setDatacenterId(StringUtil.guid());
        sysDeptDao.updateByPrimaryKey(sysDept);
        Integer deptLevel = sysDept.getDeptLevel();
        if (deptLevel != null && deptLevel == 2) {
            SysDept sysDeptVersion = sysDeptDao.selectByPrimaryKey(sysDept.getDeptId());
            if (sysDeptVersion != null) {
                publisher.send("update", sysDeptVersion);
            }
        }
        return 0;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int updateSysDeptVersion(Version version) {
        sysDeptDao.updateVersion(version);
        return 0;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int updateSysDeptFromDatacenter(SysDept sysDept) {
        sysDeptDao.updateFromDatacenter(sysDept);
        return 0;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int deleteSysDept(String deptId) {
        SysDept sysDept = sysDeptDao.selectByPrimaryKey(deptId);
        if (sysDept != null) {
            sysDeptDao.updateForDelete(deptId);
            Integer deptLevel = sysDept.getDeptLevel();
            if (deptLevel != null && deptLevel == 2) {
                sysDept.setDeptState("9999");
                publisher.send("delete", sysDept);
            }
        }

        return 1;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int deleteSysDeptFromDatacenter(SysDept sysDept) {
        Example example = new Example(SysDept.class);
        example.createCriteria().andEqualTo("datacenterId", sysDept.getDatacenterId());
        sysDeptDao.deleteByExample(example);
        return 0;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int saveSysCatalog(SysCatalog sysCatalog) {
        if (StringUtils.isEmpty(sysCatalog.getCatalogId())) {
            sysCatalog.setCatalogId(StringUtil.guid());
            sysCatalogDao.insert(sysCatalog);
        } else {
            sysCatalogDao.updateByPrimaryKey(sysCatalog);
        }
        cacheService.cacheCatalog();
        return 0;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int deleteSysCatalog(Entity entity) {
        commonService.deleteByIds(entity);
        cacheService.cacheCatalog();
        return 0;
    }

    @Override
    public String initDeptId(String parentDept) {
        String maxCorpNo = sysDeptDao.selectMaxDeptId(parentDept);
        int orderNo = 0;
        if (!StringUtils.isEmpty(maxCorpNo)) {
            orderNo = Integer.valueOf(maxCorpNo.substring(parentDept.length()));
        }
        return parentDept + StringUtil.fillLeft(orderNo + 1, 2);
    }
}
