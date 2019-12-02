package com.bestvike.rfis.data;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: ConHouseList
 * @author songhao
 * @date 2017-1-13 下午5:34:05
 * @version V1.0
 */
@Entity
@Table(name="Con_HouseList")
public class ConHouseList implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 是否主房：否
	 */
	public static final String CONHOUSELIST_ISMAIN_N = "N";
	/**
	 * 销售方式：1_按建筑面积
	 */
	public static final String CONHOUSELIST_SALETYPE_AREA = "1";
	/**
	 * 销售方式：2_按套内面积
	 */
	public static final String CONHOUSELIST_SALETYPE_OWNAREA = "2";
	/**
	 * 销售方式：3_按套
	 */
	public static final String CONHOUSELIST_SALETYPE_NUM = "3";
	/**
	 * 销售方式：4_赠送
	 */
	public static final String CONHOUSELIST_SALETYPE_FREE = "4";
	
	@Id
	@Column(name="SYSGUID")
	private String sysGuid;
	@Column(name="CONTRACTNO")
	private String contractNo; // 合同编号
	@Column(name="CONTRACTTYPE")
	private String contractType; // 合同类别
	@Column(name="BLDNO")
	private String bldNo; // 自然幢编号
	@Column(name="HOUSEGUID")
	private String houseGuid; // 房屋编号
	@Column(name="ISMAIN")
	private String isMain; // 是否主房
	@Column(name="SALETYPE")
	private String saleType; // 销售方式
	@Column(name="AREA")
	private BigDecimal area; // 面积
	@Column(name="UNITPRICE")
	private BigDecimal unitPrice; // 单价
	@Column(name="PRICE")
	private BigDecimal price; // 房屋售价
	@Column(name="FIRSTPAYAMOUNT")
	private BigDecimal firstPayAmount;//首付款
	
	@Column(name="DIVISIONCODE")
	private String divisionCode;
	@Column(name="PROJECTNO")
	private String projectNo;
	@Column(name="HOUSETYPE")
	private String houseType;
	
	@Column(name="LASTTIME")
	private String lastTime; // 数据最后更新时间
	@Column(name="REMARK")
	private String remark; // 备注
	
	private String address;
	
	private String dataCenterId; // datacenter中合同房屋表的sys_guid
	
	private Date versionTime; // 发送到共享库的日期
	
	private BigDecimal versionNumber;// 发送版本
	@Column
	private String contractGuid;
	
	private String conSysGuid; // dataCenter中合同表的sys_guid
	private String state;
	private String houseId; // dataCenter中房屋表的sys_guid
	
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
	public String getBldNo() {
		return bldNo;
	}
	public void setBldNo(String bldNo) {
		this.bldNo = bldNo;
	}
	public String getHouseGuid() {
		return houseGuid;
	}
	public void setHouseGuid(String houseGuid) {
		this.houseGuid = houseGuid;
	}
	public String getIsMain() {
		return isMain;
	}
	public void setIsMain(String isMain) {
		this.isMain = isMain;
	}
	public String getSaleType() {
		return saleType;
	}
	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}
	public BigDecimal getArea() {
		return area;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setArea(BigDecimal area) {
		this.area = area;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
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
	public String getDivisionCode() {
		return divisionCode;
	}
	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}
	public String getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}
	public String getHouseType() {
		return houseType;
	}
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	public String getConSysGuid() {
		return conSysGuid;
	}
	public void setConSysGuid(String conSysGuid) {
		this.conSysGuid = conSysGuid;
	}
	public String getDataCenterId() {
		return dataCenterId;
	}
	public void setDataCenterId(String dataCenterId) {
		this.dataCenterId = dataCenterId;
	}
	public Date getVersionTime() {
		return versionTime;
	}
	public void setVersionTime(Date versionTime) {
		this.versionTime = versionTime;
	}
	public BigDecimal getVersionNumber() {
		return versionNumber;
	}
	public void setVersionNumber(BigDecimal versionNumber) {
		this.versionNumber = versionNumber;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getHouseId() {
		return houseId;
	}
	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	public String getContractGuid() {
		return contractGuid;
	}
	public void setContractGuid(String contractGuid) {
		this.contractGuid = contractGuid;
	}
	public BigDecimal getFirstPayAmount() {
		return firstPayAmount;
	}
	public void setFirstPayAmount(BigDecimal firstPayAmount) {
		this.firstPayAmount = firstPayAmount;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
