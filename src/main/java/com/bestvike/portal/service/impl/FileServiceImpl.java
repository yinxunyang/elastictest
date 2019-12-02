package com.bestvike.portal.service.impl;

import com.bestvike.commons.support.ServiceException;
import com.bestvike.commons.util.DateUtil;
import com.bestvike.commons.util.FileUtil;
import com.bestvike.commons.util.ImageUtil;
import com.bestvike.commons.util.StringUtil;
import com.bestvike.portal.dao.LogFileDao;
import com.bestvike.portal.data.LogFile;
import com.bestvike.portal.service.BaseService;
import com.bestvike.portal.service.FileService;
import com.bestvike.portal.util.BvchFileUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.mybatis.mapper.entity.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.List;

/**
 * Created by lihua on 2016/10/1.
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class FileServiceImpl extends BaseService implements FileService {
    @Value("${app.file.upload-path:}")
    private String uploadPath;
    @Value("${app.file.recycle-path:}")
    private String recyclePath;
    @Value("${spring.http.multipart.max-file-size:}")
    private String maxFileSize;

    @Autowired
    private LogFileDao logFileDao;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public LogFile upload(InputStream inputStream, String name, String showName, String fileType, String subType, String keyId, String fileSource, String userIdAndName) throws IOException {
        // TODO: inputStream不能多次读取，因此先读出来
        // TODO: 下一步考虑是否从文件内容获取文件类型，而不是根据文件名
        byte[] bytes = FileUtil.read(inputStream);
        // TODO:
        inputStream.close();
        int fileSize = bytes.length;
        long maxSize = 0;
        if (!StringUtils.isEmpty(maxFileSize)) {
            maxFileSize = maxFileSize.toUpperCase();
            maxSize = maxFileSize.endsWith("KB") ? Long.valueOf(maxFileSize.substring(0, maxFileSize.length() - 2)).longValue() * 1024L : (maxFileSize.endsWith("MB") ? Long.valueOf(maxFileSize.substring(0, maxFileSize.length() - 2)).longValue() * 1024L * 1024L : Long.valueOf(maxFileSize).longValue());
        }
        if (maxSize > 0 && fileSize > maxSize) {
            bytes = null;
            throw new ServiceException("99", "文件大小不能超过" + maxFileSize);
        }

        String md5 = DigestUtils.md5Hex(bytes);
        String sha1 = DigestUtils.sha1Hex(bytes);
        String sign = md5;
        /* 不对文件进行去重
        Example example = new Example(LogFile.class);
        example.createCriteria().andEqualTo("fileMd5", md5);
        List<LogFile> list = logFileDao.selectByExample(example);
        String sign = md5;
        if (list != null && list.size() == 1) {
            // 存在md5相同的记录，需要判断sign
            LogFile savedFile = list.get(0);
            if (savedFile.getFileSha1() != null && savedFile.getFileSha1().equals(sha1) && savedFile.getFileSize() != null && savedFile.getFileSize() == fileSize) {
                LogFile logFile = new LogFile();

                logFile.setFileId(StringUtil.guid());
                logFile.setFileType(fileType);
                logFile.setSubType(subType);
                logFile.setKeyId(keyId);
                logFile.setFileName(savedFile.getFileName());
                logFile.setOriginName(savedFile.getOriginName());
                logFile.setShowName(savedFile.getShowName());
                logFile.setFilePath(savedFile.getFilePath());
                // fileDescribe
                logFile.setExtName(savedFile.getExtName());
                // extType
                logFile.setFileSize(savedFile.getFileSize());
                logFile.setThumbName(savedFile.getThumbName());
                logFile.setImageWidth(savedFile.getImageWidth());
                logFile.setImageHeight(savedFile.getImageHeight());
                logFile.setFileMd5(savedFile.getFileMd5());
                logFile.setFileSha1(savedFile.getFileSha1());
                logFile.setFileSign(savedFile.getFileSign());
                logFile.setFileSource(savedFile.getFileSource());
                // deleteTime
                // deleteUser
                logFile.setFileState("0000");
                // areaCode
                // appCode
                logFile.setManageTime(DateUtil.getDateDate());
                logFile.setManageUser(userIdAndName);
                logFileDao.insert(logFile);
                savedFile.setFilePath("******");
                return savedFile;
            }
            sign = md5 + sha1 + DigestUtils.md5Hex(String.valueOf(fileSize));
        }*/

        String extName = name.substring(name.lastIndexOf(".") + 1);
        String fileName = StringUtil.guid() + "." + extName;
        String filePath = BvchFileUtil.filePath(uploadPath, subType, keyId);

        // inputStream上面读取后，不能直接用
        FileUtil.save(bytes, new FileOutputStream(filePath + fileName));

        LogFile logFile = new LogFile();
        if (extName.equalsIgnoreCase("jpg") || extName.equalsIgnoreCase("png")) {
            String thumbName = fileName.substring(0, fileName.lastIndexOf(".")) + "_thumb." + extName;
            ImageUtil.thumb(new ByteArrayInputStream(bytes), new FileOutputStream(filePath + thumbName), 400, 300);
            logFile.setThumbName(thumbName);
        }

        logFile.setFileId(StringUtil.guid());
        logFile.setFileType(fileType);
        logFile.setSubType(subType);
        logFile.setKeyId(keyId);
        logFile.setFileName(fileName);
        logFile.setOriginName(name);
        logFile.setShowName(showName);
        logFile.setFilePath(filePath);
        // fileDescribe
        logFile.setExtName(extName);
        // extType
        logFile.setFileSize(fileSize);
        // thumbName
        logFile.setImageWidth(0);
        logFile.setImageHeight(0);
        logFile.setFileMd5(md5);
        logFile.setFileSha1(sha1);
        logFile.setFileSign(sign);
        logFile.setFileSource(fileSource);
        // deleteTime
        // deleteUser
        logFile.setFileState("0000");
        // areaCode
        // appCode
        logFile.setManageTime(DateUtil.getDateDate());
        logFile.setManageUser(userIdAndName);

        logFileDao.insert(logFile);

        // 隐藏文件路径
        logFile.setFilePath("******");

        return logFile;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public LogFile rotate(LogFile logFile, String userIdAndName) {
        LogFile logFileSaved = logFileDao.selectByPrimaryKey(logFile.getFileId());
        if (logFileSaved != null) {
            String fileName = StringUtil.guid() + "." + logFileSaved.getExtName();
            String filePath = BvchFileUtil.filePath(uploadPath, logFile.getSubType(), logFile.getKeyId());
            try {
                ImageUtil.rotate(new FileInputStream(logFileSaved.getFilePath() + logFileSaved.getFileName()), new FileOutputStream(filePath + fileName), logFile.getAngle());
                String thumbName = fileName.substring(0, fileName.lastIndexOf(".")) + "_thumb." + logFileSaved.getExtName();
                ImageUtil.thumb(new FileInputStream(filePath + fileName), new FileOutputStream(filePath + thumbName), 400, 300);
                if (!StringUtils.isEmpty(recyclePath)) {
                    FileUtil.delete(new File(logFileSaved.getFilePath() + logFileSaved.getFileName()), new File(BvchFileUtil.filePath(recyclePath, logFile.getSubType(), logFile.getKeyId()), logFileSaved.getFileName()));
                    if (!StringUtils.isEmpty(logFileSaved.getThumbName())) {
                        FileUtil.delete(new File(logFileSaved.getFilePath() + logFileSaved.getThumbName()), new File(BvchFileUtil.filePath(recyclePath, logFile.getSubType(), logFile.getKeyId()), logFileSaved.getThumbName()));
                    }
                } else {
                    FileUtil.delete(new File(logFileSaved.getFilePath() + logFileSaved.getFileName()), null);
                    if (!StringUtils.isEmpty(logFileSaved.getThumbName())) {
                        FileUtil.delete(new File(logFileSaved.getFilePath() + logFileSaved.getThumbName()), null);
                    }
                }
                logFileSaved.setThumbName(thumbName);
                logFileSaved.setFilePath(filePath);
                logFileSaved.setFileName(fileName);
                logFileSaved.setModifyTime(DateUtil.getDateDate());
                logFileSaved.setManageTime(DateUtil.getDateDate());
                logFileSaved.setManageUser(userIdAndName);
                logFileDao.updateByPrimaryKey(logFileSaved);
            } catch (IOException e) {
                logger.error(e);
            }
        }
        return logFile;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public LogFile crop(LogFile logFile, String userIdAndName) {
        LogFile logFileSaved = logFileDao.selectByPrimaryKey(logFile.getFileId());
        if (logFileSaved != null) {
            String fileName = StringUtil.guid() + "." + logFileSaved.getExtName();
            String filePath = BvchFileUtil.filePath(uploadPath, logFile.getSubType(), logFile.getKeyId());
            try {
                ImageUtil.crop(new FileInputStream(logFileSaved.getFilePath() + logFileSaved.getFileName()), new FileOutputStream(filePath + fileName), logFile.getZ(), logFile.getX(), logFile.getY(), logFile.getW(), logFile.getH());
                String thumbName = fileName.substring(0, fileName.lastIndexOf(".")) + "_thumb." + logFileSaved.getExtName();
                ImageUtil.thumb(new FileInputStream(filePath + fileName), new FileOutputStream(filePath + thumbName), 400, 300);
                if (!StringUtils.isEmpty(recyclePath)) {
                    FileUtil.delete(new File(logFileSaved.getFilePath() + logFileSaved.getFileName()), new File(BvchFileUtil.filePath(recyclePath, logFile.getSubType(), logFile.getKeyId()), logFileSaved.getFileName()));
                    if (!StringUtils.isEmpty(logFileSaved.getThumbName())) {
                        FileUtil.delete(new File(logFileSaved.getFilePath() + logFileSaved.getThumbName()), new File(BvchFileUtil.filePath(recyclePath, logFile.getSubType(), logFile.getKeyId()), logFileSaved.getThumbName()));
                    }
                } else {
                    FileUtil.delete(new File(logFileSaved.getFilePath() + logFileSaved.getFileName()), null);
                    if (!StringUtils.isEmpty(logFileSaved.getThumbName())) {
                        FileUtil.delete(new File(logFileSaved.getFilePath() + logFileSaved.getThumbName()), null);
                    }
                }
                logFileSaved.setThumbName(thumbName);
                logFileSaved.setFilePath(filePath);
                logFileSaved.setFileName(fileName);
                logFileSaved.setModifyTime(DateUtil.getDateDate());
                logFileSaved.setManageTime(DateUtil.getDateDate());
                logFileSaved.setManageUser(userIdAndName);
                logFileDao.updateByPrimaryKey(logFileSaved);
            } catch (IOException e) {
                logger.error(e);
            }
        }
        return logFile;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int updateShowName(LogFile logFile) {
        return logFileDao.updateShowName(logFile);
    }

    @Override
    public LogFile selectById(String fileId) {
        return logFileDao.selectByPrimaryKey(fileId);
    }

    @Override
    public LogFile selectBySign(String sign) {
        Example example = new Example(LogFile.class);
        example.createCriteria().andEqualTo("fileSign", sign);
        List<LogFile> list = logFileDao.selectByExample(example);
        if (list != null && list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public int countBySign(String sign) {
        Example example = new Example(LogFile.class);
        example.createCriteria().andEqualTo("fileSign", sign).andEqualTo("fileState", "0000");
        return logFileDao.countByExample(example);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int deleteById(String fileId, String userIdAndName) {
        LogFile logFile = logFileDao.selectByPrimaryKey(fileId);
        if (logFile != null) {
            logFile.setDeleteTime(DateUtil.getDateDate());
            logFile.setDeleteUser(userIdAndName);
            int deleteCnt = logFileDao.deleteById(logFile);
            if (!StringUtils.isEmpty(recyclePath)) {
                FileUtil.delete(new File(logFile.getFilePath() + logFile.getFileName()), new File(BvchFileUtil.filePath(recyclePath, logFile.getSubType(), logFile.getKeyId()), logFile.getFileName()));
                if (!StringUtils.isEmpty(logFile.getThumbName())) {
                    FileUtil.delete(new File(logFile.getFilePath() + logFile.getThumbName()), new File(BvchFileUtil.filePath(recyclePath, logFile.getSubType(), logFile.getKeyId()), logFile.getThumbName()));
                }
            } else {
                FileUtil.delete(new File(logFile.getFilePath() + logFile.getFileName()), null);
                if (!StringUtils.isEmpty(logFile.getThumbName())) {
                    FileUtil.delete(new File(logFile.getFilePath() + logFile.getThumbName()), null);
                }
            }
            return deleteCnt;
        }
        return -1;
    }
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int deleteBySign(String sign) {
        Example example = new Example(LogFile.class);
        example.createCriteria().andEqualTo("fileSign", sign);
        return logFileDao.deleteByExample(example);
    }

    @Override
    public List<LogFile> getByKeyIdAndFileType(LogFile logFile) {
        Example example = new Example(LogFile.class);
        example.createCriteria().andEqualTo("keyId", logFile.getKeyId()).andEqualTo("fileType", logFile.getFileType()).andEqualTo("subType", logFile.getSubType());
        List<LogFile> logFileList = logFileDao.selectByExample(example);
        if(logFileList!=null&&logFileList.size()>0){
            return logFileList;
        }
        return null;
    }
}
