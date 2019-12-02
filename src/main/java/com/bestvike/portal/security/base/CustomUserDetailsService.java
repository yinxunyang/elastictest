
package com.bestvike.portal.security.base;

import cn.com.higinet.ss.bean.Result;
import cn.com.higinet.ss.trans.TokenService;
import com.bestvike.bvch.BvchGlobal;
import com.bestvike.commons.support.UsernameEmptyException;
import com.bestvike.commons.support.ValidateCodeInvalidException;
import com.bestvike.commons.util.DateUtil;
import com.bestvike.portal.dao.EssAllotDao;
import com.bestvike.portal.dao.SysUserDao;
import com.bestvike.portal.data.EssAllot;
import com.bestvike.portal.data.SysUser;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.mapper.entity.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	protected Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private HttpSession httpSession;
	@Autowired
	private HttpServletRequest httpServletRequest;
	@Autowired
	private EssAllotDao essAllotDao;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private SysUserDao sysUserDao;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// 判断验证码
		String validateCodeStore = (String) httpSession.getAttribute("validateCode");
		if (!StringUtils.isEmpty(validateCodeStore)) {
			String validateCode = httpServletRequest.getHeader("validateCode");
			if (StringUtils.isEmpty(validateCode)) {
				throw new ValidateCodeInvalidException("请输入验证码");
			}
			if (!validateCode.equalsIgnoreCase(validateCodeStore)) {
				logger.warn("validateCode：" + validateCode + "，stored：" + validateCodeStore);
				throw new ValidateCodeInvalidException("请输入正确的验证码");
			}
		}
		String loginType = httpServletRequest.getHeader("loginType");
		if (StringUtils.isEmpty(loginType) || loginType.equals("password")) {
			if (StringUtils.isEmpty(username)) {
				throw new UsernameEmptyException("用户名不能为空");
			}
			Example example = new Example(SysUser.class);
			example.createCriteria().andEqualTo("userId", username);
			example.or().andEqualTo("userAlias", username);
			List<SysUser> list = sysUserDao.selectByExample(example);
			if (list == null || list.size() != 1) {
				throw new UsernameNotFoundException("用户 " + username + " 不存在");
			}
			if (list.get(0).getUserState().equals(BvchGlobal.SYS_USER_STATE)) {
				throw new UsernameEmptyException("该用户未激活");
			}

			return new User(list.get(0).getUserId(), list.get(0).getUserPass(), AuthorityUtils.createAuthorityList("ROLE_USER"));
		} else if (loginType.equals("passtoken")) {
			// 动态密码牌
			if (StringUtils.isEmpty(username)) {
				throw new UsernameEmptyException("用户名不能为空");
			}
			String devicePwd = httpServletRequest.getHeader("devicePwd");
			Example example = new Example(SysUser.class);
			example.createCriteria().andEqualTo("userId", username);
			example.or().andEqualTo("userAlias", username);
			List<SysUser> list = sysUserDao.selectByExample(example);
			if (list == null || list.size() != 1) {
				throw new UsernameNotFoundException("用户 " + username + " 不存在");
			}
			SysUser sysUser = list.get(0);
            if (sysUser.getUserState().equals(BvchGlobal.SYS_USER_STATE)) {
                throw new UsernameEmptyException("该用户未激活");
            }

			example = new Example(EssAllot.class);
			example.createCriteria().andEqualTo("userId", list.get(0).getUserId()).andIn("state", Arrays.asList("0", "L")); // 挂失状态除外
			List<EssAllot> listEssAllot = essAllotDao.selectByExample(example);
			if (listEssAllot == null || listEssAllot.size() == 0) {
				throw new UsernameNotFoundException("用户暂未配置动态令牌信息");
			}
			EssAllot essAllot = listEssAllot.get(0);
			if ("L".equals(essAllot.getState())) {
				throw new UsernameEmptyException("当前设备已锁定，请持设备到主管单位解锁！");
			}
			Result result = null;
			try {
				result = tokenService.query(essAllot.getDeviceId(), essAllot.getDeviceSn());
			} catch (Exception e) {
				throw new UsernameEmptyException("认证设备查询接口调用异常：" + e.getMessage());
			}
			if ("1".equals(String.valueOf(result.getAttribute(17)))) { // 已锁定
				essAllot.setState("S");
				essAllot.setLastModifyDate(DateUtil.getDateDate());
				essAllot.setLastModifyUser(sysUser.getUserId() + "_" + sysUser.getUserName());
				essAllotDao.updateByPrimaryKey(essAllot);
				throw new UsernameEmptyException("认证设备已锁定，请持设备到主管单位解锁");
			}
			try {
				result = tokenService.verify(essAllot.getDeviceId(), essAllot.getDeviceSn(), devicePwd, null, null);
			} catch (Exception e) {
				throw new UsernameEmptyException("认证设备验证接口调用异常：" + e.getMessage());
			}
			String resultCode = String.valueOf(result.getRetCode());
			if (!EssAllot.ESS_SUCCESS_RESULT_CODE.equals(resultCode)) {
				throw new UsernameEmptyException("[" + resultCode + "]" + result.getAttribute(7)); // 返回错误码及错误信息
			} else {
				return new User(sysUser.getUserId(), "passtoken:" + devicePwd, AuthorityUtils.createAuthorityList("ROLE_USER"));
			}
		}
		throw new UsernameNotFoundException("登录方式异常");
	}
}
