package com.bestvike.rfis.data;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: ConBuyerInfo
 * @author songhao
 * @date 2017-1-10 下午1:44:06
 * @version V1.0
 */
@Entity
@Table(name="Con_BuyerInfo")
public class ConBuyerInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 买受人性质:自然人
	 */
	public static final String BUYER_PROP_NORMAL = "1";
	/**
	 * 是否是第一买受人：是
	 */
	public static final String BUYER_ISFIRSTBUYER_Y = "Y";
	/**
	 * 是否是第一买受人：否
	 */
	public static final String BUYER_ISFIRSTBUYER_N = "N";
	/**
	 * 买受人类别：预购人
	 */
	public static final String BUYER_TYPE_PRE = "1";
	/**
	 * 买受人类别：共有人
	 */
	public static final String BUYER_TYPE_PUB = "2";
	/**
	 * 户籍所在地：本市
	 */
	public static final String BUYER_REGISTER_CITY = "01";
	/**
	 * 户籍所在地：本省外市
	 */
	public static final String BUYER_REGISTER_PRIVENCE = "02";
	/**
	 * 户籍所在地：省外
	 */
	public static final String BUYER_REGISTER_OTHER = "03";
	
	/**
	 * 买受人证件类型
	 */
	public static final String BUYER_CERTTYPE_OTHER = "99" ;

	@Id
	@Column
	private String sysGuid;
	@Column
	private String contractNo; // 合同编号
	@Column
	private String contractType; // 合同类别
	@Column
	private String buyName; // 买受人姓名
	@Column
	private String buyerProp; // 买受人性质
	@Column
	private String buyType; // 买受人类别
	@Column
	private String buyCountry; // 国籍
	@Column
	private String buyCertType; // 证件名称
	@Column
	private String buyCertNo; // 证件号码
	@Column
	private String buyRegister; // 户口所在地
	@Column
	private String buyBirth; // 出生年月日
	@Column
	private String buySex; // 性别
	@Column
	private String buyAddress; // 住址
	@Column
	private String buyPostCode; // 邮编
	@Column
	private String buyPhone; // 联系电话
	@Column
	private String specialNo; // 经适房/两限房备案号
	@Column
	private String lastTime; // 数据最后更新时间
	@Column
	private String remark; // 备注
	@Column
	private String isFirstBuyer; // 是否是第一预购人
	@Column
	private BigDecimal versionNumber;
	@Column
	private Date versionTime;
	@Column
	private String dataCenterId;
	@Column
	private String contractGuid; // 合同信息表的主键
	
	private String mainHouseGuid; // 主房的guid
	
	private String conSysGuid; // datacenter的合同表的sys_Guid
	

	
	

	public String getSysGuid() {
		return sysGuid;
	}
	public void setSysGuid(String sysGuid) {
		this.sysGuid = sysGuid;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getContractType() {
		return contractType;
	}
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	public String getBuyName() {
		return buyName;
	}
	public void setBuyName(String buyName) {
		this.buyName = buyName;
	}
	public String getBuyerProp() {
		return buyerProp;
	}
	public void setBuyerProp(String buyerProp) {
		this.buyerProp = buyerProp;
	}
	public String getBuyType() {
		return buyType;
	}
	public void setBuyType(String buyType) {
		this.buyType = buyType;
	}
	public String getBuyCountry() {
		return buyCountry;
	}
	public void setBuyCountry(String buyCountry) {
		this.buyCountry = buyCountry;
	}
	public String getBuyCertType() {
		return buyCertType;
	}
	public void setBuyCertType(String buyCertType) {
		this.buyCertType = buyCertType;
	}
	public String getBuyCertNo() {
		return buyCertNo;
	}
	public void setBuyCertNo(String buyCertNo) {
		this.buyCertNo = buyCertNo;
	}
	public String getBuyRegister() {
		return buyRegister;
	}
	public void setBuyRegister(String buyRegister) {
		this.buyRegister = buyRegister;
	}
	public String getBuyBirth() {
		return buyBirth;
	}
	public void setBuyBirth(String buyBirth) {
		this.buyBirth = buyBirth;
	}
	public String getBuySex() {
		return buySex;
	}
	public void setBuySex(String buySex) {
		this.buySex = buySex;
	}
	public String getBuyAddress() {
		return buyAddress;
	}
	public void setBuyAddress(String buyAddress) {
		this.buyAddress = buyAddress;
	}
	public String getBuyPostCode() {
		return buyPostCode;
	}
	public void setBuyPostCode(String buyPostCode) {
		this.buyPostCode = buyPostCode;
	}
	public String getBuyPhone() {
		return buyPhone;
	}
	public void setBuyPhone(String buyPhone) {
		this.buyPhone = buyPhone;
	}
	public String getSpecialNo() {
		return specialNo;
	}
	public void setSpecialNo(String specialNo) {
		this.specialNo = specialNo;
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
	public String getIsFirstBuyer() {
		return isFirstBuyer;
	}
	public void setIsFirstBuyer(String isFirstBuyer) {
		this.isFirstBuyer = isFirstBuyer;
	}
	public String getMainHouseGuid() {
		return mainHouseGuid;
	}
	public void setMainHouseGuid(String mainHouseGuid) {
		this.mainHouseGuid = mainHouseGuid;
	}
	public BigDecimal getVersionNumber() {
		return versionNumber;
	}
	public void setVersionNumber(BigDecimal versionNumber) {
		this.versionNumber = versionNumber;
	}
	public Date getVersionTime() {
		return versionTime;
	}
	public void setVersionTime(Date versionTime) {
		this.versionTime = versionTime;
	}
	public String getDataCenterId() {
		return dataCenterId;
	}
	public void setDataCenterId(String dataCenterId) {
		this.dataCenterId = dataCenterId;
	}
	public String getContractGuid() {
		return contractGuid;
	}
	public void setContractGuid(String contractGuid) {
		this.contractGuid = contractGuid;
	}
	public String getConSysGuid() {
		return conSysGuid;
	}
	public void setConSysGuid(String conSysGuid) {
		this.conSysGuid = conSysGuid;
	}

}
