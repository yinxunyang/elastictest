package com.bestvike.bvch.service.impl;

import com.bestvike.bvch.service.ILogDataService;
import com.bestvike.rfis.dao.LogDataDao;
import com.bestvike.rfis.data.LogData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: yinxunyang
 * @Description: LogData实现类
 * @Date: 2019/9/19 9:19
 */
@Service
public class LogDataServiceImpl implements ILogDataService {
	@Autowired
	private LogDataDao logDataDao;

	/**
	 * @Author: yinxunyang
	 * @Description: 新增logData
	 * @Date: 2019/9/19 9:19
	 * @param:
	 * @return:
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void addLog(LogData logData) {
		logDataDao.addLog(logData);
	}
}
