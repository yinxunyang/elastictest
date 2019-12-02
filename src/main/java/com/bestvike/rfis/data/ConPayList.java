package com.bestvike.rfis.data;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName: ConPayList
 * @Description: 付款明细信息
 * @author madk
 * @date 2017年3月7日 下午1:20:11
 * @version V1.0
 */
@Entity
@Table(name="Con_PayList")
public class ConPayList implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//第一次付款
	public static final int CONPAYLIST_ORDERNO = 1;
	
	@Id
	@Column
	private String sysGuid;
	@Column
	private String contractNo;
	@Column
	private String contractGuid; // 合同信息表的主键
	@Column
	private String orderNo;
	@Column
	private String currentType;
	@Column
	private BigDecimal payScale;
	@Column
	private BigDecimal payAmount;
	@Column
	private String payDate;
	@Column
	private String lastTime;
	@Column
	private String remark;
	
	private String mainHousePrice;//主房价款
	private String bankName;//监管银行名称
	private String account;//监管账户
	
	
	public String getSysGuid() {
		return sysGuid;
	}
	public String getContractNo() {
		return contractNo;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public String getPayDate() {
		return payDate;
	}
	public String getLastTime() {
		return lastTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setSysGuid(String sysGuid) {
		this.sysGuid = sysGuid;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public BigDecimal getPayScale() {
		return payScale;
	}
	public BigDecimal getPayAmount() {
		return payAmount;
	}
	public void setPayScale(BigDecimal payScale) {
		this.payScale = payScale;
	}
	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}
	public String getCurrentType() {
		return currentType;
	}
	public void setCurrentType(String currentType) {
		this.currentType = currentType;
	}
	public String getContractGuid() {
		return contractGuid;
	}
	public void setContractGuid(String contractGuid) {
		this.contractGuid = contractGuid;
	}
	
	public String getMainHousePrice() {
		return mainHousePrice;
	}
	public void setMainHousePrice(String mainHousePrice) {
		this.mainHousePrice = mainHousePrice;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}		
}
