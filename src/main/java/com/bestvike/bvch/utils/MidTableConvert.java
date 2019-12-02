package com.bestvike.bvch.utils;

import com.bestvike.bvch.param.BuyCertTypeParam;
import com.bestvike.bvch.param.BuyXzParam;
import com.bestvike.bvch.param.FrameAttrParam;
import com.bestvike.bvch.param.HouseHoldParam;
import com.bestvike.bvch.param.HousePropParam;
import com.bestvike.bvch.param.HouseStructureParam;
import com.bestvike.bvch.param.HouseTypeParam;
import com.bestvike.bvch.param.HouseUseParam;
import com.bestvike.bvch.param.PayTypeParam;
import com.bestvike.bvch.param.RoomDirectionParam;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * @Author: yinxunyang
 * @Description: 中间表参数转换工具类
 * @Date: 2019/9/9 19:32
 */
@Configuration
public class MidTableConvert {
	/**
	 * payType抵押贷款分期付款
	 */
	public static final String MORTGAGE_PAYMENT_BY_INSTALMENTS = "抵押贷款分期付款";
	/**
	 * @Author: yinxunyang
	 * @Description: 交易者证件名称转换
	 * @Date: 2019/9/9 19:40
	 * @param: buyCertTypeParam
	 */
	public void buyCertTypeConvert(BuyCertTypeParam buyCertTypeParam) {
		String buyCertType = buyCertTypeParam.getBuyCertType() == null ? "99" : buyCertTypeParam.getBuyCertType();
		switch (buyCertType) {
			case "01":
				buyCertTypeParam.setBuyCertType("居民身份证");
				buyCertTypeParam.setBuyCertTypeBm("01");
				break;
			case "1":
				buyCertTypeParam.setBuyCertType("居民身份证");
				buyCertTypeParam.setBuyCertTypeBm("01");
				break;
			case "02":
				buyCertTypeParam.setBuyCertType("港澳台身份证");
				buyCertTypeParam.setBuyCertTypeBm("03");
				break;
			case "03":
				buyCertTypeParam.setBuyCertType("护照");
				buyCertTypeParam.setBuyCertTypeBm("04");
				break;
			case "04":
				buyCertTypeParam.setBuyCertType("户口簿");
				buyCertTypeParam.setBuyCertTypeBm("05");
				break;
			case "4":
				buyCertTypeParam.setBuyCertType("户口簿");
				buyCertTypeParam.setBuyCertTypeBm("05");
				break;
			case "05":
				buyCertTypeParam.setBuyCertType("军官证（士兵证）");
				buyCertTypeParam.setBuyCertTypeBm("06");
				break;
			case "5":
				buyCertTypeParam.setBuyCertType("军官证（士兵证）");
				buyCertTypeParam.setBuyCertTypeBm("06");
				break;
			case "06":
				buyCertTypeParam.setBuyCertType("组织机构代码");
				buyCertTypeParam.setBuyCertTypeBm("07");
				break;
			case "07":
				buyCertTypeParam.setBuyCertType("营业执照");
				buyCertTypeParam.setBuyCertTypeBm("08");
				break;
			case "7":
				buyCertTypeParam.setBuyCertType("营业执照");
				buyCertTypeParam.setBuyCertTypeBm("08");
				break;
			default:
				buyCertTypeParam.setBuyCertType("无");
				buyCertTypeParam.setBuyCertTypeBm("99");
				break;
		}
	}

	/**
	 * @Author: yinxunyang
	 * @Description: 户型居室转换
	 * @Date: 2019/9/9 19:51
	 * @param: houseHoldParam
	 */
	public void houseHoldConvert(HouseHoldParam houseHoldParam) {
		String houseHold = houseHoldParam.getHouseHold() == null ? "99" : houseHoldParam.getHouseHold();
		switch (houseHold) {
			case "1":
				houseHoldParam.setHouseHold("一居室");
				houseHoldParam.setHouseHoldBM("01");
				break;
			case "2":
				houseHoldParam.setHouseHold("二居室");
				houseHoldParam.setHouseHoldBM("02");
				break;
			case "3":
				houseHoldParam.setHouseHold("三居室");
				houseHoldParam.setHouseHoldBM("03");
				break;
			case "4":
				houseHoldParam.setHouseHold("四居室");
				houseHoldParam.setHouseHoldBM("04");
				break;
			case "5":
				houseHoldParam.setHouseHold("五居室");
				houseHoldParam.setHouseHoldBM("05");
				break;
			default:
				houseHoldParam.setHouseHold("三居室");
				houseHoldParam.setHouseHoldBM("03");
				break;
		}
	}

