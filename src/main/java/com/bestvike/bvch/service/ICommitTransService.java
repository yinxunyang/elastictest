package com.bestvike.bvch.service;

import com.bestvike.bvch.param.ConBaseInfoParam;
import com.bestvike.portal.data.TradeRecord;

import java.util.List;

/**
 * @Author: yinxunyang
 * @Description: 提交事务接口类
 * @Date: 2019/9/12 15:42
 */
public interface ICommitTransService {

	/**
	 * @Author: yinxunyang
	 * @Description: 新增tradeRecode和更新Con_BaseInfo，同事务处理
	 * @Date: 2019/9/12 15:47
	 */
	void addTradeAndUpdateContract(List<ConBaseInfoParam> conBaseInfoParamList, List<TradeRecord> tradeRecodeList);
}
