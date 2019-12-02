package com.bestvike.portal.controller.app;

import com.bestvike.commons.util.FileUtil;
import com.bestvike.portal.controller.BaseController;
import com.bestvike.portal.data.LogFile;
import com.bestvike.portal.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by lihua on 2016/9/30.
 */
@RestController
@RequestMapping("/app/file")
public class FileControllerForApp extends BaseController {
    @Autowired
    private FileService fileService;

    public FileControllerForApp() {
        super("91");
    }

    @RequestMapping(value="/upload", method = RequestMethod.POST)
    public LogFile upload(@RequestParam(name = "name") String name, @RequestParam(name = "showName", required = false) String showName, @RequestParam(name = "fileType", required = false) String fileType, @RequestParam(name = "subType", required = false) String subType,
                          @RequestParam(name = "keyId", required = false) String keyId, @RequestParam(name = "fileSource", required = false) String fileSource, HttpServletRequest httpServletRequest) throws IOException {
        return fileService.upload(httpServletRequest.getInputStream(), name, showName, fileType, subType, keyId, fileSource, super.getUserIdAndName());
    }

    @RequestMapping(value="/formUpload", method = RequestMethod.POST)
    public LogFile formUpload(@RequestParam(name = "name") String name, MultipartHttpServletRequest request) throws IOException {
        List<MultipartFile> file = request.getFiles("files");
        if (file != null && file.size() > 0) {
            LogFile logFile = fileService.upload(file.get(0).getInputStream(), name, null,null, null, null, null, super.getUserIdAndName());
            if (logFile != null) {
                logFile.setViewUrl("/app/file/" + logFile.getFileSign());
                logFile.setDeleteUrl("/app/file/" + logFile.getFileSign());
            }
            return logFile;
        }
        return null;
    }

    @RequestMapping(value="/{fileId}", method = RequestMethod.GET)
    public void download(HttpServletRequest request, HttpServletResponse response, @PathVariable("fileId") String fileId,
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

    @RequestMapping(value="/{fileId}", method = RequestMethod.DELETE)
    public int delete(@PathVariable("fileId") String fileId) {
        if (!StringUtils.isEmpty(fileId)) {
            // TODO:
            return fileService.deleteById(fileId, null);
        }
        return 0;
    }
}