	/**
	 * @Author: yinxunyang
	 * @Description: 户型结构转换
	 * @Date: 2019/9/9 20:10
	 * @param: houseStructureParam
	 */
	public void houseStructureConvert(HouseStructureParam houseStructureParam) {
		String houseStructure = houseStructureParam.getHouseStructure() == null ? "99" : houseStructureParam.getHouseStructure();
		switch (houseStructure) {
			case "1":
				houseStructureParam.setHouseStructure("平层");
				houseStructureParam.setHouseStructureMB("01");
				break;
			case "2":
				houseStructureParam.setHouseStructure("错层");
				houseStructureParam.setHouseStructureMB("02");
				break;
			case "3":
				houseStructureParam.setHouseStructure("复式楼");
				houseStructureParam.setHouseStructureMB("03");
				break;
			case "4":
				houseStructureParam.setHouseStructure("跃层");
				houseStructureParam.setHouseStructureMB("04");
				break;
			default:
				houseStructureParam.setHouseStructure("平层");
				houseStructureParam.setHouseStructureMB("01");
				break;
		}
	}

	/**
	 * @Author: yinxunyang
	 * @Description: 房屋朝向转换
	 * @Date: 2019 /9/9 20:17
	 * @param: roomDirectionParam
	 */
	public void roomDirectionConvert(RoomDirectionParam roomDirectionParam) {
		String roomDirection = roomDirectionParam.getRoomDirection() == null ? "99" : roomDirectionParam.getRoomDirection();
		switch (roomDirection) {
			case "1":
				roomDirectionParam.setRoomDirection("南");
				roomDirectionParam.setRoomDirectionBM("03");
				break;
			case "2":
				roomDirectionParam.setRoomDirection("东南");
				roomDirectionParam.setRoomDirectionBM("06");
				break;
			case "3":
				roomDirectionParam.setRoomDirection("西南");
				roomDirectionParam.setRoomDirectionBM("08");
				break;
			case "4":
				roomDirectionParam.setRoomDirection("北");
				roomDirectionParam.setRoomDirectionBM("04");
				break;
			case "5":
				roomDirectionParam.setRoomDirection("东北");
				roomDirectionParam.setRoomDirectionBM("05");
				break;
			case "6":
				roomDirectionParam.setRoomDirection("西北");
				roomDirectionParam.setRoomDirectionBM("07");
				break;
			case "7":
				roomDirectionParam.setRoomDirection("东");
				roomDirectionParam.setRoomDirectionBM("01");
				break;
			case "8":
				roomDirectionParam.setRoomDirection("西");
				roomDirectionParam.setRoomDirectionBM("02");
				break;
			default:
				roomDirectionParam.setRoomDirection("南");
				roomDirectionParam.setRoomDirectionBM("03");
				break;
		}
	}

	/**
	 * @Author: yinxunyang
	 * @Description: 建筑结构转换
	 * @Date: 2019/9/10 9:59
	 * @param: frameAttrParam
	 */
	public void frameAttrParamConvert(FrameAttrParam frameAttrParam) {
		String frameAttr = frameAttrParam.getFrameAttr() == null ? "99" : frameAttrParam.getFrameAttr();
		switch (frameAttr) {
			case "1":
				frameAttrParam.setFrameAttr("钢结构");
				frameAttrParam.setFrameAttrBM("01");
				break;
			case "2":
				frameAttrParam.setFrameAttr("钢、钢筋混凝土结构");
				frameAttrParam.setFrameAttrBM("02");
				break;
			case "3":
				frameAttrParam.setFrameAttr("钢筋混凝土结构");
				frameAttrParam.setFrameAttrBM("03");
				break;
			case "4":
				frameAttrParam.setFrameAttr("混合结构");
				frameAttrParam.setFrameAttrBM("04");
				break;
			case "5":
				frameAttrParam.setFrameAttr("砖木结构");
				frameAttrParam.setFrameAttrBM("05");
				break;
			default:
				frameAttrParam.setFrameAttr("其他结构");
				frameAttrParam.setFrameAttrBM("06");
				break;
		}
	}

