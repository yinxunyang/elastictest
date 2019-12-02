package com.bestvike.bvch.service.impl;

import com.bestvike.bvch.service.IRecodeTimeService;
import com.bestvike.rfis.dao.RecodeTimeDao;
import com.bestvike.rfis.data.RecodeTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: yinxunyang
 * @Description: 记录表service实现类
 * @Date: 2019/9/12 8:48
 */
@Service
public class RecodeTimeServiceImpl implements IRecodeTimeService {
	@Autowired
	private RecodeTimeDao recodeTimeDao;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveRecode(RecodeTime recodeTime) {
		recodeTimeDao.saveRecode(recodeTime);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateRecode(RecodeTime recodeTime) {
		recodeTimeDao.updateRecode(recodeTime);
	}

	/**
	 * @Author: yinxunyang
	 * @Description: 查询RecodeTime日期最新的数据
	 * @Date: 2019/9/17 9:51
	 */
	@Override
	public RecodeTime selectRecodeTimeByLastTime() {
		return recodeTimeDao.selectRecodeTimeByLastTime();
	}
}
