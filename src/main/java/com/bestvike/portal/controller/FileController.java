package com.bestvike.portal.controller;

import com.bestvike.commons.util.FileUtil;
import com.bestvike.portal.data.LogFile;
import com.bestvike.portal.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by lihua on 2016/9/30.
 */
@RestController
public class FileController extends BaseController {
    @Autowired
    private FileService fileService;

    public FileController() {
        super("91");
    }

    @RequestMapping(value="/api/file/upload", method = RequestMethod.POST)
    public LogFile upload(@RequestParam(name = "name") String name, @RequestParam(name = "showName", required = false) String showName, @RequestParam(name = "fileType", required = false) String fileType, @RequestParam(name = "subType", required = false) String subType,
                          @RequestParam(name = "keyId", required = false) String keyId, @RequestParam(name = "fileSource", required = false) String fileSource, HttpServletRequest httpServletRequest) throws IOException {
        return fileService.upload(httpServletRequest.getInputStream(), name, showName, fileType, subType, keyId, fileSource, super.getUserIdAndName());
    }

    @RequestMapping(value="/api/file/formUpload", method = RequestMethod.POST)
    public LogFile formUpload(@RequestParam MultipartFile file) throws IOException {
        return fileService.upload(file.getInputStream(), file.getName(),  null,null, null, null, null, super.getUserIdAndName());
    }

    @RequestMapping(value="/api/file/rotate", method = RequestMethod.PUT)
    public LogFile rotate(@RequestBody LogFile logFile) {
        return fileService.rotate(logFile, super.getUserIdAndName());
    }

    @RequestMapping(value="/api/file/crop", method = RequestMethod.PUT)
    public LogFile crop(@RequestBody LogFile logFile) {
        return fileService.crop(logFile, super.getUserIdAndName());
    }

    @RequestMapping(value="/api/file/updateShowName", method = RequestMethod.PUT)
    public int updateShowName(@RequestBody LogFile logFile) {
        return fileService.updateShowName(logFile);
    }

    @RequestMapping(value="/api/file/download", method = RequestMethod.GET)
    public void download1(HttpServletRequest request, HttpServletResponse response, @RequestParam("fileName") String fileName,
                          @RequestParam(name = "originName", required = false) String originName) throws IOException {
        // FileUtil.download(request, response, fileName, originName);
        if (!StringUtils.isEmpty(originName)) {
            FileUtil.download(request, response, fileName, originName);
        } else {
            FileUtil.download(request, response, fileName);
        }
    }

    @RequestMapping(value="/api/file/template", method = RequestMethod.GET)
    public void template(HttpServletRequest request, HttpServletResponse response, String fileName) throws IOException {
        FileUtil.download(request, response, FileUtil.getInputStream(templatePath + File.separator + fileName), fileName);
    }

    @RequestMapping(value="/api/file/{fileId}", method = RequestMethod.GET)
    public void download2(HttpServletRequest request, HttpServletResponse response, @PathVariable("fileId") String fileId,
                          @RequestParam(name = "name", required = false) String name) throws IOException {
        if (!StringUtils.isEmpty(fileId)) {
            LogFile logFile = fileService.selectById(fileId);
            if (logFile != null) {
                if (StringUtils.isEmpty(name)) {
                    name = logFile.getOriginName();
                }
                FileUtil.download(request, response, logFile.getFilePath() + logFile.getFileName(), name);
            }
        }
    }
    @RequestMapping(value="/api/thumb/{fileId}", method = RequestMethod.GET)
    public void thumbDownload(HttpServletRequest request, HttpServletResponse response, @PathVariable("fileId") String fileId) throws IOException {
        if (!StringUtils.isEmpty(fileId)) {
            LogFile logFile = fileService.selectById(fileId);
            if (logFile != null) {
                FileUtil.download(request, response, logFile.getFilePath() + logFile.getThumbName());
            }
        }
    }

    @RequestMapping(value="/api/file/{fileId}", method = RequestMethod.DELETE)
    public void deleteFile(@PathVariable("fileId") String fileId) throws IOException {
        if (!StringUtils.isEmpty(fileId)) {
            LogFile logFile = fileService.selectById(fileId);
            fileService.deleteById(fileId, super.getUserIdAndName());
            if (logFile != null) {
                int cnt = fileService.countBySign(logFile.getFileSign());
                if (cnt == 0) {
                    // 没有使用，将文件删除
                }
            }
        }
    }
}
