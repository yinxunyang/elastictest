package com.bestvike.portal.dao;

import com.bestvike.portal.data.DemoTable;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.utils.PaginationList;
import org.mybatis.mapper.common.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface DemoTableDao extends BaseMapper<DemoTable> {
    public PaginationList<DemoTable> pageDemoTable(Map<String, Object> parameterMap, RowBounds rowBounds);
}
