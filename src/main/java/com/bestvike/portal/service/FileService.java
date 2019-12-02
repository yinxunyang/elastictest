package com.bestvike.portal.service;

import com.bestvike.portal.data.LogFile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by lihua on 2016/10/1.
 */
@Service
public interface FileService {
    public LogFile upload(InputStream inputStream, String name, String showName, String fileType, String subType, String keyId, String fileSource, String userIdAndName) throws IOException;
    public LogFile rotate(LogFile logFile, String userIdAndName);
    public LogFile crop(LogFile logFile, String userIdAndName);
    public int updateShowName(LogFile logFile);
    public LogFile selectById(String fileId);
    public LogFile selectBySign(String sign);
    public int countBySign(String sign);
    public int deleteById(String fileId, String userIdAndName);
    public int deleteBySign(String sign);
    public List<LogFile> getByKeyIdAndFileType(LogFile logFile);
}
