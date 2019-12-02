package com.bestvike.rfis.data;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author WangSong
 * @date 2016-8-16 上午8:58:14
 * @version V1.0
 */
@Entity
@Table(name="Arc_Corpinfo")
public class ArcCorpInfo  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 企业信息表 状态 正常 0
	 */
	public static final String CORPINFO_STATE_RECORD = "0";
	/**
	 * 企业信息表 状态 待备案 1
	 */
	public static final String CORPINFO_STATE_WILLRECORD = "1";
	/**
	 * 企业信息表 状态 临时保存T
	 */
	public static final String CORPINFO_STATE_TEMPSAVE = "T";
	/**
	 * 企业信息表 状态 删除X
	 */
	public static final String CORPINFO_STATE_DELETE = "X";
	/**
	 * 企业信息表 状态 注销
	 */
	public static final String CORPINFO_STATE_CANCEL = "D";
	/**
	 * 企业信息来源：系统录入
	 */
	public static final String CORPINFO_DATAFROM_ENTER = "10" ;
	/**
	 * 企业信息来源：excel导入
	 */
	public static final String CORPINFO_DATAFROM_EXCELIMP = "11";
	/**
	 * 企业信息来源：历史数据导入
	 */
	public static final String CORPINFO_DATAFROM_IMP = "21" ;
	/**
	 * 备案方式： 手动备案 1
	 */
	public static final String CORPINFO_RECORD_MANUAL = "1" ;
	/**
	 * 备案方式：自动备案 2
	 */
	public static final String CORPINFO_RECORD_AUTO = "2" ;
	/**
	 * 企业类别：开发企业
	 */
	public static final String CORPINFO_TYPE_DEVELOP = "1";
	/**
	 * 企业类别：物业服务企业
	 */
	public static final String CORPINFO_TYPE_SERVICE = "4";
	/**
	 * 企业类别：业主委员会
	 */
	public static final String CORPINFO_TYPE_OWNERS = "6";
	/**
	 * 企业类别 ： 代理销售企业
	 */
	public static final String CORPINFO_TYPE_AGENCY  = "98";
	/**
	 * 主管单位默认编号：00000000
	 */
	public static final String defaultCorpNo = "00000000";
	
	@Id
	@Basic(optional = false)
	@Column(name="SYSGUID")
	private String sysGuid;
	@Column(name="CORPNO")
	private String corpNo; //企业编号
	@Column(name="CORPTYPE")
	private String corpType;//企业类别
	@Column(name="STATE")
	private String state;//状态
	@Column(name="CORPNAME")
	private String corpName;//企业名称
	@Column(name="SHORTNAME")
	private String shortName;//企业简称
	@Column(name="CERTIFICATENO")
	private String certificateNo;//组织机构代码
	@Column(name="CITYCODE")
	private String cityCode;//所在城市
	@Column(name="DIVISIONCODE")
	private String divisionCode;//区县
	@Column(name="ISFOREIGN")
	private String isForeign ;//是否外来机构
	@Column(name="ISBRANCH")
	private String isBranch ;//是否分支机构
	@Column(name="BUSISCOPE")
	private String busiScope;//经营范围
	@Column(name="REGISTERADDRESS")
	private String registerAddress;//注册地址
	@Column(name="CONTACTADDRESS")
	private String contactAddress;//联系地址
	@Column(name="POSTALCODE")
	private String postalCode;//邮政编码
	@Column(name="CONTACTNAME")
	private String contactName;//联系人
	@Column(name="PHONENO")
	private String phoneNo;//联系人电话
	@Column(name="FAXNO")
	private String faxNo;//传真
	@Column(name="EMAIL")
	private String email;//电子邮件
	@Column(name="WEBSITE")
	private String website;//网站
	@Column(name="CREDENTIALGRADE")
	private String credentialGrade;//资质等级
	@Column(name="CREDENTIALNO")
	private String credentialNo;//资质证书编号
	@Column(name="CREDENTIALSTART")
	private String credentialStart;//资质证书有效起始日期
	@Column(name="CREDENTIALEND")
	private String credentialEnd;//资质证书有效终止日期
	@Column(name="HASTITLENUM")
	private Integer hasTitleNum;//有职称专业人数
	@Column(name="HIGHTITLENUM")
	private Integer highTitleNum;//高级职称人数
	@Column(name="HEADCOUNT")
	private Integer headCount;//在册人员总数
	@Column(name="MIDDLETITLENUM")
	private Integer middleTitleNum;//中级职称人数
	@Column(name="PRIMARYTITLENUM")
	private Integer primaryTitleNum;//初级职称人数
	@Column(name="LICENSENO")
	private String licenseNo;//营业执照号码
	@Column(name="LICENSESTART")
	private String licenseStart;//营业执照经营期限起始日
	@Column(name="LICENSEEND")
	private String licenseEnd;//营业执照经营期限到期日
	@Column(name="NETASSET")
	private BigDecimal netAsset;//净资产（元）
	@Column(name="TOTALASSET")
	private BigDecimal totalAsset;//总资产（元）
	@Column(name="REGISTERASSET")
	private BigDecimal registerAsset;//注册资本（元）
	@Column(name="CURRENCYTYPE")
	private String currencyType;//注册资本单位
	@Column(name="GENERALMANAGER")
	private String generalManager;//总经理
	@Column(name="LEGALNAME")
	private String legalName;//法定代表人
	@Column(name="LEGALPHONENO")
	private String legalPhoneNo;//法定代表联系电话
	@Column(name="LEGALCERTTYPE")
	private String legalCertType;//法定代表证件类别
	@Column(name="LEGALCERTNO")
	private String legalCertNo;//法定代表证件号码
	@Column(name="REGISTERDATE")
	private String registerDate;//录入日期
	@Column(name="REGISTERTIME")
	private String registerTime;//录入时间
	@Column(name="REGISTERUSER")
	private String registerUser;//录入人员
	@Column(name="RECORDDATE")
	private String recordDate;//备案日期
	@Column(name="RECORDTIME")
	private String recordTime;//备案时间
	@Column(name="RECORDUSER")
	private String recordUser;//备案人员
	@Column(name="CORPPROPERTY")
	private String corpProperty;//企业性质
	@Column(name="ESTABLISHDATE")
	private String establishDate;//成立日期
	@Column(name="APPROVEDATE")
	private String approveDate;//批准从事房地产业务日期
	@Column(name="DESCRIBE")
	private String describe;//企业描述
	@Column(name="DATAFROM")
	private String dataFrom;//数据来源
	@Column(name="ORGINNO")
	private String orginNo;//数据原编号
	@Column(name="DATAEXPLAIN")
	private String dataExplain;//数据说明
	@Column(name="LASTTIME")
	private String lastTime ;//数据最后更新时间
	@Column(name="REMARK")
	private String remark;//备注
	
	@Column(name="VERSIONTIME")
	private Date versionTime;
	@Column(name="VERSIONNUMBER")
	private BigDecimal versionNumber;
	@Column
	private String dataCenterId;
	
	/********添加的属性*********/
	private String isHavePro; //企业下是否有项目
	private Integer illegal ;//企业违规次数
	private Integer praise ;//表扬次数
	private Integer complaint ;//投诉次数
	public ArcCorpInfo(){
		
	}
	 public ArcCorpInfo(String corpNo) {
	        this.corpNo = corpNo;
	    }
	public ArcCorpInfo(String sysGuid, String corpNo, String corpType,
                       String state, String corpName, String shortName,
                       String certificateNo, String cityCode, String divisionCode,
                       String isForeign, String isBranch, String busiScope,
                       String registerAddress, String contactAddress, String postalCode,
                       String contactName, String phoneNo, String faxNo, String email,
                       String website, String credentialGrade, String credentialNo,
                       String credentialStart, String credentialEnd, Integer hasTitleNum,
                       Integer highTitleNum, Integer headCount, Integer middleTitleNum,
                       Integer primaryTitleNum, String licenseNo, String licenseStart,
                       String licenseEnd, BigDecimal netAsset, BigDecimal totalAsset,
                       BigDecimal registerAsset, String currencyType,
                       String generalManager, String legalName, String legalPhoneNo,
                       String legalCertType, String legalCertNo, String registerDate,
                       String registerTime, String registerUser, String recordDate,
                       String recordTime, String recordUser, String corpProperty,
                       String establishDate, String approveDate, String describe,
                       String dataFrom, String orginNo, String dataExplain,
                       String lastTime, String remark, String isHavePro, Integer illegal,
                       Integer praise, Integer complaint) {
		super();
		this.sysGuid = sysGuid;
		this.corpNo = corpNo;
		this.corpType = corpType;
		this.state = state;
		this.corpName = corpName;
		this.shortName = shortName;
		this.certificateNo = certificateNo;
		this.cityCode = cityCode;
		this.divisionCode = divisionCode;
		this.isForeign = isForeign;
		this.isBranch = isBranch;
		this.busiScope = busiScope;
		this.registerAddress = registerAddress;
		this.contactAddress = contactAddress;
		this.postalCode = postalCode;
		this.contactName = contactName;
		this.phoneNo = phoneNo;
		this.faxNo = faxNo;
		this.email = email;
		this.website = website;
		this.credentialGrade = credentialGrade;
		this.credentialNo = credentialNo;
		this.credentialStart = credentialStart;
		this.credentialEnd = credentialEnd;
		this.hasTitleNum = hasTitleNum;
		this.highTitleNum = highTitleNum;
		this.headCount = headCount;
		this.middleTitleNum = middleTitleNum;
		this.primaryTitleNum = primaryTitleNum;
		this.licenseNo = licenseNo;
		this.licenseStart = licenseStart;
		this.licenseEnd = licenseEnd;
		this.netAsset = netAsset;
		this.totalAsset = totalAsset;
		this.registerAsset = registerAsset;
		this.currencyType = currencyType;
		this.generalManager = generalManager;
		this.legalName = legalName;
		this.legalPhoneNo = legalPhoneNo;
		this.legalCertType = legalCertType;
		this.legalCertNo = legalCertNo;
		this.registerDate = registerDate;
		this.registerTime = registerTime;
		this.registerUser = registerUser;
		this.recordDate = recordDate;
		this.recordTime = recordTime;
		this.recordUser = recordUser;
		this.corpProperty = corpProperty;
		this.establishDate = establishDate;
		this.approveDate = approveDate;
		this.describe = describe;
		this.dataFrom = dataFrom;
		this.orginNo = orginNo;
		this.dataExplain = dataExplain;
		this.lastTime = lastTime;
		this.remark = remark;
		this.isHavePro = isHavePro;
		this.illegal = illegal;
		this.praise = praise;
		this.complaint = complaint;
	}
	public String getSysGuid() {
		return sysGuid;
	}
	public void setSysGuid(String sysGuid) {
		this.sysGuid = sysGuid;
	}
	public String getCorpNo() {
		return corpNo;
	}
	public void setCorpNo(String corpNo) {
		this.corpNo = corpNo;
	}
	public String getCorpType() {
		return corpType;
	}
	public void setCorpType(String corpType) {
		this.corpType = corpType;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getCertificateNo() {
		return certificateNo;
	}
	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getDivisionCode() {
		return divisionCode;
	}
	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}
	public String getIsForeign() {
		return isForeign;
	}
	public void setIsForeign(String isForeign) {
		this.isForeign = isForeign;
	}
	public String getIsBranch() {
		return isBranch;
	}
	public void setIsBranch(String isBranch) {
		this.isBranch = isBranch;
	}
	public String getBusiScope() {
		return busiScope;
	}
	public void setBusiScope(String busiScope) {
		this.busiScope = busiScope;
	}
	public String getRegisterAddress() {
		return registerAddress;
	}
	public void setRegisterAddress(String registerAddress) {
		this.registerAddress = registerAddress;
	}
	public String getContactAddress() {
		return contactAddress;
	}
	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getFaxNo() {
		return faxNo;
	}
	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getCredentialGrade() {
		return credentialGrade;
	}
	public void setCredentialGrade(String credentialGrade) {
		this.credentialGrade = credentialGrade;
	}
	public String getCredentialNo() {
		return credentialNo;
	}
	public void setCredentialNo(String credentialNo) {
		this.credentialNo = credentialNo;
	}
	public String getCredentialStart() {
		return credentialStart;
	}
	public void setCredentialStart(String credentialStart) {
		this.credentialStart = credentialStart;
	}
	public String getCredentialEnd() {
		return credentialEnd;
	}
	public void setCredentialEnd(String credentialEnd) {
		this.credentialEnd = credentialEnd;
	}
	public Integer getHasTitleNum() {
		return hasTitleNum;
	}
	public void setHasTitleNum(Integer hasTitleNum) {
		this.hasTitleNum = hasTitleNum;
	}
	public Integer getHighTitleNum() {
		return highTitleNum;
	}
	public void setHighTitleNum(Integer highTitleNum) {
		this.highTitleNum = highTitleNum;
	}
	public Integer getHeadCount() {
		return headCount;
	}
	public void setHeadCount(Integer headCount) {
		this.headCount = headCount;
	}
	public Integer getMiddleTitleNum() {
		return middleTitleNum;
	}
	public void setMiddleTitleNum(Integer middleTitleNum) {
		this.middleTitleNum = middleTitleNum;
	}
	public Integer getPrimaryTitleNum() {
		return primaryTitleNum;
	}
	public void setPrimaryTitleNum(Integer primaryTitleNum) {
		this.primaryTitleNum = primaryTitleNum;
	}
	public String getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	public String getLicenseStart() {
		return licenseStart;
	}
	public void setLicenseStart(String licenseStart) {
		this.licenseStart = licenseStart;
	}
	public String getLicenseEnd() {
		return licenseEnd;
	}
	public void setLicenseEnd(String licenseEnd) {
		this.licenseEnd = licenseEnd;
	}
	public BigDecimal getNetAsset() {
		return netAsset == null?BigDecimal.ZERO : netAsset;
	}
	public void setNetAsset(BigDecimal netAsset) {
		this.netAsset = netAsset;
	}
	public BigDecimal getTotalAsset() {
		return totalAsset == null?BigDecimal.ZERO : totalAsset;
	}
	public void setTotalAsset(BigDecimal totalAsset) {
		this.totalAsset = totalAsset;
	}
	public BigDecimal getRegisterAsset() {
		return registerAsset == null ? BigDecimal.ZERO :registerAsset ;
	}
	public void setRegisterAsset(BigDecimal registerAsset) {
		this.registerAsset = registerAsset;
	}
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	public String getGeneralManager() {
		return generalManager;
	}
	public void setGeneralManager(String generalManager) {
		this.generalManager = generalManager;
	}
	public String getLegalName() {
		return legalName;
	}
	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}
	public String getLegalPhoneNo() {
		return legalPhoneNo;
	}
	public void setLegalPhoneNo(String legalPhoneNo) {
		this.legalPhoneNo = legalPhoneNo;
	}
	public String getLegalCertType() {
		return legalCertType;
	}
	public void setLegalCertType(String legalCertType) {
		this.legalCertType = legalCertType;
	}
	public String getLegalCertNo() {
		return legalCertNo;
	}
	public void setLegalCertNo(String legalCertNo) {
		this.legalCertNo = legalCertNo;
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
	public String getCorpProperty() {
		return corpProperty;
	}
	public void setCorpProperty(String corpProperty) {
		this.corpProperty = corpProperty;
	}
	public String getEstablishDate() {
		return establishDate;
	}
	public void setEstablishDate(String establishDate) {
		this.establishDate = establishDate;
	}
	public String getApproveDate() {
		return approveDate;
	}
	public void setApproveDate(String approveDate) {
		this.approveDate = approveDate;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
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
	public String getIsHavePro() {
		return isHavePro;
	}
	public void setIsHavePro(String isHavePro) {
		this.isHavePro = isHavePro;
	}
	public Integer getIllegal() {
		return illegal;
	}
	public void setIllegal(Integer illegal) {
		this.illegal = illegal;
	}
	public Integer getPraise() {
		return praise;
	}
	public void setPraise(Integer praise) {
		this.praise = praise;
	}
	public Integer getComplaint() {
		return complaint;
	}
	public Date getVersionTime() {
		return versionTime;
	}
	public void setVersionTime(Date versionTime) {
		this.versionTime = versionTime;
	}
	public void setComplaint(Integer complaint) {
		this.complaint = complaint;
	}
	public BigDecimal getVersionNumber() {
		return versionNumber;
	}
	public void setVersionNumber(BigDecimal versionNumber) {
		this.versionNumber = versionNumber;
	}
	public String getDataCenterId() {
		return dataCenterId;
	}
	public void setDataCenterId(String dataCenterId) {
		this.dataCenterId = dataCenterId;
	}
}