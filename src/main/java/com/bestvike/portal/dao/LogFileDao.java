package com.bestvike.portal.dao;

import com.bestvike.portal.data.LogFile;
import org.mybatis.mapper.common.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface LogFileDao extends BaseMapper<LogFile> {
    List<LogFile> selectCompanyFile(Map<String, Object> parameterMap);
    List<LogFile> selectOwnerFile(Map<String, Object> parameterMap);
    List<LogFile> selectPreReqFile(Map<String, Object> parameterMap);
    List<LogFile> selectReqAllFile(Map<String, Object> parameterMap);
    List<LogFile> selectReqFile(Map<String, Object> parameterMap);
    List<LogFile> selectSignFile(Map<String, Object> parameterMap);
    List<LogFile> selectCompleteFile(Map<String, Object> parameterMap);
    List<LogFile> selectCompleteAllFile(Map<String, Object> parameterMap);
    int updateShowName(LogFile logFile);
    int deleteById(LogFile logFile);
    void updateKeyId(LogFile logFile);
}
