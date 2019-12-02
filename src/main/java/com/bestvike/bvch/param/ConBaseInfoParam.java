package com.bestvike.bvch.param;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: yinxunyang
 * @Description: 合同信息表参数类
 * @Date: 2019/9/9 18:38
 */
@Getter
@Setter
public class ConBaseInfoParam {
	/**
	 * 主键
	 */
	private String sysGuid;
	/**
	 * 是否迁移到tradeRecode表，Y是，N否
	 */
	private String isMergeTrade;

	/**
	 * 日志表logData的ID
	 */
	private String logId;
	/**
	 * 备案状态
	 */
	private String constractState;
	/**
	 * 最多查询的条数
	 */
	private Integer conBaseNum;
	/**
	 * 取数范围，开始时间
	 */
	private String scopeBeginTime;
	/**
	 * 取数范围，结束时间
	 */
	private String scopeEndTime;
	/**
	 * 主附房标志 Y_是;N_否
	 */
	private String houseFlag;

	/**
	 * 房屋状态 0_正常
	 */
	private String houseState;
	/**
	 * 是否根据备案时间升序 byRecordTime  Y是；
	 */
	private String byRecordTime;
}
