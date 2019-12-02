package com.bestvike.portal.dao;

import com.bestvike.portal.data.SysArea;
import org.mybatis.mapper.common.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface SysAreaDao extends BaseMapper<SysArea> {

    /**
     * @Title：queryParentAreaByAreaCode
     * @Description：根据区域编号查询上级区域代码
     * @Author：ybb
     * @Date：2018/2/24 16:21
     * @param：areaCode 区域编号
     * @return parentArea 上级区域代码
     */
    public String queryParentAreaByAreaCode(String areaCode);
}
