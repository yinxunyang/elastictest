package com.bestvike.bvch.service.impl;

import com.bestvike.bvch.param.ConBaseInfoParam;
import com.bestvike.bvch.service.ICommitTransService;
import com.bestvike.bvch.service.ITradeRecodeService;
import com.bestvike.portal.data.TradeRecord;
import com.bestvike.rfis.dao.ConBaseInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: yinxunyang
 * @Description: 提交事务实现类
 * @Date: 2019/9/12 15:43
 */
@Service
public class CommitTransServiceImpl implements ICommitTransService {
	@Autowired
	private ITradeRecodeService iTradeRecodeService;
	@Autowired
	private ConBaseInfoDao conBaseInfoDao;

	/**
	 * @Author: yinxunyang
	 * @Description: 新增tradeRecode和更新Con_BaseInfo，同事务处理
	 * @Date: 2019/9/12 15:48
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void addTradeAndUpdateContract(List<ConBaseInfoParam> conBaseInfoParamList, List<TradeRecord> tradeRecodeList) {
		// 新增tradeRecode
		iTradeRecodeService.insertBatchTradeRecode(tradeRecodeList);
		// 更新Con_BaseInfo
		conBaseInfoDao.updateConBaseInfoByBatch(conBaseInfoParamList);
	}
}