	/**
	 * @Author: yinxunyang
	 * @Description: 房屋用途转换
	 * @Date: 2019/9/10 8:29
	 * @param: houseUseParam
	 */
	public void houseUseConvert(HouseUseParam houseUseParam) {
		String houseUse = houseUseParam.getHouseUse() == null ? "99" : houseUseParam.getHouseUse();
		switch (houseUse) {
			case "11":
				houseUseParam.setHouseUse("成套住宅");
				houseUseParam.setHouseUseBM("002");
				break;
			case "12":
				houseUseParam.setHouseUse("非成套住宅");
				houseUseParam.setHouseUseBM("005");
				break;
			case "13":
				houseUseParam.setHouseUse("集体宿舍");
				houseUseParam.setHouseUseBM("006");
				break;
			case "14":
				houseUseParam.setHouseUse("别墅");
				houseUseParam.setHouseUseBM("003");
				break;
			case "15":
				houseUseParam.setHouseUse("高档公寓");
				houseUseParam.setHouseUseBM("004");
				break;
			case "21":
				houseUseParam.setHouseUse("工业");
				houseUseParam.setHouseUseBM("008");
				break;
			case "22":
				houseUseParam.setHouseUse("公共设施");
				houseUseParam.setHouseUseBM("009");
				break;
			case "23":
				houseUseParam.setHouseUse("铁路");
				houseUseParam.setHouseUseBM("010");
				break;
			case "24":
				houseUseParam.setHouseUse("民航");
				houseUseParam.setHouseUseBM("011");
				break;
			case "25":
				houseUseParam.setHouseUse("航运");
				houseUseParam.setHouseUseBM("012");
				break;
			case "26":
				houseUseParam.setHouseUse("公共运输");
				houseUseParam.setHouseUseBM("013");
				break;
			case "31":
				houseUseParam.setHouseUse("商业服务");
				houseUseParam.setHouseUseBM("016");
				break;
			case "32":
				houseUseParam.setHouseUse("经营");
				houseUseParam.setHouseUseBM("017");
				break;
			case "33":
				houseUseParam.setHouseUse("旅游");
				houseUseParam.setHouseUseBM("018");
				break;
			case "34":
				houseUseParam.setHouseUse("金融保险");
				houseUseParam.setHouseUseBM("019");
				break;
			case "35":
				houseUseParam.setHouseUse("电讯信息");
				houseUseParam.setHouseUseBM("020");
				break;
			case "41":
				houseUseParam.setHouseUse("教育");
				houseUseParam.setHouseUseBM("022");
				break;
			case "42":
				houseUseParam.setHouseUse("医疗卫生");
				houseUseParam.setHouseUseBM("023");
				break;
			case "43":
				houseUseParam.setHouseUse("科研");
				houseUseParam.setHouseUseBM("024");
				break;
			case "51":
				houseUseParam.setHouseUse("文化");
				houseUseParam.setHouseUseBM("026");
				break;
			case "52":
				houseUseParam.setHouseUse("新闻");
				houseUseParam.setHouseUseBM("027");
				break;
			case "53":
				houseUseParam.setHouseUse("娱乐");
				houseUseParam.setHouseUseBM("028");
				break;
			case "54":
				houseUseParam.setHouseUse("园林绿化");
				houseUseParam.setHouseUseBM("029");
				break;
			case "55":
				houseUseParam.setHouseUse("体育");
				houseUseParam.setHouseUseBM("030");
				break;
			case "60":
				houseUseParam.setHouseUse("办公");
				houseUseParam.setHouseUseBM("031");
				break;
			case "70":
				houseUseParam.setHouseUse("军事");
				houseUseParam.setHouseUseBM("032");
				break;
			case "81":
				houseUseParam.setHouseUse("涉外");
				houseUseParam.setHouseUseBM("034");
				break;
			case "82":
				houseUseParam.setHouseUse("宗教");
				houseUseParam.setHouseUseBM("035");
				break;
			case "83":
				houseUseParam.setHouseUse("监狱");
				houseUseParam.setHouseUseBM("036");
				break;
			case "88":
				houseUseParam.setHouseUse("仓储");
				houseUseParam.setHouseUseBM("014");
				break;
			default:
				houseUseParam.setHouseUse("其它");
				houseUseParam.setHouseUseBM("033");
				break;
		}
	}

