package com.bestvike.rfis.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @ClassName: ArcBuildInfo
 * @author songhao
 * @date 2016-9-20 下午1:43:22
 * @version V1.0
 */

@Entity
@Table(name="Arc_BuildInfo")
public class ArcBuildInfo implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 自然幢状态：正常
	 */
	public static final String BUILD_STATE_NORMAL = "0";
	/**
	 * 自然幢状态：待备案
	 */
	public static final String BUILD_STATE_WAIT_VERIFY = "1";
	/**
	 * 自然幢状态：临时保存
	 */
	public static final String BUILD_STATE_TEMP = "T";
	/**
	 * 自然幢状态：注销
	 */
	public static final String BUILD_STATE_CANCEL = "D";
	/**
	 * 自然幢状态：删除
	 */
	public static final String BUILD_STATE_DELETE = "X";
	/**
	 * 房屋生成方式：按单元
	 */
	public static final String BUILD_HOUSEADDRTYPE_CELL = "0";
	/**
	 * 房屋生成方式：按楼层
	 */
	public static final String BUILD_HOUSEADDRTYPE_FLOOR = "1";
	/**
	 * 开盘状态：未开盘
	 */
	public static final String BUILD_SELLSTATE_NO_OPEN = "1";
	/**
	 * 开盘状态：预售开盘
	 */
	public static final String BUILD_SELLSTATE_PRE_OPEN = "2";
	/**
	 * 开盘状态：现售开盘
	 */
	public static final String BUILD_SELLSTATE_NOW_OPEN = "3";
	/**
	 * 开盘状态：暂停开盘
	 */
	public static final String BUILD_SELLSTATE_STOP_OPEN = "4";
	/**
	 * 开盘状态：现售开盘申请
	 */
	public static final String BUILD_SELLSTATE_NOW_REQ = "5";
	/**
	 * 开盘状态：预售开盘申请
	 */
	public static final String BUILD_SELLSTATE_PRE_REQ = "6";
	/**
	 * 开盘状态：待开盘
	 */
	public static final String BUILD_SELLSTATE_WAIT_OPEN = "7";
	/**
	 * 房屋位置生成方式：按单元
	 */
	public static final String BUILD_HOUSEADDR_TYPE_CELL = "0";
	/**
	 * 房屋位置生成方式：按楼层
	 */
	public static final String BUILD_HOUSEADDR_TYPE_FLOOR = "1";
	/**
	 * 楼层属性：多层
	 */
	public static final String HEIGHT_ATTR_SMALL = "1";
	/**
	 * 楼层属性：小高层
	 */
	public static final String HEIGHT_ATTR_MIDDLE = "2";
	/**
	 * 楼层属性：多层
	 */
	public static final String HEIGHT_ATTR_BIG = "3";
	/**
	 * 楼盘功能属性：住宅（测绘导入默认值）
	 */
	public static final String BUILD_FUNCTION_LIVE = "1";
	/**
	 * 房屋性质：市场化商品房（测绘导入默认值）
	 */
	public static final String BUILD_HOUSEPROP_DEFAULT = "0";
	/**
	 * 产业类型：其他（测绘导入默认值）
	 */
	public static final String BUILD_PRODUCTTYPE_OTHER = "99";
	/**
	 * 单元方向：东西（测绘导入默认值）
	 */
	public static final String BUILD_CELLDIRECTION_DEFAULT = "1";
	
	/**
	 * 预售许可状态 1_未许可
	 */
	public static final String BUILD_PERSELLPERMIT_N = "1";
	/**
	 * 预售许可状态 2_正常
	 */
	public static final String BUILD_PERSELLPERMIT_Y = "2";
	/**
	 * 预售许可状态 3_已过期
	 */
	public static final String BUILD_PERSELLPERMIT_INVALID = "3";
	/**
	 * 预售许可状态：申请在途
	 */
	public static final String BUILD_PERSELLPERMIT_ING = "4";
	
	@Id
	@Column(name="SYSGUID")
	private String sysGuid;
	@Column(name="BLDNO")
	private String bldNo; // 楼盘编号
	@Column(name="BLDNAME")
	private String bldName; // 楼盘名称
	@Column(name="DIVISIONCODE")
	private String divisionCode; // 所属行政区
	@Column(name="STATE")
	private String state; // 状态
	@Column(name="ADDRESS")
	private String address; // 楼盘地址
	@Column(name="PROJECTNO")
	private String projectNo; // 项目编号
	@Column(name="ISPERMITPRE")
	private String isPermitPre; // 是否取得预售许可证
	@Column(name="PRESELLNO")
	private String presellNo; // 预售许可证号
	@Column(name="CASHSELLNO")
	private String cashSellNo; // 现售备案申请号
	@Column(name="SALESTATE")
	private String saleState; // 开盘状态
	@Column(name="ISSUPER")
	private String isSuper; // 是否建立监管
	@Column(name="SUPERRCNO")
	private String superrcNo; // 资金监管备案号
	@Column(name="SUPERFUND")
	private BigDecimal superFund; // 监管金额
	@Column(name="HEIGHTATTR")
	private String heightAttr; // 楼层属性
	@Column(name="BLDFUNCTION")
	private String bldFunction; // 楼盘功能属性
	@Column(name="HOUSEPROP")
	private String houseProp; // 房屋性质
	@Column(name="FRAMEATTR")
	private String frameAttr; // 结构属性
	@Column(name="PRODUCTTYPE")
	private String productType; // 产业类型
	@Column(name="HOUSEADDRTYPE")
	private String houseAddrType; // 房屋位置生成方式
	@Column(name="CELLDIRECTION")
	private String cellDirection; // 单元方向
	@Column(name="COSTUNITPRICE")
	private BigDecimal costUnitPrice; // 建安成本单价
	@Column(name="OVERFLOOR")
	private Integer overFloor; // 地上楼层数
	@Column(name="UNDERFLOOR")
	private Integer underFloor; // 地下楼层数
	@Column(name="CELLNUM")
	private BigDecimal cellNum; // 单元数
	@Column(name="TOTALHOUSE")
	private BigDecimal totalHouse; // 总套数
	@Column(name="CANSOLDNUM")
	private BigDecimal canSoldNum; // 预售套数
	@Column(name="PRELIVENUM")
	private BigDecimal preliveNum; // 预售住宅套数
	@Column(name="PRETRADENUM")
	private BigDecimal pretradeNum; // 预售商业套数
	@Column(name="OTHERNUM")
	private BigDecimal otherNum; // 其他套数
	@Column(name="MINPRICE")
	private BigDecimal minPrice; // 最低价
	@Column(name="MAXPRICE")
	private BigDecimal maxPrice; // 最高价
	@Column(name="AVGPRICE")
	private BigDecimal avgPrice; // 平均价
	@Column(name="CONSTRUCTAREA")
	private BigDecimal constructArea; // 建筑面积
	@Column(name="CANSOLDAREA")
	private BigDecimal canSoldArea; // 建筑面积
	@Column(name="TRADEAREA")
	private BigDecimal tradeArea; // 商用面积
	@Column(name="LIVEAREA")
	private BigDecimal liveArea; // 住宅面积
	@Column(name="OTHERAREA")
	private BigDecimal otherArea; // 其他面积
	@Column(name="PLANSALES")
	private BigDecimal planSales; // 计划销售总额(元)
	@Column(name="PLANINVEST")
	private BigDecimal planInvest; // 计划总投资(元)
	@Column(name="COVERAREA")
	private BigDecimal coverArea; // 占地面积
	@Column(name="DEVELOPNO")
	private String developNo; // 开发公司编号
	@Column(name="SERVICENO")
	private String serviceNo; // 物业公司编号
	@Column(name="OWNERSNO")
	private String ownersNo; // 业主委员会编号
	@Column(name="STARTDATE")
	private String startDate; // 开工日期
	@Column(name="FINISHDATE")
	private String finishDate; // 竣工日期
	@Column(name="PRESELLDATE")
	private String presellDate; // 预售日期
	@Column(name="PLANDELIVERDATE")
	private String planDeliverDate; // 计划交付日期
	@Column(name="OPENDATE")
	private String openDate; // 开盘日期
	@Column(name="PLOTNO")
	private String plotNo; // 测绘编号
	@Column(name="LANDNO")
	private String landNo; // 土地证号
	@Column(name="CONSTRPERMITNO")
	private String constrPermitNo; // 施工证号
	@Column(name="LANDPLANPERMITNO")
	private String landPlanPermitNo; // 建设用地规划证号
	@Column(name="CONSTRPLANPERMITNO")
	private String constrPlanPermitNo; // 建设工程规划证号
	@Column(name="MANAGPERMITNO")
	private String managPermitNo; // 开发经营许可证号
	@Column(name="REGISTERDATE")
	private String registerDate; // 录入日期
	@Column(name="REGISTERTIME")
	private String registerTime; // 录入时间
	@Column(name="REGISTERUSER")
	private String registerUser; // 录入用户
	@Column(name="RECORDDATE")
	private String recordDate; // 备案日期
	@Column(name="RECORDTIME")
	private String recordTime; // 备案时间
	@Column(name="RECORDUSER")
	private String recordUser; // 备案用户
	@Column(name="ISPASS")
	private String isPass; // 是否完成验收
	@Column(name="PASSDATE")
	private String passDate; // 验收日期
	@Column(name="PASSOPER")
	private String passOper; // 验收操作人
	@Column(name="ISPLEDGE")
	private String isPledge; // 是否抵押
	@Column(name="PLEDGENO")
	private String pledgeNo; // 抵押编号
	@Column(name="PLEDGEDATE")
	private String pledgeDate; // 抵押日期
	@Column(name="PLEDGEOPER")
	private String pledgeOper; // 抵押操作人
	@Column(name="ISCLOSED")
	private String isClosed; // 是否查封
	@Column(name="CLOSEDNO")
	private String closedNo; // 查封登记编号
	@Column(name="CLOSEDDATE")
	private String closedDate; // 查封登记日期
	@Column(name="REMVCLOSED")
	private String remvClosed; // 解除查封日期
	@Column(name="ISFROZEN")
	private String isFrozen; // 是否冻结
	@Column(name="FROZENDATE")
	private String frozenDate; // 冻结操作日期
	@Column(name="FROZENOPER")
	private String frozenOper; // 冻结操作人
	@Column(name="ISINITREGISTER")
	private String isInitRegister; // 是否初始登记
	@Column(name="INITREGDATE")
	private String initRegDate; // 初始登记时间
	@Column(name="FITMENT")
	private String fitment; // 装修情况说明
	@Column(name="DATAFROM")
	private String dataFrom; // 数据来源
	@Column(name="ORGINNO")
	private String orginNo; // 数据原编号
	@Column(name="DATAEXPLAIN")
	private String dataExplain; // 数据说明
	@Column(name="LASTTIME")
	private String lastTime; // 数据最后更新时间
	@Column(name="REMARK")
	private String remark; // 备注
	@Column
	private BigDecimal versionNumber;
	@Column
	private Date versionTime;
	@Column
	private String dataCenterId;
	@Column
	private BigDecimal realInvest; // 实际完成投资额，现售备案申请时填写
	@Column
	private BigDecimal conCancelNum; // 自然幢下合同自动撤销次数

	
	// flag:判断是保存还是提交
	private String flag;
	// isCompany:是否是开发公司。 Y_是; N_否
	private String isCompany;
	// 开发公司名称
	private String developName;
	// 物业公司名称
	private String serviceName;
	// 业主委员会名称
	private String ownersName;

	private String corpName ;
	private String corpNo;
	private String projectName ;
	private String falseContractArea;//未实测房屋数
	private String contractType; //楼盘可以签订的合同类型
	
	private String perState;		//预售证状态
	private String perStopDate;		//预售证有效截止日期
	private BigDecimal storageNum; // 仓储套数
	private BigDecimal storageArea; // 仓储面积
	private BigDecimal carportNum; // 车库套数
	private BigDecimal carportArea; // 车库面积
	private String projectId; // datacenter中的项目表的主键
	private String serviceId; // datacenter中企业表的主键（物业公司）
	private String ownersId; // datacenter中企业表的主键（业主委员会）
	private String developId; // datacenter中企业表的主键（开发企业）
	private String frameAttrStr;
	private BigDecimal floorNum;
	private String investRate;
	private BigDecimal totalArea; //
	private BigDecimal totalNum; //
	private BigDecimal otherNums; //
	private BigDecimal otherAreas;

	public Integer getOverFloor() {
		return overFloor;
	}

	public void setOverFloor(Integer overFloor) {
		this.overFloor = overFloor;
	}

	public Integer getUnderFloor() {
		return underFloor;
	}

	public void setUnderFloor(Integer underFloor) {
		this.underFloor = underFloor;
	}

	public String getSysGuid() {
		return sysGuid;
	}
	public void setSysGuid(String sysGuid) {
		this.sysGuid = sysGuid;
	}
	public String getBldNo() {
		return bldNo;
	}
	public void setBldNo(String bldNo) {
		this.bldNo = bldNo;
	}
	public String getBldName() {
		return bldName;
	}
	public void setBldName(String bldName) {
		this.bldName = bldName;
	}
	public String getDivisionCode() {
		return divisionCode;
	}
	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}
	public String getIsPermitPre() {
		return isPermitPre;
	}
	public void setIsPermitPre(String isPermitPre) {
		this.isPermitPre = isPermitPre;
	}
	public String getPresellNo() {
		return presellNo;
	}
	public void setPresellNo(String presellNo) {
		this.presellNo = presellNo;
	}
	public String getCashSellNo() {
		return cashSellNo;
	}
	public void setCashSellNo(String cashSellNo) {
		this.cashSellNo = cashSellNo;
	}
	public String getSaleState() {
		return saleState;
	}
	public void setSaleState(String saleState) {
		this.saleState = saleState;
	}
	public String getIsSuper() {
		return isSuper;
	}
	public void setIsSuper(String isSuper) {
		this.isSuper = isSuper;
	}
	public String getSuperrcNo() {
		return superrcNo;
	}
	public void setSuperrcNo(String superrcNo) {
		this.superrcNo = superrcNo;
	}
	public BigDecimal getSuperFund() {
		return superFund == null ? BigDecimal.ZERO : superFund;
	}
	public void setSuperFund(BigDecimal superFund) {
		this.superFund = superFund;
	}
	public String getHeightAttr() {
		return heightAttr;
	}
	public void setHeightAttr(String heightAttr) {
		this.heightAttr = heightAttr;
	}
	public String getBldFunction() {
		return bldFunction;
	}
	public void setBldFunction(String bldFunction) {
		this.bldFunction = bldFunction;
	}
	public String getHouseProp() {
		return houseProp;
	}
	public void setHouseProp(String houseProp) {
		this.houseProp = houseProp;
	}
	public String getFrameAttr() {
		return frameAttr;
	}
	public void setFrameAttr(String frameAttr) {
		this.frameAttr = frameAttr;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getHouseAddrType() {
		return houseAddrType;
	}
	public void setHouseAddrType(String houseAddrType) {
		this.houseAddrType = houseAddrType;
	}
	public String getCellDirection() {
		return cellDirection;
	}
	public void setCellDirection(String cellDirection) {
		this.cellDirection = cellDirection;
	}
	public BigDecimal getCostUnitPrice() {
		return costUnitPrice == null ? BigDecimal.ZERO : costUnitPrice;
	}
	public void setCostUnitPrice(BigDecimal costUnitPrice) {
		this.costUnitPrice = costUnitPrice;
	}
	public BigDecimal getCellNum() {
		return cellNum == null ? BigDecimal.ZERO : cellNum;
	}
	public void setCellNum(BigDecimal cellNum) {
		this.cellNum = cellNum;
	}
	public BigDecimal getTotalHouse() {
		return totalHouse == null ? BigDecimal.ZERO : totalHouse;
	}
	public void setTotalHouse(BigDecimal totalHouse) {
		this.totalHouse = totalHouse;
	}
	public BigDecimal getPreliveNum() {
		return preliveNum == null ? BigDecimal.ZERO : preliveNum;
	}
	public void setPreliveNum(BigDecimal preliveNum) {
		this.preliveNum = preliveNum;
	}
	public BigDecimal getPretradeNum() {
		return pretradeNum == null ? BigDecimal.ZERO : pretradeNum;
	}
	public void setPretradeNum(BigDecimal pretradeNum) {
		this.pretradeNum = pretradeNum;
	}
	public BigDecimal getOtherNum() {
		return otherNum == null ? BigDecimal.ZERO : otherNum;
	}
	public void setOtherNum(BigDecimal otherNum) {
		this.otherNum = otherNum;
	}
	public BigDecimal getMinPrice() {
		return minPrice == null ? BigDecimal.ZERO : minPrice;
	}
	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}
	public BigDecimal getMaxPrice() {
		return maxPrice == null ? BigDecimal.ZERO : maxPrice;
	}
	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}
	public BigDecimal getAvgPrice() {
		return avgPrice == null ? BigDecimal.ZERO : avgPrice;
	}
	public void setAvgPrice(BigDecimal avgPrice) {
		this.avgPrice = avgPrice;
	}
	public BigDecimal getConstructArea() {
		return constructArea == null ? BigDecimal.ZERO : constructArea;
	}
	public void setConstructArea(BigDecimal constructArea) {
		this.constructArea = constructArea;
	}
	public BigDecimal getTradeArea() {
		return tradeArea == null ? BigDecimal.ZERO : tradeArea;
	}
	public void setTradeArea(BigDecimal tradeArea) {
		this.tradeArea = tradeArea;
	}
	public BigDecimal getLiveArea() {
		return liveArea == null ? BigDecimal.ZERO : liveArea;
	}
	public void setLiveArea(BigDecimal liveArea) {
		this.liveArea = liveArea;
	}
	public BigDecimal getOtherArea() {
		return otherArea == null ? BigDecimal.ZERO : otherArea;
	}
	public void setOtherArea(BigDecimal otherArea) {
		this.otherArea = otherArea;
	}
	public BigDecimal getPlanSales() {
		return planSales == null ? BigDecimal.ZERO : planSales;
	}
	public void setPlanSales(BigDecimal planSales) {
		this.planSales = planSales;
	}
	public BigDecimal getPlanInvest() {
		return planInvest == null ? BigDecimal.ZERO : planInvest;
	}
	public void setPlanInvest(BigDecimal planInvest) {
		this.planInvest = planInvest;
	}
	public BigDecimal getCoverArea() {
		return coverArea == null ? BigDecimal.ZERO : coverArea;
	}
	public void setCoverArea(BigDecimal coverArea) {
		this.coverArea = coverArea;
	}
	public String getDevelopNo() {
		return developNo;
	}
	public void setDevelopNo(String developNo) {
		this.developNo = developNo;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}
	public String getPresellDate() {
		return presellDate;
	}
	public void setPresellDate(String presellDate) {
		this.presellDate = presellDate;
	}
	public String getPlanDeliverDate() {
		return planDeliverDate;
	}
	public void setPlanDeliverDate(String planDeliverDate) {
		this.planDeliverDate = planDeliverDate;
	}
	public String getOpenDate() {
		return openDate;
	}
	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}
	public String getPlotNo() {
		return plotNo;
	}
	public void setPlotNo(String plotNo) {
		this.plotNo = plotNo;
	}
	public String getLandNo() {
		return landNo;
	}
	public void setLandNo(String landNo) {
		this.landNo = landNo;
	}
	public String getConstrPermitNo() {
		return constrPermitNo;
	}
	public void setConstrPermitNo(String constrPermitNo) {
		this.constrPermitNo = constrPermitNo;
	}
	public String getLandPlanPermitNo() {
		return landPlanPermitNo;
	}
	public void setLandPlanPermitNo(String landPlanPermitNo) {
		this.landPlanPermitNo = landPlanPermitNo;
	}
	public String getConstrPlanPermitNo() {
		return constrPlanPermitNo;
	}
	public void setConstrPlanPermitNo(String constrPlanPermitNo) {
		this.constrPlanPermitNo = constrPlanPermitNo;
	}
	public String getManagPermitNo() {
		return managPermitNo;
	}
	public void setManagPermitNo(String managPermitNo) {
		this.managPermitNo = managPermitNo;
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
	public String getIsPass() {
		return isPass;
	}
	public void setIsPass(String isPass) {
		this.isPass = isPass;
	}
	public String getPassDate() {
		return passDate;
	}
	public void setPassDate(String passDate) {
		this.passDate = passDate;
	}
	public String getPassOper() {
		return passOper;
	}
	public void setPassOper(String passOper) {
		this.passOper = passOper;
	}
	public String getIsPledge() {
		return isPledge;
	}
	public void setIsPledge(String isPledge) {
		this.isPledge = isPledge;
	}
	public String getPledgeNo() {
		return pledgeNo;
	}
	public void setPledgeNo(String pledgeNo) {
		this.pledgeNo = pledgeNo;
	}
	public String getPledgeDate() {
		return pledgeDate;
	}
	public void setPledgeDate(String pledgeDate) {
		this.pledgeDate = pledgeDate;
	}
	public String getPledgeOper() {
		return pledgeOper;
	}
	public void setPledgeOper(String pledgeOper) {
		this.pledgeOper = pledgeOper;
	}
	public String getIsClosed() {
		return isClosed;
	}
	public void setIsClosed(String isClosed) {
		this.isClosed = isClosed;
	}
	public String getClosedNo() {
		return closedNo;
	}
	public void setClosedNo(String closedNo) {
		this.closedNo = closedNo;
	}
	public String getClosedDate() {
		return closedDate;
	}
	public void setClosedDate(String closedDate) {
		this.closedDate = closedDate;
	}
	public String getRemvClosed() {
		return remvClosed;
	}
	public void setRemvClosed(String remvClosed) {
		this.remvClosed = remvClosed;
	}
	public String getIsFrozen() {
		return isFrozen;
	}
	public void setIsFrozen(String isFrozen) {
		this.isFrozen = isFrozen;
	}
	public String getFrozenDate() {
		return frozenDate;
	}
	public void setFrozenDate(String frozenDate) {
		this.frozenDate = frozenDate;
	}
	public String getFrozenOper() {
		return frozenOper;
	}
	public void setFrozenOper(String frozenOper) {
		this.frozenOper = frozenOper;
	}
	public String getIsInitRegister() {
		return isInitRegister;
	}
	public void setIsInitRegister(String isInitRegister) {
		this.isInitRegister = isInitRegister;
	}
	public String getInitRegDate() {
		return initRegDate;
	}
	public void setInitRegDate(String initRegDate) {
		this.initRegDate = initRegDate;
	}
	public String getFitment() {
		return fitment;
	}
	public void setFitment(String fitment) {
		this.fitment = fitment;
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


	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getIsCompany() {
		return isCompany;
	}
	public void setIsCompany(String isCompany) {
		this.isCompany = isCompany;
	}
	public String getDevelopName() {
		return developName;
	}
	public void setDevelopName(String developName) {
		this.developName = developName;
	}
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getOwnersName() {
		return ownersName;
	}
	public void setOwnersName(String ownersName) {
		this.ownersName = ownersName;
	}
	public String getServiceNo() {
		return serviceNo;
	}
	public void setServiceNo(String serviceNo) {
		this.serviceNo = serviceNo;
	}
	public String getOwnersNo() {
		return ownersNo;
	}
	public void setOwnersNo(String ownersNo) {
		this.ownersNo = ownersNo;
	}
	public String getCorpNo() {
		return corpNo;
	}
	public void setCorpNo(String corpNo) {
		this.corpNo = corpNo;
	}
	public BigDecimal getCanSoldNum() {
		return canSoldNum;
	}
	public BigDecimal getCanSoldArea() {
		return canSoldArea;
	}
	public void setCanSoldNum(BigDecimal canSoldNum) {
		this.canSoldNum = canSoldNum;
	}
	public void setCanSoldArea(BigDecimal canSoldArea) {
		this.canSoldArea = canSoldArea;
	}
	public String getContractType() {
		return contractType;
	}
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	public String getPerState() {
		return perState;
	}
	public String getPerStopDate() {
		return perStopDate;
	}
	public void setPerState(String perState) {
		this.perState = perState;
	}
	public void setPerStopDate(String perStopDate) {
		this.perStopDate = perStopDate;
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
	public BigDecimal getStorageNum() {
		return storageNum;
	}
	public void setStorageNum(BigDecimal storageNum) {
		this.storageNum = storageNum;
	}
	public BigDecimal getStorageArea() {
		return storageArea;
	}
	public void setStorageArea(BigDecimal storageArea) {
		this.storageArea = storageArea;
	}

	public BigDecimal getCarportNum() {
		return carportNum;
	}
	public void setCarportNum(BigDecimal carportNum) {
		this.carportNum = carportNum;
	}
	public BigDecimal getCarportArea() {
		return carportArea;
	}
	public void setCarportArea(BigDecimal carportArea) {
		this.carportArea = carportArea;
	}

	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
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
	public String getDevelopId() {
		return developId;
	}
	public void setDevelopId(String developId) {
		this.developId = developId;
	}
	public String getFalseContractArea() {
		return falseContractArea;
	}
	public void setFalseContractArea(String falseContractArea) {
		this.falseContractArea = falseContractArea;
	}

	public BigDecimal getRealInvest() {
		return realInvest;
	}
	public void setRealInvest(BigDecimal realInvest) {
		this.realInvest = realInvest;
	}

	public String getFrameAttrStr() {
		return frameAttrStr;
	}
	public void setFrameAttrStr(String frameAttrStr) {
		this.frameAttrStr = frameAttrStr;
	}
	public BigDecimal getFloorNum() {
		return floorNum;
	}
	public void setFloorNum(BigDecimal floorNum) {
		this.floorNum = floorNum;
	}

	public String getInvestRate() {
		return investRate;
	}

	public void setInvestRate(String investRate) {
		this.investRate = investRate;
	}

	public BigDecimal getConCancelNum() {
		return conCancelNum;
	}

	public void setConCancelNum(BigDecimal conCancelNum) {
		this.conCancelNum = conCancelNum;
	}
	public BigDecimal getTotalArea() {
		return totalArea;
	}
	public void setTotalArea(BigDecimal totalArea) {
		this.totalArea = totalArea;
	}
	public BigDecimal getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(BigDecimal totalNum) {
		this.totalNum = totalNum;
	}
	public BigDecimal getOtherNums() {
		return otherNums;
	}
	public void setOtherNums(BigDecimal otherNums) {
		this.otherNums = otherNums;
	}
	public BigDecimal getOtherAreas() {
		return otherAreas;
	}
	public void setOtherAreas(BigDecimal otherAreas) {
		this.otherAreas = otherAreas;
	}
	
	
}
