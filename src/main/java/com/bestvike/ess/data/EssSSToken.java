package com.bestvike.ess.data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Ess_SS_Token")
public class EssSSToken implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String deviceSn;
	private String deviceId;
	private String tkPrivateData;
	private String tkSpecialData;
	private Date tkBirth;
	private String tkLostFlag;
	private String tkLockFlag;
	private String tkEnabledFlag;
	private String tkAssignedFlag;
	private String tkCancelFlag;
	private Date tkDeathDate;
	private Date tkImportDate;
	private String tkMethod;
	private BigDecimal tkPinFlag;
	private Date firstErDate;
	private Date lastErDate;
	private BigDecimal tkErSingleNum;
	private BigDecimal tkErTotalNum;
	private BigDecimal tkErStrategyId;
	private String tkOrg;
	private String initCode;

	public String getDeviceSn() {
		return deviceSn;
	}

	public void setDeviceSn(String deviceSn) {
		this.deviceSn = deviceSn;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getTkPrivateData() {
		return tkPrivateData;
	}

	public void setTkPrivateData(String tkPrivateData) {
		this.tkPrivateData = tkPrivateData;
	}

	public String getTkSpecialData() {
		return tkSpecialData;
	}

	public void setTkSpecialData(String tkSpecialData) {
		this.tkSpecialData = tkSpecialData;
	}

	public Date getTkBirth() {
		return tkBirth;
	}

	public void setTkBirth(Date tkBirth) {
		this.tkBirth = tkBirth;
	}

	public String getTkLostFlag() {
		return tkLostFlag;
	}

	public void setTkLostFlag(String tkLostFlag) {
		this.tkLostFlag = tkLostFlag;
	}

	public String getTkLockFlag() {
		return tkLockFlag;
	}

	public void setTkLockFlag(String tkLockFlag) {
		this.tkLockFlag = tkLockFlag;
	}

	public String getTkEnabledFlag() {
		return tkEnabledFlag;
	}

	public void setTkEnabledFlag(String tkEnabledFlag) {
		this.tkEnabledFlag = tkEnabledFlag;
	}

	public String getTkAssignedFlag() {
		return tkAssignedFlag;
	}

	public void setTkAssignedFlag(String tkAssignedFlag) {
		this.tkAssignedFlag = tkAssignedFlag;
	}

	public String getTkCancelFlag() {
		return tkCancelFlag;
	}

	public void setTkCancelFlag(String tkCancelFlag) {
		this.tkCancelFlag = tkCancelFlag;
	}

	public Date getTkDeathDate() {
		return tkDeathDate;
	}

	public void setTkDeathDate(Date tkDeathDate) {
		this.tkDeathDate = tkDeathDate;
	}

	public Date getTkImportDate() {
		return tkImportDate;
	}

	public void setTkImportDate(Date tkImportDate) {
		this.tkImportDate = tkImportDate;
	}

	public String getTkMethod() {
		return tkMethod;
	}

	public void setTkMethod(String tkMethod) {
		this.tkMethod = tkMethod;
	}

	public BigDecimal getTkPinFlag() {
		return tkPinFlag;
	}

	public void setTkPinFlag(BigDecimal tkPinFlag) {
		this.tkPinFlag = tkPinFlag;
	}

	public Date getFirstErDate() {
		return firstErDate;
	}

	public void setFirstErDate(Date firstErDate) {
		this.firstErDate = firstErDate;
	}

	public Date getLastErDate() {
		return lastErDate;
	}

	public void setLastErDate(Date lastErDate) {
		this.lastErDate = lastErDate;
	}

	public BigDecimal getTkErSingleNum() {
		return tkErSingleNum;
	}

	public void setTkErSingleNum(BigDecimal tkErSingleNum) {
		this.tkErSingleNum = tkErSingleNum;
	}

	public BigDecimal getTkErTotalNum() {
		return tkErTotalNum;
	}

	public void setTkErTotalNum(BigDecimal tkErTotalNum) {
		this.tkErTotalNum = tkErTotalNum;
	}

	public BigDecimal getTkErStrategyId() {
		return tkErStrategyId;
	}

	public void setTkErStrategyId(BigDecimal tkErStrategyId) {
		this.tkErStrategyId = tkErStrategyId;
	}

	public String getTkOrg() {
		return tkOrg;
	}

	public void setTkOrg(String tkOrg) {
		this.tkOrg = tkOrg;
	}

	public String getInitCode() {
		return initCode;
	}

	public void setInitCode(String initCode) {
		this.initCode = initCode;
	}
}
