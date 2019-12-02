package com.bestvike.bvch.sequence;

/**
 * @Author: yinxunyang
 * @Description: 中间表主键生成序列接口类
 * @Date: 2019/9/11 14:30
 */
public interface ITradeSequenceService {
	Integer generateTradeXH();
	/**
	 * @Author: yinxunyang
	 * @Description: 日志表的主键
	 * @Date: 2019/9/12 9:52
	 */
	String generateLogId(String scopeBeginTime);
}
