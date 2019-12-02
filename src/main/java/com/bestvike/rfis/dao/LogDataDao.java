package com.bestvike.rfis.dao;

import com.bestvike.rfis.data.LogData;
import org.mybatis.mapper.common.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface LogDataDao extends BaseMapper<LogData> {

    void addLog(LogData logData);

}
