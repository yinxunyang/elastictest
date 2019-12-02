package com.bestvike.frame.dao;

import com.bestvike.frame.data.DicPublist;
import org.mybatis.mapper.common.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by bv_RD on 2018/9/21.
 */
@Repository
public interface DicPublistDao extends BaseMapper<DicPublist> {
    List<Map<String, Object>> getAllPublist();
    List<DicPublist> getCertType();
}
