package com.bestvike.rfis.dao;

import com.bestvike.rfis.data.ArcCorpInfo;
import org.mybatis.mapper.common.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface ArcCorpInfoDao extends BaseMapper<ArcCorpInfo> {

    ArcCorpInfo queryArcCorpInfoBycorpNo(String corpNo);
}
