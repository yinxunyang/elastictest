package com.bestvike.rfis.dao;

import com.bestvike.rfis.data.ArcProjectInfo;
import org.mybatis.mapper.common.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface ArcProjectInfoDao extends BaseMapper<ArcProjectInfo> {

    ArcProjectInfo queryArcProjectInfoByProjectNo(String projectNo);
}
