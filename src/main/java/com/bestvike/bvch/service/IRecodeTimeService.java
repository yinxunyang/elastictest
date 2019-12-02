package com.bestvike.bvch.service;

import com.bestvike.rfis.data.RecodeTime;

/**
 * @Author: yinxunyang
 * @Description: 记录表service
 * @Date: 2019/9/12 8:46
 */
public interface IRecodeTimeService {
	void saveRecode(RecodeTime recodeTime);

	void updateRecode(RecodeTime recodeTime);

	/**
	 * @Author: yinxunyang
	 * @Description: 查询RecodeTime日期最新的数据
	 * @Date: 2019/9/17 9:51
	 */
	RecodeTime selectRecodeTimeByLastTime();
}
