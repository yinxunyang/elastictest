package com.bestvike.rfis.dao;

import com.bestvike.rfis.data.ArcHouseInfo;
import org.mybatis.mapper.common.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by bv_RD on 2018/9/15.
 */
@Repository
public interface ArcHouseInfoDao extends BaseMapper<ArcHouseInfo>{

    List<ArcHouseInfo> getHouseListByRegionNo(ArcHouseInfo arcHouseInfo);
    ArcHouseInfo getHouseBySysGuid(String sysGuid);
    ArcHouseInfo qureyArcHouseInfoByMap(Map arcHouseInfoMap);
}
