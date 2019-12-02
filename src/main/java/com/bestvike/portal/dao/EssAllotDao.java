package com.bestvike.portal.dao;

import com.bestvike.portal.data.EssAllot;
import com.bestvike.portal.data.SysUser;
import java.util.Map;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.utils.PaginationList;
import org.mybatis.mapper.common.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface EssAllotDao extends BaseMapper<EssAllot> {

}
