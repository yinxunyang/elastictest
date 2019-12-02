package com.bestvike.portal.dao;

import com.bestvike.portal.data.TradeRecord;
import org.mybatis.mapper.common.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TradeRecordDao extends BaseMapper<TradeRecord> {
    /**
     * @Author: yinxunyang
     * @Description: 批量新增中间表
     * @Date: 2019/9/18 11:06
     */
    void insertBatchTradeRecode(List<TradeRecord> tradeRecodeList);

    List<TradeRecord> selectByParamter(Map map);

    List<TradeRecord> selectByParamterCLF(Map map);
}
