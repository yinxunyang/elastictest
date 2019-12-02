package com.bestvike.rfis.dao;

import com.bestvike.rfis.data.ConBuyerInfo;
import org.mybatis.mapper.common.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ConBuyerInfoDao extends BaseMapper<ConBuyerInfo> {

   List<ConBuyerInfo> queryConBuyerInfoBycontractNo(String contractNo);

   Integer queryConBuyerInfoCountBycontractNo(Map map);
}
