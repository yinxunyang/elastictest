package com.bestvike.bvch.param;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: yinxunyang
 * @Description: 交易者性质参数类
 * @Date: 2019/9/9 18:38
 */
@Getter
@Setter
public class BuyXzParam {

	/**
	 * 交易者证件号码
	 */
	private String buyCertNo;
	/**
	 * 交易者性质
	 */
	private String buyXz;
	/**
	 * 交易者性质编码
	 */
	private String buyXzBm;
}
