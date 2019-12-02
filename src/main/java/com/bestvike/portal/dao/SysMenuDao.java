package com.bestvike.portal.dao;

import com.bestvike.portal.data.SysMenu;
import com.bestvike.portal.entity.DicMenuInfo;
import org.mybatis.mapper.common.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SysMenuDao extends BaseMapper<SysMenu> {
    public List<SysMenu> authorityMenus(Map<String, Object> parameterMap);

    /**
     * 获取用户对应的系统菜单
     * @param parameterMap
     * @return
     */
    public List<DicMenuInfo> authorityBVMenus(Map<String, Object> parameterMap);
}
