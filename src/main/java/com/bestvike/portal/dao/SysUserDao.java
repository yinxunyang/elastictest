package com.bestvike.portal.dao;

import com.bestvike.portal.data.SysUser;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.utils.PaginationList;
import org.mybatis.mapper.common.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SysUserDao extends BaseMapper<SysUser> {

    /**
     * 分页查询用户列表
     * @param parameterMap
     * @param rowBounds
     * @return
     */
    public PaginationList<SysUser> pageSysUser(Map<String, Object> parameterMap, RowBounds rowBounds);

    /**
     * 生成用户编号
     * @return
     */
    public Integer selectId();

    /**
     * 查询用户登录名是否已存在
     * @return parameterMap
     */
    public List<SysUser> selectOtherUserAlias(Map<String, Object> parameterMap);


    public SysUser getOneByUserId(String userId);
}
