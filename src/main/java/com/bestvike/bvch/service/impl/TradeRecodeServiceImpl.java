package com.bestvike.bvch.service.impl;

import com.bestvike.bvch.service.ITradeRecodeService;
import com.bestvike.portal.dao.TradeRecordDao;
import com.bestvike.portal.data.TradeRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author: yinxunyang
 * @Description: 中间表service实现类
 * @Date: 2019/9/12 8:48
 */
@Service
public class TradeRecodeServiceImpl implements ITradeRecodeService {
	@Autowired
	private TradeRecordDao tradeRecodeDao;

	/**
	 * @Author: yinxunyang
	 * @Description: 新增中间表
	 * @Date: 2019/9/12 8:50
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertBatchTradeRecode(List<TradeRecord> tradeRecodeList) {
		tradeRecodeDao.insertBatchTradeRecode(tradeRecodeList);
	}

	/**
	 * @Author: yinxunyang
	 * @Description: 查询中间表
	 * @Date: 2019/9/12 8:54
	 */
	@Override
	public List<TradeRecord> selectByParamter(Map map) {
		return tradeRecodeDao.selectByParamter(map);
	}
}
