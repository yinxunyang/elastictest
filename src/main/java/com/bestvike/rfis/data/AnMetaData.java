package com.bestvike.rfis.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="an_metadata")
public class AnMetaData  implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column
	private String objType ;
	@Column
	private String objCode  ;
	@Column
	private String objName ;
	@Column
	private String houseType ;
	@Column
	private String busiDate ;
	@Column
	private BigDecimal price ;
	@Column
	private BigDecimal totalNum ;
	@Column
	private BigDecimal totalArea ;
	@Column
	private BigDecimal totalAmount ;
	@Column
	private String recordAate ;
	@Column
	private String recordTime ;
	@Column
	private String remark ;
	@Column
	private BigDecimal houseNum;
	// add by songhao
	@Column
	private BigDecimal constructArea; // 建筑面积（上面的totalArea是销售出的面积，这里指房屋的建筑面积）
	@Column
	private String busiMonth;
	@Column
	private String buyRegister; // 买受人户籍所在地
	
	public String getObjType() {
		return objType;
	}
	public void setObjType(String objType) {
		this.objType = objType;
	}
	public String getObjCode() {
		return objCode;
	}
	public void setObjCode(String objCode) {
		this.objCode = objCode;
	}
	public String getObjName() {
		return objName;
	}
	public void setObjName(String objName) {
		this.objName = objName;
	}
	public String getHouseType() {
		return houseType;
	}
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	public String getBusiDate() {
		return busiDate;
	}
	public void setBusiDate(String busiDate) {
		this.busiDate = busiDate;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(BigDecimal totalNum) {
		this.totalNum = totalNum;
	}
	public BigDecimal getTotalArea() {
		return totalArea;
	}
	public void setTotalArea(BigDecimal totalArea) {
		this.totalArea = totalArea;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getRecordAate() {
		return recordAate;
	}
	public void setRecordAate(String recordAate) {
		this.recordAate = recordAate;
	}
	public String getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public BigDecimal getHouseNum() {
		return houseNum;
	}
	public void setHouseNum(BigDecimal houseNum) {
		this.houseNum = houseNum;
	}
	public BigDecimal getConstructArea() {
		return constructArea;
	}
	public void setConstructArea(BigDecimal constructArea) {
		this.constructArea = constructArea;
	}
	public String getBusiMonth() {
		return busiMonth;
	}
	public void setBusiMonth(String busiMonth) {
		this.busiMonth = busiMonth;
	}
	public String getBuyRegister() {
		return buyRegister;
	}
	public void setBuyRegister(String buyRegister) {
		this.buyRegister = buyRegister;
	}
}
