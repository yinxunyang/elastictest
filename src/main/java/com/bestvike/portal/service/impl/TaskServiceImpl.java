package com.bestvike.portal.service.impl;

import com.bestvike.commons.util.DateUtil;
import com.bestvike.portal.dao.SysTaskDao;
import com.bestvike.portal.data.SysTask;
import com.bestvike.portal.service.BaseService;
import com.bestvike.portal.service.TaskService;
import org.mybatis.mapper.entity.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class TaskServiceImpl extends BaseService implements TaskService {
    @Autowired
    private SysTaskDao sysTaskDao;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Date selectTimestamp() {
        Example example = new Example(SysTask.class);
        example.createCriteria().andEqualTo("taskType", "exchange");
        List<SysTask> taskList = sysTaskDao.selectByExample(example);
        if (taskList == null || taskList.size() == 0) {
            Date startDate = DateUtil.parseString(DateUtil.getDate(1900, 1, 1), "yyyy-mm-dd");
            SysTask sysTask = new SysTask();
            sysTask.setTaskType("exchange");
            sysTask.setTaskTimestamp(startDate);
            sysTaskDao.insert(sysTask);
            return startDate;
        }
        return taskList.get(0).getTaskTimestamp();
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int updateTimestamp(Date date) {
        SysTask sysTask = new SysTask();
        sysTask.setTaskType("exchange");
        sysTask.setTaskTimestamp(date);
        return sysTaskDao.updateNotNullByPrimaryKey(sysTask);
    }
}
