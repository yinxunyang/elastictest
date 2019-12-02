package com.bestvike.bvch.service;

import com.bestvike.portal.data.TradeRecord;

import java.util.List;
import java.util.Map;

/**
 * @Author: yinxunyang
 * @Description: 中间表service
 * @Date: 2019/9/12 8:46
 */
public interface ITradeRecodeService {

	/**
	 * @Author: yinxunyang
	 * @Description: 查询中间表
	 * @Date: 2019/9/12 8:53
	 */
	List<TradeRecord> selectByParamter(Map map);
}
