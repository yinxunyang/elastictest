package com.bestvike.bvch;

import com.bestvike.commons.util.EncryptUtil;
import com.bestvike.frame.dao.DicUserInfoDao;
import com.bestvike.frame.data.DicUserInfo;
import com.bestvike.portal.dao.SysUserDao;
import com.bestvike.portal.data.SysUser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Component
public class BvchTask {
    public Log logger = LogFactory.getLog(getClass());

    @Autowired
    private DicUserInfoDao dicUserInfoDao;
    @Autowired
    private SysUserDao sysUserDao;

    //@Scheduled(cron = "0 0/5 * * * ?")
    public void task() {
        logger.info("bvch task start...");
        List<DicUserInfo> userList = dicUserInfoDao.getByCorpType();
        logger.info("userList size: " + userList.size());
        for (DicUserInfo dicUserInfo : userList) {
            SysUser sysUser = sysUserDao.getOneByUserId(dicUserInfo.getUserId());
            if(sysUser!=null){
                try {
                    if (dicUserInfo.getUserPass().equals(EncryptUtil.MD5Encode(BvchGlobal.User_Pass))) {
                        sysUser.setUserPass(new BCryptPasswordEncoder().encode(EncryptUtil.MD5Encode(BvchGlobal.User_Pass)));
                    }
                    /*if (dicUserInfo.getState().equals("0")) {
                        sysUser.setUserState("0000");
                    }
                    if (dicUserInfo.getState().equals("F")) {
                        sysUser.setUserState(BvchGlobal.SYS_USER_STATE);
                    }*/
                    sysUser.setUserName(dicUserInfo.getUserName());
                    sysUser.setUserAlias(dicUserInfo.getAliasName());
                    sysUserDao.updateByPrimaryKey(sysUser);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
