package com.bestvike.frame.dao;


import com.bestvike.frame.data.DicUserInfo;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.utils.PaginationList;
import org.mybatis.mapper.common.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by bv_RD on 2018/9/6.
 */
@Repository
public interface DicUserInfoDao extends BaseMapper<DicUserInfo> {
    public PaginationList<DicUserInfo> pageByFilter(Map<String, Object> parameterMap, RowBounds rowBounds);
    public List<DicUserInfo> getByCorpType();
}
