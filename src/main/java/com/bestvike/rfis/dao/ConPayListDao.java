package com.bestvike.rfis.dao;

import com.bestvike.rfis.data.ConPayList;
import org.mybatis.mapper.common.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface ConPayListDao extends BaseMapper<ConPayList> {

    ConPayList queryConPayListByContractNo(String contractNo);
}
