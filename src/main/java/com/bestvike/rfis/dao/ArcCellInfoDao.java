package com.bestvike.rfis.dao;

import com.bestvike.rfis.data.ArcCellInfo;
import org.mybatis.mapper.common.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface ArcCellInfoDao extends BaseMapper<ArcCellInfo> {

    ArcCellInfo queryArcCellInfoByArcCellInfoMap(Map arcCellInfoMap);
}