	/**
	 * @Author: yinxunyang
	 * @Description: 房屋性质转换
	 * @Date: 2019/9/10 8:53
	 * @param: housePropParam
	 */
	public void housePropConvert(HousePropParam housePropParam) {
		String houseProp = housePropParam.getHouseProp() == null ? "99" : housePropParam.getHouseProp();
		switch (houseProp) {
			case "0":
				housePropParam.setHouseProp("市场化商品房");
				housePropParam.setHousePropBM("01");
				break;
			case "1":
				housePropParam.setHouseProp("动迁房");
				housePropParam.setHousePropBM("02");
				break;
			case "2":
				housePropParam.setHouseProp("配套商品房");
				housePropParam.setHousePropBM("03");
				break;
			case "3":
				housePropParam.setHouseProp("公共租赁住房");
				housePropParam.setHousePropBM("04");
				break;
			case "4":
				housePropParam.setHouseProp("廉租住房");
				housePropParam.setHousePropBM("05");
				break;
			case "5":
				housePropParam.setHouseProp("限价普通商品住房");
				housePropParam.setHousePropBM("06");
				break;
			case "6":
				housePropParam.setHouseProp("经济适用住房");
				housePropParam.setHousePropBM("07");
				break;
			case "7":
				housePropParam.setHouseProp("定销商品房 ");
				housePropParam.setHousePropBM("08");
				break;
			case "8":
				housePropParam.setHouseProp("集资建房");
				housePropParam.setHousePropBM("09");
				break;
			case "9":
				housePropParam.setHouseProp("福利房");
				housePropParam.setHousePropBM("10");
				break;
			default:
				housePropParam.setHouseProp("市场化商品房");
				housePropParam.setHousePropBM("01");
				break;
		}


	}

	/**
	 * @Author: yinxunyang
	 * @Description: 房屋类型转换
	 * @Date: 2019/9/10 9:05
	 * @param: houseTypeParam
	 */
	public void houseTypeConvert(HouseTypeParam houseTypeParam) {
		String houseType = houseTypeParam.getHouseType() == null ? "99" : houseTypeParam.getHouseType();
		switch (houseType) {
			case "1":
				houseTypeParam.setHouseType("住宅");
				houseTypeParam.setHouseTypeBM("01");
				break;
			case "2":
				houseTypeParam.setHouseType("商业用房");
				houseTypeParam.setHouseTypeBM("02");
				break;
			case "3":
				houseTypeParam.setHouseType("办公用房");
				houseTypeParam.setHouseTypeBM("03");
				break;
			case "4":
				houseTypeParam.setHouseType("工业用房");
				houseTypeParam.setHouseTypeBM("04");
				break;
			case "5":
				houseTypeParam.setHouseType("仓储用房");
				houseTypeParam.setHouseTypeBM("05");
				break;
			case "6":
				houseTypeParam.setHouseType("车库");
				houseTypeParam.setHouseTypeBM("06");
				break;
			default:
				houseTypeParam.setHouseType("其他");
				houseTypeParam.setHouseTypeBM("99");
				break;
		}
	}

