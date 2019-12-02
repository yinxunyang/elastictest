package com.bestvike.rfis.data;



import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: ArcProjectInfo
 * @Description: 项目信息
 * @author fanli
 * @date 2016-9-20 上午10:21:19
 * @version V1.0
 */
@Entity
@Table(name="Arc_Projectinfo")
public class ArcProjectInfo  implements Serializable {
	
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 项目信息表 状态 正常 0
	 */
	public static final String PROJECT_STATE_RECORD = "0";
	/**
	 * 项目信息表 状态 待备案 1
	 */
	public static final String PROJECT_STATE_WILLRECORD = "1";
	/**
	 * 项目信息表 状态 临时保存T
	 */
	public static final String PROJECT_STATE_TEMPSAVE = "T";
	/**
	 * 项目信息表 状态 删除X
	 */
	public static final String PROJECT_STATE_DELETE = "X";
	/**
	 * 五证状态 正常
	 */
	public static final String CERT_STATE_NORMAL = "0";
	
	@Id
	@Basic(optional = false)
	@Column(name="SYSGUID")
	private String sysGuid;
	@Column(name="PROJECTNO")
	private String projectNo;
	@Column(name="PROJECTNAME")
	private String projectName;
	@Column(name="SALENAME")
	private String saleName;
	@Column(name="STATE")
	private String state;
	@Column(name="DIVISIONCODE")
	private String divisionCode;
	@Column(name="ADDRESS")
	private String address;
	private String corpName;
	@Column(name="CORPNO")
	private String corpNo;
	@Column(name="SERVICENO")
	private String serviceNo;
	@Column(name="OWNERNO")
	private String ownerNo;
	@Column(name="OPENDATE")
	private String openDate;
	@Column(name="ADMITDATE")
	private String admitDate;
	@Column(name="SALEPHONENO")
	private String salePhoneNo;
	@Column(name="SALEADDRESS")
	private String saleAddress;
	@Column(name="TOTALNUM")
	private Integer totalNum;
	@Column(name="TOTALAREA")
	private BigDecimal totalArea;
	@Column(name="BOUNDARIES")
	private String boundaries;
	@Column(name="COVERAREA")
	private BigDecimal coverArea;
	@Column(name="BLDNUM")
	private Integer bldNum;
	@Column(name="SALEABLENUM")
	private Integer saleableNum;
	@Column(name="SALEABLEAREA")
	private BigDecimal saleableArea;
	@Column(name="ORDERNUM")
	private Integer orderNum;
	@Column(name="SOLDNUM")
	private Integer soldNum;
	@Column(name="DESCRIBE")
	private String describe;
	@Column(name="FITMENT")
	private String fitment;
	@Column(name="PROGRESSING")
	private String progressing;
	@Column(name="SUPPORTING")
	private String supporting;
	@Column(name="TRAFFIC")
	private String traffic;
	@Column(name="GREENPERCENTAGE")
	private String greenPercentage;
	@Column(name="PLOTRATIO")
	private String plotRatio;
	@Column(name="BUILDINGDENSITY")
	private BigDecimal buildingDensity;
	@Column(name="PARKINGNUM")
	private Integer parkingNum;
	@Column(name="PLATENO")
	private String plateNo;
	@Column(name="LANDINVEST")
	private BigDecimal landInvest;
	@Column(name="PLANTOTALAREA")
	private BigDecimal planTotalArea;
	@Column(name="PLANTOTALINVEST")
	private BigDecimal planTotalInvest;
	@Column(name="PLANOPENDATE")
	private String planOpenDate;
	@Column(name="PLANFINISHDATE")
	private String planFinishDate;
	@Column(name="ACTUALINVEST")
	private BigDecimal actualInvest;
	@Column(name="ACTUALOPENDATE")
	private String actualOpenDate;
	@Column(name="ACTUALFINISHDATE")
	private String actualFinishDate;
	@Column(name="REGISTERDATE")
	private String registerDate;
	@Column(name="REGISTERTIME")
	private String registerTime;
	@Column(name="REGISTERUSER")
	private String registerUser;
	@Column(name="RECORDDATE")
	private String recordDate;
	@Column(name="RECORDTIME")
	private String recordTime;
	@Column(name="RECORDUSER")
	private String recordUser;
	@Column(name="DATAFROM")
	private String dataFrom;
	@Column(name="ORGINNO")
	private String orginNo;
	@Column(name="DATAEXPLAIN")
	private String dataExplain;
	@Column(name="LASTTIME")
	private String lastTime;
	@Column(name="REMARK")
	private String remark;
	@Column
	private BigDecimal versionNumber; // 往数据平台传送数据版本号
	@Column
	private Date versionTime; // 往数据平台传送数据时间
	@Column
	private String dataCenterId;
	
