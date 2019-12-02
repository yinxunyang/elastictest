package com.bestvike.bvch.sequence.impl;

import com.bestvike.bvch.sequence.ITradeSequenceService;
import com.bestvike.rfis.dao.SequenceDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author: yinxunyang
 * @Description: 中间表主键生成序列实现类
 * @Date: 2019/9/11 14:31
 */
@Service
public class TradeSequenceServiceImpl implements ITradeSequenceService {
	@Value("${config.KSXH}")
	private String KSXH;
	@Autowired
	private SequenceDao sequenceDao;
	public Log log = LogFactory.getLog(getClass());

	@Override
	public Integer generateTradeXH() {
		try {
			String tradeXHSequence = sequenceDao.generateTradeXH();
			StringBuilder sb = new StringBuilder();
			// 庆云县开头为126，陵城区开头为103
			String tradeXHStr = sb
					.append(KSXH)
					.append(tradeXHSequence)
					.toString();
			return Integer.valueOf(tradeXHStr);
		} catch (Exception e) {
			log.error("根据序列生成中间表的主键异常", e);
			throw e;
		}
	}

	/**
	 * @Author: yinxunyang
	 * @Description: 日志表的主键
	 * @Date: 2019/9/12 9:52
	 */
	@Override
	public String generateLogId(String scopeBeginTime) {
		try {
			String logIdSequence = sequenceDao.generateLogId();
			// 取值范围时间
			String preDate = scopeBeginTime
					.replace("-", "")
					.replace(":", "")
					.replace(" ", "");
			// 庆云县开头为126，陵城区开头为103
			return new StringBuilder()
					.append(KSXH)
					.append(preDate)
					.append(logIdSequence)
					.toString();
		} catch (Exception e) {
			log.error("根据序列日志表的主键异常", e);
			throw e;
		}
	}
}
