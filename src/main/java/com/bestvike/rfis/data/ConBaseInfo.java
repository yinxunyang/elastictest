package com.bestvike.rfis.data;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: ConBaseInfo
 * @Description: 合同信息
 * @author madk
 * @date 2017年3月3日 下午4:04:01
 * @version V1.0
 */
@Entity
@Table(name="Con_BaseInfo")
public class ConBaseInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 状态：保存未提交
	 */
	public static final String CONBASEINFO_STATE_SAVE = "1";
	/**
	 * 状态：签订并提交
	 */
	public static final String CONBASEINFO_STATE_SUBMIT = "2";
	/**
	 * 状态：备案
	 */
	public static final String CONBASEINFO_STATE_VERIFY = "3";
	/**
	 * 状态：送达
	 */
	public static final String CONBASEINFO_STATE_SEND = "4";
	/**
	 * 状态：暂停备案
	 */
	public static final String CONBASEINFO_STATE_STOP = "S";
	/**
	 * 状态：注销
	 */
	public static final String CONBASEINFO_STATE_X = "X";
	/**
	 * 状态：删除
	 */
	public static final String CONBASEINFO_STATE_DEL = "D";
	/**
	 * 状态：不予备案
	 */
	public static final String CONBASEINFO_STATE_NO = "N";
	/**
	 * 付款方式：一次性付款
	 */
	public static final String CONBASEINFO_PAYTYPE_1 = "1";
	/**
	 * 付款方式：分期付款
	 */
	public static final String CONBASEINFO_PAYTYPE_2 = "2";
	/**
	 * 付款方式：抵押贷款付款
	 */
	public static final String CONBASEINFO_PAYTYPE_3 = "3";
	/**
	 * 付款方式：组合贷款
	 */
	public static final String CONBASEINFO_PAYTYPE_4 = "4";
	/**
	 * 付款方式：公积金贷款
	 */
	public static final String CONBASEINFO_PAYTYPE_5 = "5";
	/**
	 * 付款方式：其他
	 */
	public static final String CONBASEINFO_PAYTYPE_99 = "99";
	/**
	 * 合同类型：预售合同
	 */
	public static final String CONBASEINFO_CONTRACTTYPE_02 = "02";
	/**
	 * 合同类型：现售合同
	 */
	public static final String CONBASEINFO_CONTRACTTYPE_03 = "03";

	/**
	 * 合同数据来源： 来自系统，与之对应的是历史合同，用于青州系统，合同自动删除，撤销，只针对来源是网签系统的合同
	 */
	public static final String CONBASEINFO_DATAFROM_NORMAL = "00";
	/**
	 * 合同是否打印： Y
	 */
	public static final String CONBASEINFO_ISPRINT_Y = "Y";
	/**
	 * 合同是否打印： N
	 */
	public static final String CONBASEINFO_ISPRINT_N = "N";
	
	
	@Id
	@Column(name="SYSGUID")
	private String sysGuid;
	@Column(name="CONTRACTNO")
	private String contractNo;
	@Column(name="RECORDNO")
	private String recordNo;
	@Column(name="PASSWORD")
	private String password;
	@Column(name="CONTRACTTYPE")
	private String contractType;
	@Column(name="PROJECTNO")
	private String projectNo;
	@Column(name="TEMPDATAID")
	private String tempDataId;
	@Column(name="PLANDELIVER")
	private String planDeliver;
	@Column(name="SALESSCORE")
	private String salesScore;
	@Column(name="CONTRACTSTATE")
	private String contractState;
	@Column(name="SIGNDATE")
	private String signDate;
	@Column(name="SIGNTIME")
	private String signTime;
	@Column(name="SIGNUSER")
	private String signUser;
	@Column(name="MAINHOUSEPRICE")
	private BigDecimal mainHousePrice;
	@Column(name="TOTALPRICE")
	private BigDecimal totalPrice;
	@Column(name="PAYTYPE")
	private String payType;
	@Column(name="VALIDATEDATE")
	private String validateDate;
	@Column(name="RECORDDATE")
	private String recordDate;
	@Column(name="RECORDTIME")
	private String recordTime;
	@Column(name="RECORDUSER")
	private String recordUser;
	@Column(name="RECORDNUM")
	private BigDecimal recordNum;
	@Column(name="ISHISTORICAL")
	private String isHistorical;
	@Column(name="ISDOWNPAYMENT")
	private String isDownPayment;
	@Column(name="CANCELREASON")
	private String cancelReason;
	@Column(name="CANCELDATE")
	private String cancelDate;
	@Column(name="CANCELTIME")
	private String cancelTime;
	@Column(name="CANCELUSER")
	private String cancelUser;
	@Column(name="RECORDDATEFST")
	private String recordDateFst;	//第一次保存日期
	@Column(name="BLDNO")
	private String bldNo;	//主房自然幢编号
	@Column(name="LASTTIME")
	private String lastTime;
	@Column(name="BUYNAMES")
	private String buyNames;//房屋买受人（仅用于合同的查询）
	@Column(name="BUYCERTNOS")
	private String buyCertNos;//房屋买受人证件号（仅用于合同查询）
	@Column(name="REMARK")
	private String remark;
	@Column(name="BANKID")
	private String bankId;
	@Column(name="BANKNAME")
	private String bankName;
	@Column(name="ACCID")
	private String accId;
	@Column(name="ACCOUNT")
	private String account;
	@Column(name="ACCNAME")
	private String accName;
	@Column(name="ISDELIVER")
	private String isDeliver;
	@Column(name="DELIVERDATE")
	private String deliverDate; //送达日期
	@Column(name="DELIVERTIME")
	private String deliverTime;
	@Column(name="DELIVERUSER")
	private String deliverUser;
	@Column(name="DELIVERNUM")
	private String deliverNum;
	@Column(name="LOANBANKCODE")
	private String loanBankCode; // 贷款银行编号
	@Column(name="NORECORDREASON")
	private String noRecordReason; //不予备案原因
	@Column
	private BigDecimal versionNumber; // 版本
	@Column
	private Date versionTime; // 版本时间
	@Column
	private String dataCenterId; // datacenter的合同表的主键
	@Column
	private String houseGuid; // 主房的guid
	@Column
	private BigDecimal supplementNum; // 补备案份数
	@Column(name="SIGNNUM")
	private BigDecimal signNum; //签订份数
	@Column
	private String isCanceling; //是否撤销在途
	@Column
	private String landRightNo;//土地证
	@Column
	private String consPmtNo;//施工许可证
	@Column
	private String landPmtNo;//用地规划许可
	@Column
	private String consPlPmtNo;//建筑规划许可
	@Column
	private String mangPmtNo;//经营许可证
	@Column(name="ISPRINT")
	private String isPrint ;
	@Column(name="PRINTNUM")
	private BigDecimal printNum ;
	@Column(name="PRINTDATE")
	private String printDate ;
	
	private int payNum;	//分期付款次数
	
	private String contractName;
	private String houseId; // datacenter中房屋表的主键
	private String address; // 主房地址
	private String autoDepReq; // 合同签订后是否自动发起交存申请
	private String title; // （青州）合同打印如果不能打印，提示信息
	
	private String isCancel;//是否发起撤销
	private String cancelSureDate;//撤销发起日期
	private String cancelSureTime;//撤销发起时间
	private String cancelSureUser;//撤销发起人
	private String cancelSureDescribe ;//撤销发起原因
	private String dataFrom;
	private String depState;
	@Column(name="NAME")
	private String name;
	@Column(name="CERTCODE")
	private String certcode;
	@Column(name="CPPID")
	private String cppid;
	@Column(name="SIGNINGPERSONNELNAME")
	private String signingPersonnelName;
	@Column(name="SIGNINGPERSONNELCERTCODE")
	private String signingPersonnelCertcode;
	@Column(name="SIGNINGPERSONNELCPPID")
	private String signingPersonnelCppid;
	
	private String version;//版本号
	/**
	 * 是否迁移到tradeRecode表，Y是，N否
	 */
	@Column(name = "ISMERGETRADE")
	private String isMergeTrade;

	/**
	 * 日志表logData的ID
	 */
	@Column(name = "LOGID")
	private String logId;

	public String getSysGuid() {
		return sysGuid;
	}
	public String getContractNo() {
		return contractNo;
	}
	public String getPassword() {
		return password;
	}
	public String getContractType() {
		return contractType;
	}
	public String getProjectNo() {
		return projectNo;
	}
	public String getPlanDeliver() {
		return planDeliver;
	}
	public String getSalesScore() {
		return salesScore;
	}
	public String getContractState() {
		return contractState;
	}
	public String getSignDate() {
		return signDate;
	}
	public String getSignTime() {
		return signTime;
	}
	public String getSignUser() {
		return signUser;
	}
	public BigDecimal getMainHousePrice() {
		return mainHousePrice;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setMainHousePrice(BigDecimal mainHousePrice) {
		this.mainHousePrice = mainHousePrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getValidateDate() {
		return validateDate;
	}
	public String getRecordDate() {
		return recordDate;
	}
	public String getRecordTime() {
		return recordTime;
	}
	public String getRecordUser() {
		return recordUser;
	}
	public BigDecimal getRecordNum() {
		return recordNum;
	}
	public String getIsHistorical() {
		return isHistorical;
	}
	public String getIsDownPayment() {
		return isDownPayment;
	}
	public String getCancelReason() {
		return cancelReason;
	}
	public String getCancelDate() {
		return cancelDate;
	}
	public String getCancelTime() {
		return cancelTime;
	}
	public String getCancelUser() {
		return cancelUser;
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
	public void setPassword(String password) {
		this.password = password;
	}
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}
	public void setPlanDeliver(String planDeliver) {
		this.planDeliver = planDeliver;
	}
	public void setSalesScore(String salesScore) {
		this.salesScore = salesScore;
	}
	public void setContractState(String contractState) {
		this.contractState = contractState;
	}
	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}
	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}
	public void setSignUser(String signUser) {
		this.signUser = signUser;
	}
	public void setValidateDate(String validateDate) {
		this.validateDate = validateDate;
	}
	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}
	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}
	public void setRecordUser(String recordUser) {
		this.recordUser = recordUser;
	}
	public void setRecordNum(BigDecimal recordNum) {
		this.recordNum = recordNum;
	}
	public void setIsHistorical(String isHistorical) {
		this.isHistorical = isHistorical;
	}
	public void setIsDownPayment(String isDownPayment) {
		this.isDownPayment = isDownPayment;
	}
	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}
	public void setCancelDate(String cancelDate) {
		this.cancelDate = cancelDate;
	}
	public void setCancelTime(String cancelTime) {
		this.cancelTime = cancelTime;
	}
	public void setCancelUser(String cancelUser) {
		this.cancelUser = cancelUser;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTempDataId() {
		return tempDataId;
	}
	public void setTempDataId(String tempDataId) {
		this.tempDataId = tempDataId;
	}
	public String getContractName() {
		return contractName;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	public String getRecordDateFst() {
		return recordDateFst;
	}
	public String getBldNo() {
		return bldNo;
	}
	public void setRecordDateFst(String recordDateFst) {
		this.recordDateFst = recordDateFst;
	}
	public void setBldNo(String bldNo) {
		this.bldNo = bldNo;
	}
	public int getPayNum() {
		return payNum;
	}
	public void setPayNum(int payNum) {
		this.payNum = payNum;
	}
	public String getBuyNames() {
		return buyNames;
	}
	public void setBuyNames(String buyNames) {
		this.buyNames = buyNames;
	}
	public String getBankId() {
		return bankId;
	}
	public String getAccId() {
		return accId;
	}
	public String getAccount() {
		return account;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public void setAccId(String accId) {
		this.accId = accId;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
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
	public String getHouseId() {
		return houseId;
	}
	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getHouseGuid() {
		return houseGuid;
	}
	public void setHouseGuid(String houseGuid) {
		this.houseGuid = houseGuid;
	}
	public String getRecordNo() {
		return recordNo;
	}
	public void setRecordNo(String recordNo) {
		this.recordNo = recordNo;
	}
	public String getIsDeliver() {
		return isDeliver;
	}
	public void setIsDeliver(String isDeliver) {
		this.isDeliver = isDeliver;
	}
	public String getDeliverDate() {
		return deliverDate;
	}
	public void setDeliverDate(String deliverDate) {
		this.deliverDate = deliverDate;
	}
	public String getDeliverTime() {
		return deliverTime;
	}
	public void setDeliverTime(String deliverTime) {
		this.deliverTime = deliverTime;
	}
	public String getDeliverUser() {
		return deliverUser;
	}
	public void setDeliverUser(String deliverUser) {
		this.deliverUser = deliverUser;
	}
	public String getDeliverNum() {
		return deliverNum;
	}
	public void setDeliverNum(String deliverNum) {
		this.deliverNum = deliverNum;
	}
	public String getBuyCertNos() {
		return buyCertNos;
	}
	public void setBuyCertNos(String buyCertNos) {
		this.buyCertNos = buyCertNos;
	}
	public String getLoanBankCode() {
		return loanBankCode;
	}
	public void setLoanBankCode(String loanBankCode) {
		this.loanBankCode = loanBankCode;
	}
	public String getAutoDepReq() {
		return autoDepReq;
	}
	public void setAutoDepReq(String autoDepReq) {
		this.autoDepReq = autoDepReq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public BigDecimal getSupplementNum() {
		return supplementNum;
	}
	public void setSupplementNum(BigDecimal supplementNum) {
		this.supplementNum = supplementNum;
	}
	public BigDecimal getSignNum() {
		return signNum;
	}
	public void setSignNum(BigDecimal signNum) {
		this.signNum = signNum;
	}
	public String getIsCanceling() {
		return isCanceling;
	}
	public void setIsCanceling(String isCanceling) {
		this.isCanceling = isCanceling;
	}
	public String getNoRecordReason() {
		return noRecordReason;
	}
	public void setNoRecordReason(String noRecordReason) {
		this.noRecordReason = noRecordReason;
	}
	public String getIsCancel() {
		return isCancel;
	}
	public void setIsCancel(String isCancel) {
		this.isCancel = isCancel;
	}
	public String getCancelSureDate() {
		return cancelSureDate;
	}
	public void setCancelSureDate(String cancelSureDate) {
		this.cancelSureDate = cancelSureDate;
	}
	public String getCancelSureTime() {
		return cancelSureTime;
	}
	public void setCancelSureTime(String cancelSureTime) {
		this.cancelSureTime = cancelSureTime;
	}
	public String getCancelSureUser() {
		return cancelSureUser;
	}
	public void setCancelSureUser(String cancelSureUser) {
		this.cancelSureUser = cancelSureUser;
	}
	public String getCancelSureDescribe() {
		return cancelSureDescribe;
	}
	public void setCancelSureDescribe(String cancelSureDescribe) {
		this.cancelSureDescribe = cancelSureDescribe;
	}
	public String getLandRightNo() {
		return landRightNo;
	}
	public void setLandRightNo(String landRightNo) {
		this.landRightNo = landRightNo;
	}
	public String getConsPmtNo() {
		return consPmtNo;
	}
	public void setConsPmtNo(String consPmtNo) {
		this.consPmtNo = consPmtNo;
	}
	public String getLandPmtNo() {
		return landPmtNo;
	}
	public void setLandPmtNo(String landPmtNo) {
		this.landPmtNo = landPmtNo;
	}
	public String getConsPlPmtNo() {
		return consPlPmtNo;
	}
	public void setConsPlPmtNo(String consPlPmtNo) {
		this.consPlPmtNo = consPlPmtNo;
	}
	public String getMangPmtNo() {
		return mangPmtNo;
	}
	public void setMangPmtNo(String mangPmtNo) {
		this.mangPmtNo = mangPmtNo;
	}

	public String getDataFrom() {
		return dataFrom;
	}

	public void setDataFrom(String dataFrom) {
		this.dataFrom = dataFrom;
	}
	public String getIsPrint() {
		return isPrint;
	}
	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
	}
	
	public BigDecimal getPrintNum() {
		return printNum;
	}
	public void setPrintNum(BigDecimal printNum) {
		this.printNum = printNum;
	}
	public String getPrintDate() {
		return printDate;
	}
	public void setPrintDate(String printDate) {
		this.printDate = printDate;
	}
	public String getDepState() {
		return depState;
	}
	public void setDepState(String depState) {
		this.depState = depState;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCertcode() {
		return certcode;
	}
	public void setCertcode(String certcode) {
		this.certcode = certcode;
	}
	public String getCppid() {
		return cppid;
	}
	public void setCppid(String cppid) {
		this.cppid = cppid;
	}
	public String getSigningPersonnelName() {
		return signingPersonnelName;
	}
	public void setSigningPersonnelName(String signingPersonnelName) {
		this.signingPersonnelName = signingPersonnelName;
	}
	public String getSigningPersonnelCertcode() {
		return signingPersonnelCertcode;
	}
	public void setSigningPersonnelCertcode(String signingPersonnelCertcode) {
		this.signingPersonnelCertcode = signingPersonnelCertcode;
	}
	public String getSigningPersonnelCppid() {
		return signingPersonnelCppid;
	}
	public void setSigningPersonnelCppid(String signingPersonnelCppid) {
		this.signingPersonnelCppid = signingPersonnelCppid;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}


	public String getIsMergeTrade() {
		return isMergeTrade;
	}

	public void setIsMergeTrade(String isMergeTrade) {
		this.isMergeTrade = isMergeTrade;
	}

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}
}
