package com.bestvike.bvch.param;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: yinxunyang
 * @Description: 交易者证件名称参数类
 * @Date: 2019/9/9 18:38
 */
@Getter
@Setter
public class BuyCertTypeParam {
	/**
	 * 交易者证件名称
	 */
	private String buyCertType;
	/**
	 * 交易者证件名称编码
	 */
	private String buyCertTypeBm;
}
