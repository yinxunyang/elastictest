package com.bestvike.rfis.dao;

import com.bestvike.bvch.param.ConBaseInfoParam;
import com.bestvike.rfis.data.ConBaseInfo;
import org.apache.ibatis.annotations.Param;
import org.mybatis.mapper.common.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConBaseInfoDao extends BaseMapper<ConBaseInfo> {
    List<ConBaseInfo> queryConBaseInfoByTime(@Param("param") ConBaseInfoParam conBaseInfoParam);

    /**
     * @Author: yinxunyang
     * @Description: 批量更新合同信息表
     * @Date: 2019/9/12 16:55
     */
    void updateConBaseInfoByBatch(List<ConBaseInfoParam> conBaseInfoParamList);

    /**
     * @Author: yinxunyang
     * @Description: 统计还有多少条合同信息表未迁移
     * @Date: 2019/9/16 19:45
     */
    int countConBaseInfo(@Param("param") ConBaseInfoParam conBaseInfoParam);
}
