package com.bestvike.ess.service.impl;

import cn.com.higinet.ss.bean.Result;
import cn.com.higinet.ss.trans.TokenService;
import cn.com.higinet.token.lg.LgToken;
import cn.com.higinet.token.lg.LgTokenDriver;
import cn.com.higinet.token.lg.impl.LgTokenDriverTESM3Impl;
import cn.com.higinet.token.lg.impl.LgTokenDriverTSM3Impl;
import com.bestvike.commons.support.ServiceException;
import com.bestvike.commons.util.DateUtil;
import com.bestvike.ess.dao.EssSSTokenDao;
import com.bestvike.ess.data.EssSSToken;
import com.bestvike.ess.service.EssService;
import com.bestvike.portal.dao.EssAllotDao;
import com.bestvike.portal.data.EssAllot;
import com.bestvike.portal.service.BaseService;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.mybatis.mapper.entity.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jiankai on 2018/01/16.
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class EssServiceImpl extends BaseService implements EssService {

	@Autowired
	private EssAllotDao essAllotDao;
	@Autowired
	private EssSSTokenDao essSSTokenDao;
	@Autowired
	private TokenService tokenService;

	@Override
	public int importEssSeedFile(String deviceId, InputStream inputStream, String userIdAndName) throws Exception {
		SAXReader reader = new SAXReader();
		org.dom4j.Document document = reader.read(inputStream);
		String seedstr = "";
		String birth = "";
		String death = "";
		String deviceSn = "";
		int index = 0;
		Element root = document.getRootElement();
		Iterator<?> deviceIter = root.elementIterator("Device"); // 获取根节点下的子节点Device
		// 遍历head节点
		while (deviceIter.hasNext()) {
			Element deviceElement = (Element) deviceIter.next();
			Element secretElement = deviceElement.element("Secret");
			deviceSn = secretElement.attributeValue("SecretId");
			Iterator<?> dataIter = secretElement.elementIterator("Data");
			while (dataIter.hasNext()) {
				Element dataElement = (Element) dataIter.next();
				if ("SECRET".equals(dataElement.attributeValue("Name"))) {
					seedstr = dataElement.elementText("Value");
				}
				if ("DATE".equals(dataElement.attributeValue("Name"))) {
					birth = ((Element) dataElement.elements("Value").get(0)).getText();
					death = ((Element) dataElement.elements("Value").get(1)).getText();
				}
			}
			Properties conf = new Properties();
			conf.put("elimit", "10");
			conf.put("stepsize", "60");
			LgTokenDriver driver = null;
			if ("1101".equals(deviceId)) { // 时间型
				driver = new LgTokenDriverTSM3Impl(conf);
			} else if ("1102".equals(deviceId)) { // 时间事件型
				driver = new LgTokenDriverTESM3Impl(conf);
			}
			String tkPrivateData = getTkPrivateData(driver, deviceSn, seedstr, birth, death, "");
			Example example = new Example(EssAllot.class);
			example.createCriteria().andEqualTo("deviceSn", deviceSn).andEqualTo("deviceId", deviceId);
			List<EssAllot> listEssAllot = essAllotDao.selectByExample(example);
			if (listEssAllot == null || listEssAllot.size() == 0) {
				EssAllot essAllot = new EssAllot();
				essAllot.setState("9");
				essAllot.setDeviceSn(deviceSn);
				essAllot.setDeviceId(deviceId);
				essAllot.setLastModifyDate(DateUtil.getDateDate());
				essAllot.setLastModifyUser(userIdAndName);
				essAllotDao.insert(essAllot);
			}
			example = new Example(EssSSToken.class);
			example.createCriteria().andEqualTo("deviceSn", deviceSn).andEqualTo("deviceId", deviceId);
			List<EssSSToken> listEssSSToken = essSSTokenDao.selectByExample(example);
			if (listEssSSToken == null || listEssSSToken.size() == 0) {
				EssSSToken essSSToken = new EssSSToken();
				essSSToken.setDeviceSn(deviceSn);
				essSSToken.setDeviceId(deviceId);
				essSSToken.setTkPrivateData(tkPrivateData);
				essSSToken.setTkSpecialData(deviceSn);
				essSSToken.setTkBirth(new Date(Long.valueOf(birth) * 1000));
				essSSToken.setTkLostFlag("0");
				essSSToken.setTkLockFlag("0");
				essSSToken.setTkEnabledFlag("0");
				essSSToken.setTkAssignedFlag("0");
				essSSToken.setTkCancelFlag("0");
				essSSToken.setTkDeathDate(new Date(Long.valueOf(death) * 1000));
				essSSToken.setTkImportDate(new Date());
				essSSToken.setTkMethod("0");
				essSSToken.setTkPinFlag(BigDecimal.ZERO);
				essSSTokenDao.insert(essSSToken);
				index++;
			}
		}
		return index;
	}

	/**
	 * 生成种子密文
	 */
	private static String getTkPrivateData(LgTokenDriver driver, String deviceSn, String seedstr, String birth, String death, String macstr) {
		LgToken token = driver.retrieveToken(deviceSn, seedstr, birth, death, macstr);
		byte[] pdata = new byte[driver.getPdataLength()];
		token.encPdata(pdata);
		return new String(pdata);
	}

	/**
	 * 派发
	 */
	@Override
	public void allot(String deviceSn, String userId, String devicePwd, String userIdAndName) throws ServiceException {
		Example example = new Example(EssAllot.class);
		example.createCriteria().andEqualTo("deviceSn", deviceSn).andNotEqualTo("state", "S"); // 不是停用状态
		List<EssAllot> listEssAllot = essAllotDao.selectByExample(example);
		if (listEssAllot == null || listEssAllot.size() == 0) {
			throw new ServiceException("99", "动态令牌种子信息不存在");
		}
		EssAllot essAllot = listEssAllot.get(0);
		if (StringUtils.isEmpty(essAllot.getUserId())) {
			logger.info("----------" + deviceSn + "：当前设备尚未认证");
			// 查询当前用户是否绑定令牌
			example = new Example(EssAllot.class);
			example.createCriteria().andEqualTo("userId", userId).andIn("state", Arrays.asList("0", "L", "M")); // 当前用户已绑定的令牌（正常、锁定、挂失）
			listEssAllot = essAllotDao.selectByExample(example);
			if (listEssAllot == null || listEssAllot.size() == 0) {
				essAllot.setUserId(userId);
				essAllot.setState("0");
				essAllot.setLastModifyDate(DateUtil.getDateDate());
				essAllot.setLastModifyUser(userIdAndName);
				essAllotDao.updateByPrimaryKey(essAllot);
				// 查看当前令牌状态
				try {
					Result result = tokenService.query(essAllot.getDeviceId(), essAllot.getDeviceSn());
					if (!"1".equals(String.valueOf(result.getAttribute(19)))) { // 未激活
						result = tokenService.enable(essAllot.getDeviceId(), deviceSn, devicePwd, null);
						String resultCode = String.valueOf(result.getRetCode());
						if (!EssAllot.ESS_SUCCESS_RESULT_CODE.equals(resultCode)) {
							throw new ServiceException("99", "[" + resultCode + "]" + result.getAttribute(7)); // 激活失败，抛出异常
						}
					}
				} catch (Exception e) {
					throw new ServiceException("99", "调用令牌查询接口异常:" + e.getMessage());
				}
			} else {
				essAllot = listEssAllot.get(0);
				if ("0".equals(essAllot.getState())) {
					throw new ServiceException("99", "当前用户有正常状态的令牌，请先停用后再分配新的令牌！");
				}
				if ("L".equals(essAllot.getState())) {
					throw new ServiceException("99", "当前用户有锁定状态的令牌，解锁后即可正常使用，不用重新分配新令牌！");
				}
				if ("M".equals(essAllot.getState())) {
					throw new ServiceException("99", "当前用户有已挂失状态的令牌，请先将已挂失令牌停用后再分配新的令牌！");
				}
			}
		} else {
			throw new ServiceException("99", "动态令牌已分配");
		}
	}

	/**
	 * 动态令牌收回
	 */
	@Override
	public void back(String deviceSn, String userIdAndName) throws ServiceException {
		Example example = new Example(EssAllot.class);
		example.createCriteria().andEqualTo("deviceSn", deviceSn).andNotEqualTo("state", "S"); // 停用的除外
		List<EssAllot> listEssAllot = essAllotDao.selectByExample(example);
		if (listEssAllot == null || listEssAllot.size() == 0) {
			throw new ServiceException("99", "动态令牌种子信息不存在");
		}
		EssAllot essAllot = listEssAllot.get(0);
		if (StringUtils.isEmpty(essAllot.getUserId())) {
			logger.info("----------" + deviceSn + "：当前设备已回收");
			throw new ServiceException("99", "动态令牌已回收");
		} else {
			essAllot.setUserId("");
			essAllot.setState("9");
			essAllot.setLastModifyDate(DateUtil.getDateDate());
			essAllot.setLastModifyUser(userIdAndName);
			essAllotDao.updateByPrimaryKey(essAllot);
		}
	}

	/**
	 * 动态令牌激活
	 */
	@Override
	public void enable(String userId, String deviceSn, String devicePwd, String userIdAndName) throws ServiceException {
		Example example = new Example(EssAllot.class);
		example.createCriteria().andEqualTo("deviceSn", deviceSn).andIn("state", Arrays.asList("9", "S")); // 新派发或停用的
		List<EssAllot> listEssAllot = essAllotDao.selectByExample(example);
		if (listEssAllot == null || listEssAllot.size() == 0) {
			throw new ServiceException("99", "动态令牌种子信息不存在");
		}
		EssAllot essAllot = listEssAllot.get(0);
		// 查询激活状态
		try {
			Result result = tokenService.query(essAllot.getDeviceId(), deviceSn);
			if ("1".equals(String.valueOf(result.getAttribute(19)))) { // 已激活
				essAllot.setState("0");
				essAllot.setLastModifyDate(DateUtil.getDateDate());
				essAllot.setLastModifyUser(userIdAndName);
				essAllotDao.updateByPrimaryKey(essAllot);
				return;
			}
			result = tokenService.enable(essAllot.getDeviceId(), deviceSn, devicePwd, null);
			String resultCode = String.valueOf(result.getRetCode());
			if (!EssAllot.ESS_SUCCESS_RESULT_CODE.equals(resultCode)) {
				throw new ServiceException("99", "[" + resultCode + "]" + result.getAttribute(7)); // 返回错误码及错误信息
			} else {
				essAllot.setState("9");
				essAllot.setLastModifyDate(DateUtil.getDateDate());
				essAllot.setLastModifyUser(userIdAndName);
				essAllotDao.updateByPrimaryKey(essAllot);
				return;
			}
		} catch (Exception e) {
			throw new ServiceException("99", "调用令牌enable接口异常:" + e.getMessage());
		}
	}

	/**
	 * 动态令牌停用
	 */
	@Override
	public void disable(String deviceSn, String userIdAndName) throws ServiceException {
		Example example = new Example(EssAllot.class);
		example.createCriteria().andEqualTo("deviceSn", deviceSn).andIn("state", Arrays.asList("0", "L", "M")); // 停用状态除外
		List<EssAllot> listEssAllot = essAllotDao.selectByExample(example);
		if (listEssAllot == null || listEssAllot.size() == 0) {
			throw new ServiceException("99", "动态令牌种子信息不存在");
		}
		EssAllot essAllot = listEssAllot.get(0);
		// 查询激活状态
		try {
			Result result = tokenService.query(essAllot.getDeviceId(), deviceSn);
			if ("0".equals(String.valueOf(result.getAttribute(19)))) { // 未激活
				essAllot.setState("S");
				essAllot.setLastModifyDate(DateUtil.getDateDate());
				essAllot.setLastModifyUser(userIdAndName);
				essAllotDao.updateByPrimaryKey(essAllot);
				return;
			}
			result = tokenService.disable(essAllot.getDeviceId(), essAllot.getDeviceSn());
			String resultCode = String.valueOf(result.getRetCode());
			if (!EssAllot.ESS_SUCCESS_RESULT_CODE.equals(resultCode)) {
				throw new ServiceException("99", "[" + resultCode + "]" + result.getAttribute(7)); // 返回错误码及错误信息
			} else {
				essAllot.setState("S");
				essAllot.setLastModifyDate(DateUtil.getDateDate());
				essAllot.setLastModifyUser(userIdAndName);
				essAllotDao.updateByPrimaryKey(essAllot);
				return;
			}
		} catch (Exception e) {
			throw new ServiceException("99", "调用令牌disable接口异常:" + e.getMessage());
		}
	}

	/**
	 * 解锁
	 */
	@Override
	public void unlock(String deviceSn, String userIdAndName) throws ServiceException {
		Example example = new Example(EssAllot.class);
		example.createCriteria().andEqualTo("deviceSn", deviceSn).andIn("state", Arrays.asList("0", "L")); // 挂失状态除外
		List<EssAllot> listEssAllot = essAllotDao.selectByExample(example);
		if (listEssAllot == null || listEssAllot.size() == 0) {
			throw new ServiceException("99", "认证设备信息获取失败");
		}
		EssAllot essAllot = listEssAllot.get(0);
		Result result = null;
		try {
			result = tokenService.query(essAllot.getDeviceId(), deviceSn);
			if ("0".equals(String.valueOf(result.getAttribute(17)))) { // 当前设备处于正常状态
				essAllot.setState("0");
				essAllot.setLastModifyDate(DateUtil.getDateDate());
				essAllot.setLastModifyUser(userIdAndName);
				essAllotDao.updateByPrimaryKey(essAllot);
				return;
			}
			result = tokenService.unlock(essAllot.getDeviceId(), deviceSn);
			String resultCode = String.valueOf(result.getRetCode());
			if (!EssAllot.ESS_SUCCESS_RESULT_CODE.equals(resultCode)) {
				throw new ServiceException("99", "[" + resultCode + "]" + result.getAttribute(7)); // 返回错误码及错误信息
			} else {
				essAllot.setState("0");
				essAllot.setLastModifyDate(DateUtil.getDateDate());
				essAllot.setLastModifyUser(userIdAndName);
				essAllotDao.updateByPrimaryKey(essAllot);
				return;
			}
		} catch (Exception e) {
			throw new ServiceException("99", "调用令牌unlock接口异常:" + e.getMessage());
		}
	}

	/**
	 * 挂失
	 */
	@Override
	public void lost(String deviceSn, String userIdAndName) throws ServiceException {
		Example example = new Example(EssAllot.class);
		example.createCriteria().andEqualTo("deviceSn", deviceSn).andIn("state", Arrays.asList("0", "L")); // 挂失状态除外
		List<EssAllot> listEssAllot = essAllotDao.selectByExample(example);
		if (listEssAllot == null || listEssAllot.size() == 0) {
			throw new ServiceException("99", "认证设备信息获取失败");
		}
		EssAllot essAllot = listEssAllot.get(0);
		Result result = null;
		try {
			result = tokenService.query(essAllot.getDeviceId(), essAllot.getDeviceSn());
			if ("1".equals(String.valueOf(result.getAttribute(16)))) { // 已挂失
				essAllot.setState("M");
				essAllot.setLastModifyDate(DateUtil.getDateDate());
				essAllot.setLastModifyUser(userIdAndName);
				essAllotDao.updateByPrimaryKey(essAllot);
				return;
			}
			result = tokenService.lost(essAllot.getDeviceId(), deviceSn);
			String resultCode = String.valueOf(result.getRetCode());
			if (!EssAllot.ESS_SUCCESS_RESULT_CODE.equals(resultCode)) {
				throw new ServiceException("99", "[" + resultCode + "]" + result.getAttribute(7)); // 返回错误码及错误信息
			} else {
				essAllot.setState("M");
				essAllot.setLastModifyDate(DateUtil.getDateDate());
				essAllot.setLastModifyUser(userIdAndName);
				essAllotDao.updateByPrimaryKey(essAllot);
				return;
			}
		} catch (Exception e) {
			throw new ServiceException("99", "调用令牌lost接口异常:" + e.getMessage());
		}
	}

	/**
	 * 解挂
	 * @param deviceSn
	 * @param devicePwd
	 * @throws ServiceException
	 */
	@Override
	public void unlost(String deviceSn, String devicePwd, String userIdAndName) throws ServiceException {
		Example example = new Example(EssAllot.class);
		example.createCriteria().andEqualTo("deviceSn", deviceSn).andNotEqualTo("state", "S"); // 除已停用
		List<EssAllot> listEssAllot = essAllotDao.selectByExample(example);
		if (listEssAllot == null || listEssAllot.size() == 0) {
			throw new ServiceException("99", "认证设备信息获取失败");
		}
		EssAllot essAllot = listEssAllot.get(0);
		Result result = null;
		try {
			result = tokenService.query(essAllot.getDeviceId(), essAllot.getDeviceSn());
			if ("0".equals(String.valueOf(result.getAttribute(16)))) { // 正常状态
				essAllot.setState("0");
				essAllot.setLastModifyDate(DateUtil.getDateDate());
				essAllot.setLastModifyUser(userIdAndName);
				essAllotDao.updateByPrimaryKey(essAllot);
				return;
			}
			result = tokenService.unlost(essAllot.getDeviceId(), deviceSn, devicePwd, null);
			String resultCode = String.valueOf(result.getRetCode());
			if (!EssAllot.ESS_SUCCESS_RESULT_CODE.equals(resultCode)) {
				throw new ServiceException("99", "[" + resultCode + "]" + result.getAttribute(7)); // 返回错误码及错误信息
			} else {
				essAllot.setState("0");
				essAllot.setLastModifyDate(DateUtil.getDateDate());
				essAllot.setLastModifyUser(userIdAndName);
				essAllotDao.updateByPrimaryKey(essAllot);
				return;
			}
		} catch (Exception e) {
			throw new ServiceException("99", "调用令牌unlost接口异常:" + e.getMessage());
		}
	}

	/**
	 * 同步
	 * @param deviceId
	 * @param deviceSn
	 * @param firstDevicePwd
	 * @param secondDevicePwd
	 * @throws ServiceException
	 */
	@Override
	public void sync(String deviceId, String deviceSn, String firstDevicePwd, String secondDevicePwd) throws ServiceException {
		Result result = null;
		try {
			result = tokenService.sync(deviceId, deviceSn, firstDevicePwd, secondDevicePwd);
			String resultCode = String.valueOf(result.getRetCode());
			if (!EssAllot.ESS_SUCCESS_RESULT_CODE.equals(resultCode)) {
				throw new ServiceException("99", "[" + resultCode + "]" + result.getAttribute(7)); // 返回错误码及错误信息
			} else {
				return;
			}
		} catch (Exception e) {
			throw new ServiceException("99", "调用令牌sync接口异常:" + e.getMessage());
		}
	}
}
