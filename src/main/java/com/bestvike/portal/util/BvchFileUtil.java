package com.bestvike.portal.util;

import com.bestvike.bvch.BvchGlobal;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BvchFileUtil {
    public static String filePath(String basePath, String subType, String keyId) {
        File file = new File(basePath + File.separator + BvchGlobal.FILE_BUSI_PATH + File.separator + new SimpleDateFormat("yyyy" + File.separator + "MM" + File.separator + "dd").format(new Date()) + File.separator + subType + File.separator + keyId);
        if (!file.exists()) {
            file.mkdirs();
        }

        return file.getAbsolutePath() + File.separator;
    }
}
