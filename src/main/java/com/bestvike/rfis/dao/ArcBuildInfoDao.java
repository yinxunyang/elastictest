package com.bestvike.rfis.dao;

import com.bestvike.rfis.data.ArcBuildInfo;
import org.mybatis.mapper.common.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArcBuildInfoDao extends BaseMapper<ArcBuildInfo> {

    List< ArcBuildInfo> getArcBuildInfo();

    ArcBuildInfo queryArcBuildInfoBybldNo(String bldNo);
}
