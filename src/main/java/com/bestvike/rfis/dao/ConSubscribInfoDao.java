package com.bestvike.rfis.dao;

import com.bestvike.rfis.data.ConSubscribInfo;
import org.mybatis.mapper.common.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface ConSubscribInfoDao extends BaseMapper<ConSubscribInfo> {

    ConSubscribInfo queryConSubscribInfoByContractNo(String contractNo);
}
