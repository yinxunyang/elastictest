package com.bestvike.portal.security.base;

import com.bestvike.commons.support.ServiceException;
import com.bestvike.portal.dao.SysUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import cn.com.higinet.ss.trans.TokenService;
import com.bestvike.ess.service.EssService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created by lihua on 2017/5/3.
 */
public class CustomEncoder implements PasswordEncoder {

	protected Log logger = LogFactory.getLog(this.getClass());

	@Override
	public String encode(CharSequence rawPassword) {
		return new BCryptPasswordEncoder().encode(rawPassword);
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		if (!StringUtils.isEmpty(encodedPassword) && encodedPassword.startsWith("passtoken:")) {
			String devicePwd = encodedPassword.split(":")[1];
			return rawPassword.toString().equals(devicePwd);
		}
		return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
	}
}
