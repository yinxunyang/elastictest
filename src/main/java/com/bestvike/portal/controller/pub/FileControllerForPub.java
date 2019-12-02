package com.bestvike.portal.controller.pub;

import com.bestvike.commons.util.FileUtil;
import com.bestvike.portal.controller.BaseController;
import com.bestvike.portal.data.LogFile;
import com.bestvike.portal.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lihua on 2016/9/30.
 */
@RestController
@RequestMapping("/pub/file")
public class FileControllerForPub extends BaseController {
    @Autowired
    private FileService fileService;

    public FileControllerForPub() {
        super("91");
    }

    @RequestMapping(value="/upload", method = RequestMethod.POST)
    public LogFile upload(@RequestParam(name = "name") String name, @RequestParam(name = "showName", required = false) String showName, @RequestParam(name = "fileType", required = false) String fileType, @RequestParam(name = "subType", required = false) String subType,
                          @RequestParam(name = "keyId", required = false) String keyId, @RequestParam(name = "fileSource", required = false) String fileSource, HttpServletRequest httpServletRequest) throws IOException {
        return fileService.upload(httpServletRequest.getInputStream(), name, showName, fileType, subType, keyId, fileSource, super.getUserIdAndName());
    }

    @RequestMapping(value="/info/{fileId}", method = RequestMethod.GET)
    public LogFile fileInfo(@PathVariable("fileId") String fileId) {
        if (!StringUtils.isEmpty(fileId)) {
            return fileService.selectById(fileId);
        }
        return null;
    }

    @RequestMapping(value="/{fileId}", method = RequestMethod.GET)
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
}