	private String landRightNo; // 土地使用证编号
	private String landPermitNo; // 建设用地规划许可证编号
	private String landLevel; // 土地等级
	private String planUsage; // 土地用途
	
	private String auditremark;    //审核意见
	private String reqDescribe;    //申请描述

	private String developId; // 数据中心企业信息表Id（开发公司）
	private String serviceId; // 数据中心企业信息表Id(物业企业)
	private String ownersId; // 数据中心企业信息表I的（业主委员会）
	private String serviceName; // 物业公司名称
	private String ownerName; // 业主委员会名称

	public ArcProjectInfo(){
		
	}
	 public ArcProjectInfo(String sysGuid) {
	        this.sysGuid = sysGuid;
	    }
	public ArcProjectInfo(String sysGuid, String projectNo, String projectName, String saleName,
                          String state, String divisionCode, String address, String corpName,
                          String corpNo, String serviceNo, String ownerNo, String openDate, String admitDate, String salePhoneNo,
                          String saleAddress, Integer totalNum, BigDecimal totalArea, String boundaries,
                          BigDecimal coverArea, Integer bldNum, Integer saleableNum, BigDecimal saleableArea,
                          Integer orderNum, Integer soldNum, String describe, String fitment,
                          String progressing, String supporting, String traffic, String greenPercentage,
                          String plotRatio, BigDecimal buildingDensity, Integer parkingNum, String plateNo,
                          BigDecimal landInvest, BigDecimal planTotalArea, BigDecimal planTotalInvest, String planOpenDate,
                          String planFinishDate, BigDecimal actualInvest, String actualOpenDate, String actualFinishDate,
                          String registerDate, String registerTime, String registerUser, String recordDate,
                          String recordTime, String recordUser, String dataFrom, String orginNo,
                          String dataExplain, String lastTime, String remark) {
				super();
				this.sysGuid = sysGuid;
				this.projectNo = projectNo;
				this.projectName = projectName;
				this.saleName = saleName;
				this.state = state;
				this.divisionCode = divisionCode;
				this.address = address;
				this.corpName=corpName;
				this.corpNo = corpNo;
				this.openDate = openDate;
				this.admitDate = admitDate;
				this.salePhoneNo = salePhoneNo;
				this.saleAddress = saleAddress;
				this.totalNum = totalNum;
				this.totalArea = totalArea;
				this.boundaries = boundaries;
				this.coverArea = coverArea;
				this.bldNum = bldNum;
				this.saleableNum = saleableNum;
				this.saleableArea = saleableArea;
				this.orderNum = orderNum;
				this.soldNum = soldNum;
				this.describe = describe;
				this.fitment = fitment;
				this.progressing = progressing;
				this.supporting = progressing;
				this.traffic = traffic;
				this.greenPercentage = greenPercentage;
				this.plotRatio = plotRatio;
				this.buildingDensity = buildingDensity;
				this.parkingNum = parkingNum;
				this.plateNo = plateNo;
				this.landInvest = landInvest;
				this.planTotalArea = planTotalArea;
				this.planTotalInvest = planTotalInvest;
				this.planOpenDate = planOpenDate;
				this.planFinishDate = planFinishDate;
				this.actualInvest = actualInvest;
				this.actualOpenDate = actualOpenDate;
				this.actualFinishDate = actualFinishDate;
				this.registerDate = registerDate;
				this.registerTime = registerTime;
				this.registerUser = registerUser;
				this.recordDate = recordDate;
			    this.recordTime = recordTime;
			    this.recordUser = recordUser;
			    this.dataFrom = dataFrom;
			    this.orginNo = orginNo;
			    this.dataExplain = dataExplain;
			    this.lastTime = lastTime;
			    this.remark = remark;
				this.serviceNo = serviceNo;
				this.ownerNo = ownerNo;
	}
	public String getSysGuid() {
		return sysGuid;
	}
	public void setSysGuid(String sysGuid) {
		this.sysGuid = sysGuid;
	}
	public String getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getSaleName() {
		return saleName;
	}
	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDivisionCode() {
		return divisionCode;
	}
	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getCorpNo() {
		return corpNo;
	}
	public void setCorpNo(String corpNo) {
		this.corpNo = corpNo;
	}
	public String getOpenDate() {
		return openDate;
	}
	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}
	public String getAdmitDate() {
		return admitDate;
	}
	public void setAdmitDate(String admitDate) {
		this.admitDate = admitDate;
	}
	public String getSalePhoneNo() {
		return salePhoneNo;
	}
	public void setSalePhoneNo(String salePhoneNo) {
		this.salePhoneNo = salePhoneNo;
	}
	public String getSaleAddress() {
		return saleAddress;
	}
	public void setSaleAddress(String saleAddress) {
		this.saleAddress = saleAddress;
	}
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public BigDecimal getTotalArea() {
		return totalArea;
	}
	public void setTotalArea(BigDecimal totalArea) {
		this.totalArea = totalArea;
	}
	public String getBoundaries() {
		return boundaries;
	}
	public void setBoundaries(String boundaries) {
		this.boundaries = boundaries;
	}
	public BigDecimal getCoverArea() {
		return coverArea;
	}
	public void setCoverArea(BigDecimal coverArea) {
		this.coverArea = coverArea;
	}
	public Integer getBldNum() {
		return bldNum;
	}
	public void setBldNum(Integer bldNum) {
		this.bldNum = bldNum;
	}
	public Integer getSaleableNum() {
		return saleableNum;
	}
	public void setSaleableNum(Integer saleableNum) {
		this.saleableNum = saleableNum;
	}
	public BigDecimal getSaleableArea() {
		return saleableArea;
	}
	public void setSaleableArea(BigDecimal saleableArea) {
		this.saleableArea = saleableArea;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public Integer getSoldNum() {
		return soldNum;
	}
	public void setSoldNum(Integer soldNum) {
		this.soldNum = soldNum;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getFitment() {
		return fitment;
	}
	public void setFitment(String fitment) {
		this.fitment = fitment;
	}
	public String getProgressing() {
		return progressing;
	}
	public void setProgressing(String progressing) {
		this.progressing = progressing;
	}
	public String getSupporting() {
		return supporting;
	}
	public void setSupporting(String supporting) {
		this.supporting = supporting;
	}
	public String getTraffic() {
		return traffic;
	}
	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}
	public String getGreenPercentage() {
		return greenPercentage;
	}
	public void setGreenPercentage(String greenPercentage) {
		this.greenPercentage = greenPercentage;
	}
	public String getPlotRatio() {
		return plotRatio;
	}
	public void setPlotRatio(String plotRatio) {
		this.plotRatio = plotRatio;
	}
	public BigDecimal getBuildingDensity() {
		return buildingDensity;
	}
	public void setBuildingDensity(BigDecimal buildingDensity) {
		this.buildingDensity = buildingDensity;
	}
	public Integer getParkingNum() {
		return parkingNum;
	}
	public void setParkingNum(Integer parkingNum) {
		this.parkingNum = parkingNum;
	}
	public String getPlateNo() {
		return plateNo;
	}
	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}
	public BigDecimal getLandInvest() {
		return landInvest;
	}
	public void setLandInvest(BigDecimal landInvest) {
		this.landInvest = landInvest;
	}
	public BigDecimal getPlanTotalArea() {
		return planTotalArea;
	}
	public void setPlanTotalArea(BigDecimal planTotalArea) {
		this.planTotalArea = planTotalArea;
	}

	public BigDecimal getPlanTotalInvest() {
		return planTotalInvest;
	}
	public void setPlanTotalInvest(BigDecimal planTotalInvest) {
		this.planTotalInvest = planTotalInvest;
	}

	public String getPlanOpenDate() {
		return planOpenDate;
	}
	public void setPlanOpenDate(String planOpenDate) {
		this.planOpenDate = planOpenDate;
	}
	public String getPlanFinishDate() {
		return planFinishDate;
	}
	public void setPlanFinishDate(String planFinishDate) {
		this.planFinishDate = planFinishDate;
	}
	public BigDecimal getActualInvest() {
		return actualInvest;
	}
	public void setActualInvest(BigDecimal actualInvest) {
		this.actualInvest = actualInvest;
	}
	public String getActualOpenDate() {
		return actualOpenDate;
	}
	public void setActualOpenDate(String actualOpenDate) {
		this.actualOpenDate = actualOpenDate;
	}
	public String getActualFinishDate() {
		return actualFinishDate;
	}
	public void setActualFinishDate(String actualFinishDate) {
		this.actualFinishDate = actualFinishDate;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	
	public String getRegisterUser() {
		return registerUser;
	}
	public void setRegisterUser(String registerUser) {
		this.registerUser = registerUser;
	}
	public String getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}
	public String getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}
	
	public String getRecordUser() {
		return recordUser;
	}
	public void setRecordUser(String recordUser) {
		this.recordUser = recordUser;
	}
	public String getDataFrom() {
		return dataFrom;
	}
	public void setDataFrom(String dataFrom) {
		this.dataFrom = dataFrom;
	}
	public String getOrginNo() {
		return orginNo;
	}
	public void setOrginNo(String orginNo) {
		this.orginNo = orginNo;
	}
	public String getDataExplain() {
		return dataExplain;
	}
	public void setDataExplain(String dataExplain) {
		this.dataExplain = dataExplain;
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
	public String getAuditremark() {
		return auditremark;
	}
	public void setAuditremark(String auditremark) {
		this.auditremark = auditremark;
	}
    public String getReqDescribe() {
        return reqDescribe;
    }
    public void setReqDescribe(String reqDescribe) {
        this.reqDescribe = reqDescribe;
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
	public String getLandRightNo() {
		return landRightNo;
	}
	public void setLandRightNo(String landRightNo) {
		this.landRightNo = landRightNo;
	}
	public String getLandPermitNo() {
		return landPermitNo;
	}
	public void setLandPermitNo(String landPermitNo) {
		this.landPermitNo = landPermitNo;
	}
	public String getLandLevel() {
		return landLevel;
	}
	public void setLandLevel(String landLevel) {
		this.landLevel = landLevel;
	}
	public String getPlanUsage() {
		return planUsage;
	}
	public void setPlanUsage(String planUsage) {
		this.planUsage = planUsage;
	}
	public String getDataCenterId() {
		return dataCenterId;
	}
	public void setDataCenterId(String dataCenterId) {
		this.dataCenterId = dataCenterId;
	}
	public String getDevelopId() {
		return developId;
	}
	public void setDevelopId(String developId) {
		this.developId = developId;
	}
	public String getServiceNo() {
		return serviceNo;
	}
	public void setServiceNo(String serviceNo) {
		this.serviceNo = serviceNo;
	}
	public String getOwnerNo() {
		return ownerNo;
	}
	public void setOwnerNo(String ownerNo) {
		this.ownerNo = ownerNo;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getOwnersId() {
		return ownersId;
	}
	public void setOwnersId(String ownersId) {
		this.ownersId = ownersId;
	}
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	
}
