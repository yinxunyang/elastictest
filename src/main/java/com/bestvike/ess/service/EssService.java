package com.bestvike.ess.service;

import com.bestvike.commons.support.ServiceException;
import java.io.InputStream;
import org.springframework.stereotype.Service;

/**
 * Created by jiankai on 2018/01/16.
 */
@Service
public interface EssService {

	/**
	 * 导入种子文件
	 * @param deviceId
	 * @param inputStream
	 * @param userIdAndName
	 * @return
	 * @throws ServiceException
	 */
	public int importEssSeedFile(String deviceId, InputStream inputStream, String userIdAndName) throws Exception;

	/**
	 * 动态令牌派发
	 * @param deviceSn
	 * @param userId
	 * @param devicePwd
	 * @param userIdAndName
	 * @throws ServiceException
	 */
	public void allot(String deviceSn, String userId, String devicePwd, String userIdAndName) throws ServiceException;

	/**
	 * 动态令牌收回
	 * @param deviceSn
	 * @param userIdAndName
	 * @throws ServiceException
	 */
	public void back(String deviceSn, String userIdAndName) throws ServiceException;

	/**
	 * 激活
	 * @param userId
	 * @param deviceSn
	 * @param devicePwd
	 * @param userIdAndName
	 * @throws ServiceException
	 */
	public void enable(String userId, String deviceSn, String devicePwd, String userIdAndName) throws ServiceException;

	/**
	 * 禁用
	 * @param deviceSn
	 * @param userIdAndName
	 * @throws ServiceException
	 */
	public void disable(String deviceSn, String userIdAndName) throws ServiceException;

	/**
	 * 解锁
	 * @param deviceSn
	 * @param userIdAndName
	 * @throws ServiceException
	 */
	public void unlock(String deviceSn, String userIdAndName) throws ServiceException;

	/**
	 * 挂失
	 * @param deviceSn
	 * @param userIdAndName
	 * @throws ServiceException
	 */
	public void lost(String deviceSn, String userIdAndName) throws ServiceException;

	/**
	 * 解挂
	 * @param deviceSn
	 * @param devicePwd
	 * @param userIdAndName
	 * @throws ServiceException
	 */
	public void unlost(String deviceSn, String devicePwd, String userIdAndName) throws ServiceException;

	/**
	 * 同步
	 * @param deviceId
	 * @param deviceSn
	 * @param firstDevicePwd
	 * @param secondDevicePwd
	 * @throws ServiceException
	 */
	public void sync(String deviceId, String deviceSn, String firstDevicePwd, String secondDevicePwd) throws ServiceException;
}
