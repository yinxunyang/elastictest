package com.bestvike.bvch.param;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: yinxunyang
 * @Description: 付款类型参数类
 * @Date: 2019/9/9 18:38
 */
@Getter
@Setter
public class PayTypeParam {
	/**
	 * 付款类型
	 */
	private String payType;
	/**
	 * 付款类型编码
	 */
	private String payTypeBM;
	/**
	 * 付款方式
	 */
	private String payWay;
	/**
	 * 付款方式编码
	 */
	private String payWayBM;
}
