package com.bestvike.rfis.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName: ConSubscribInfo
 * @author songhao
 * @date 2017-1-12 上午11:51:32
 * @version V1.0
 */
@Entity
@Table(name="Con_SubscribInfo")
public class ConSubscribInfo  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 协议状态：临时保存
	 */
	public static final String CONSUBSCRIBINFO_SCRIBSTATE_TEMP = "T";
	/**
	 * 协议状态：已提交
	 */
	public static final String CONSUBSCRIBINFO_SCRIBSTATE_SUBMIT = "1";
	/**
	 * 协议状态：已转网签合同
	 */
	public static final String CONSUBSCRIBINFO_SCRIBSTATE_CONVERTCON = "2";
	/**
	 * 协议状态：已过期
	 */
	public static final String CONSUBSCRIBINFO_SCRIBSTATE_INVALID = "3";
	/**
	 * 协议状态：撤销
	 */
	public static final String CONSUBSCRIBINFO_SCRIBSTATE_CANCEL = "X";
	@Id
	@Column
	private String sysGuid; // GUID
	@Column
	private String scribNo; // 协议编号
	@Column
	private String scribState; // 协议状态
	@Column
	private String password; // 密码
	@Column
	private String templateNo; // 模版编号
	@Column
	private String contractNo; // 合同号
	@Column
	private String projectNo; // 项目编号
	@Column
	private String validDate; // 协议有效日期
	@Column
	private String signDate; // 签定日期
	@Column
	private String signUser; // 签定人员
	@Column
	private BigDecimal mainHousePrice; // 主房价款
	@Column
	private BigDecimal totalPrice; // 总价款
	@Column
	private BigDecimal amount; // 定金金额
	@Column
	private String payType; // 付款方式
	@Column
	private String lastTime; // 数据最后更新时间
	@Column
	private String remark; // 备注
	public String getSysGuid() {
		return sysGuid;
	}
	public void setSysGuid(String sysGuid) {
		this.sysGuid = sysGuid;
	}
	public String getScribNo() {
		return scribNo;
	}
	public void setScribNo(String scribNo) {
		this.scribNo = scribNo;
	}
	public String getScribState() {
		return scribState;
	}
	public void setScribState(String scribState) {
		this.scribState = scribState;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTemplateNo() {
		return templateNo;
	}
	public void setTemplateNo(String templateNo) {
		this.templateNo = templateNo;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}
	public String getValidDate() {
		return validDate;
	}
	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}
	public String getSignDate() {
		return signDate;
	}
	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}
	public String getSignUser() {
		return signUser;
	}
	public void setSignUser(String signUser) {
		this.signUser = signUser;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getLastTime() {
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getMainHousePrice() {
		return mainHousePrice;
	}
	public void setMainHousePrice(BigDecimal mainHousePrice) {
		this.mainHousePrice = mainHousePrice;
	}

}