	/**
	 * @Author: yinxunyang
	 * @Description: 付款类型转换
	 * @Date: 2019/9/10 9:19
	 * @param: payTypeParam
	 */
	public void payTypeConvert(PayTypeParam payTypeParam) {
		String payType = payTypeParam.getPayType() == null ? "99" : payTypeParam.getPayType();
		switch (payType) {
			case "1":
				payTypeParam.setPayType("无抵押一次性付款");
				payTypeParam.setPayTypeBM("01");
				payTypeParam.setPayWay("不确定");
				payTypeParam.setPayWayBM("04");
				break;
			case "2":
				payTypeParam.setPayType("无抵押分期付款");
				payTypeParam.setPayTypeBM("02");
				payTypeParam.setPayWay("不确定");
				payTypeParam.setPayWayBM("04");
				break;
			case "3":
				payTypeParam.setPayType(MORTGAGE_PAYMENT_BY_INSTALMENTS);
				payTypeParam.setPayTypeBM("04");
				payTypeParam.setPayWay("商业贷款");
				payTypeParam.setPayWayBM("01");
				break;
			case "4":
				payTypeParam.setPayType(MORTGAGE_PAYMENT_BY_INSTALMENTS);
				payTypeParam.setPayTypeBM("04");
				payTypeParam.setPayWay("组合贷款");
				payTypeParam.setPayWayBM("03");
				break;
			case "5":
				payTypeParam.setPayType(MORTGAGE_PAYMENT_BY_INSTALMENTS);
				payTypeParam.setPayTypeBM("04");
				payTypeParam.setPayWay("公积金贷款");
				payTypeParam.setPayWayBM("02");
				break;
			default:
				payTypeParam.setPayType("其他");
				payTypeParam.setPayTypeBM("99");
				payTypeParam.setPayWay("不确定");
				payTypeParam.setPayWayBM("04");
				break;
		}
	}

	/**
	 * @Author: yinxunyang
	 * @Description: 房屋状态转换
	 * @Date: 2019/9/16 10:33
	 */
	public String fWZTConvert(String contractType) {
		String FWZT;
		switch (contractType == null ? "99" : contractType) {
			// 期房
			case "02":
				FWZT = "01";
				break;
			// 现房
			case "03":
				FWZT = "02";
				break;
			default:
				FWZT = "99";
				break;
		}
		return FWZT;
	}

	/**
	 * @Author: yinxunyang
	 * @Description: 交易者性质转换
	 * @Date: 2019/9/16 14:30
	 */
	public void buyXzConvert(BuyXzParam buyXzParam) {
		// 交易者证件号码
		String buyCertNo = buyXzParam.getBuyCertNo();
		if (StringUtils.isEmpty(buyCertNo)) {
			buyXzParam.setBuyCertNo("无");
			buyXzParam.setBuyXz("无");
			buyXzParam.setBuyXzBm("99");
		} else {
			String subStrTwo = "";
			if (buyCertNo.length() >= 2) {
				// 截取前2位
				subStrTwo = buyCertNo.substring(0, 2);
			}
			if (buyCertNo.length() == 15) {
				buyCertNo = IDnumber15to18(buyCertNo);
			}
			if ("37".equals(subStrTwo)) {
				String subStrFour = "";
				if (buyCertNo.length() >= 4) {
					// 截取前4位
					subStrFour = buyCertNo.substring(0, 4);
				}
				if ("3714".equals(subStrFour)) {
					buyXzParam.setBuyXz("本市城镇居民");
					buyXzParam.setBuyXzBm("01");
				} else {
					buyXzParam.setBuyXz("本省外市居民");
					buyXzParam.setBuyXzBm("10");
				}
			} else {
				buyXzParam.setBuyXz("外省市个人");
				buyXzParam.setBuyXzBm("03");
			}
		}
	}

	/**
	 * @return String
	 * @Title: IDnumber15to18
	 * @Description: 15位身份证号转15位
	 * @author jiagaungyuan
	 * @date 2019年8月24日15:47:26
	 */
	public String IDnumber15to18(String idCode) {
		if (idCode == null || idCode == "") {
			return null;
		}
		// 加入年份，变成17位
		idCode = idCode.substring(0, 6) + "19" + idCode.substring(6);

		//计算校验位   begin
		char[] Ai = idCode.toCharArray();
		int[] Wi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
		char[] verifyCode = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
		int S = 0;
		int Y;
		for (int i = 0; i < Wi.length; i++) {
			S += (Ai[i] - '0') * Wi[i];
		}
		Y = S % 11;
		// 校验位计算结果verifyCode[Y] end
		idCode += verifyCode[Y];
		return idCode;
	}
}
