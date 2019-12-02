package com.bestvike.portal.dao;

import com.bestvike.portal.data.SysApp;
import org.mybatis.mapper.common.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysAppDao extends BaseMapper<SysApp> {

    /**
     * 获取用户能进入的系统列表
     * @param userId
     * @return
     */
    public List<SysApp> authorityApps(String userId);
}
