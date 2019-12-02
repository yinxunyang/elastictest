package com.bestvike.rfis.dao;

import org.springframework.stereotype.Repository;

/**
 * @Author: yinxunyang
 * @Description: 序列生成dao层
 * @Date: 2019/9/11 14:35
 */
@Repository
public interface SequenceDao {
	/**
	 * @Author: yinxunyang
	 * @Description: 根据序列生成中间表的主键
	 * @Date: 2019/9/11 14:38
	 */
	String generateTradeXH();
	/**
	 * @Author: yinxunyang
	 * @Description: 日志表的主键
	 * @Date: 2019/9/12 10:11
	 */
	String generateLogId();
}
