package com.bestvike.portal.dao;

import com.bestvike.portal.data.SysRole;
import com.bestvike.portal.data.SysUserRight;
import java.util.List;
import org.mybatis.mapper.common.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface SysUserRightDao extends BaseMapper<SysUserRight> {
    public void insertSysUserRights(Map<String, Object> parameterMap);
    public List<SysRole> authorityRoles(Map<String, Object> parameterMap);
}
