package com.bestvike.bvch.service;

import com.bestvike.rfis.data.LogData;

/**
 * @Author: yinxunyang
 * @Description: LogData接口类
 * @Date: 2019/9/19 9:16
 */
public interface ILogDataService {
	/**
	 * @Author: yinxunyang
	 * @Description: 新增logData
	 * @Date: 2019/9/19 9:19
	 * @param:
	 * @return:
	 */
	void addLog(LogData logData);
}
