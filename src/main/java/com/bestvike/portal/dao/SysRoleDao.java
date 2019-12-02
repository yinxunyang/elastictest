package com.bestvike.portal.dao;

import com.bestvike.portal.data.SysRole;
import org.mybatis.mapper.common.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleDao extends BaseMapper<SysRole> {
    public List<SysRole> selectAllExtend();
    public Integer selectOrder();
}
