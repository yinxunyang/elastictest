package com.bestvike.rfis.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by bv_RD on 2018/9/15.
 */
@Entity
@Table(name = "Arc_HouseInfo")
public class ArcHouseInfo {
    @Id
    @Column(name="SYSGUID")
    private String sysGuid; // 唯一主键
    @Column(name="CONTRACTNO")
    private String contractNo; // 合同号
    @Column(name="DIVISIONCODE")
    private String divisionCode;
    @Column(name="CORPNO")
    private String corpNo;//企业编号
    @Column(name="PROJECTNO")
    private String projectNo;//项目编号
    @Column(name="BLDNO")
    private String bldNo; // 自然幢编号
    @Column(name="CELLNO")
    private String cellNo; // 单元编号
    @Column(name="FLOORNO")
    private String floorNo; // 楼层编号
    @Column(name="FLOORNAME")
    private String floorName; // 楼层名称
    @Column(name="ROOMNO")
    private String roomNo; // 房间编号
    @Column(name="SHOWNAMETYPE")
    private String showNameType; // 房间显示名称组合方式
    @Column(name="SHOWNAME")
    private String showName; // 显示名称
    @Column(name="ADDRESS")
    private String address; // 房屋地址
    @Column(name="STATE")
    private String state; // 状态
    @Column(name="HOUSEUSE")
    private String houseUse; // 房屋用途
    @Column(name="HOUSETYPE")
    private String houseType; // 房屋类型
    @Column(name="HOUSEPROP")
    private String houseProp; // 房屋性质
    @Column(name="CONSTRUCTAREA")
    private BigDecimal constructArea; // 建筑面积
    @Column(name="OWNAREA")
    private BigDecimal ownArea; // 套内建筑面积
    @Column(name="SHAREAREA")
    private BigDecimal shareArea; // 分摊面积
    @Column(name="BALCONYAREA")
    private BigDecimal balconyArea; // 阳台面积
    @Column(name="OTHERAREA")
    private BigDecimal otherArea; // 其他面积
    @Column(name="HOUSESTRUCTURE")
    private String houseStructure; // 房屋结构
    @Column(name="HOUSEHOLD")
    private String houseHold; // 户型
    @Column(name="BALCONYNUM")
    private BigDecimal balconyNum; // 阳台个数
    @Column(name="ROOMDIRECTION")
    private String roomDirection; // 房屋朝向
    @Column(name="PRESELLPRICE")
    private BigDecimal presellPrice; // 预售价格
    @Column(name="PRESELLSTATE")
    private String presellState; // 预售状态
    @Column(name="ISMORTGAGE")
    private String isMortgage; // 是否抵押
    @Column(name="MORTGAGENO")
    private String mortgageNo; // 抵押编号
    @Column(name="MORTGAGEDATE")
    private String mortgageDate; // 抵押时间
    @Column(name="ISCLOSE")
    private String isClose; // 是否查封
    @Column(name="ISFROZEN")
    private String isFrozen; // 是否冻结
    @Column(name="ISPREREG")
    private String isPreReg; // 是否预告登记
    @Column(name="PREREGDATE")
    private String preRegDate; // 预告登记时间
    @Column(name="ISPROVCARD")
    private String isProvCard; //是否发放他项权证
    @Column(name="PROVCARDDATE")
    private String provCardDate; // 他项权证发放时间
    @Column(name="ISPASS")
    private String isPass; // 是否验收完成
    @Column(name="PASSDATE")
    private String passDate; // 验收日期
    @Column(name="DATAFROM")
    private String dataFrom; // 数据来源
    @Column(name="ORGINNO")
    private String orginNo; // 数据来源编号
    @Column(name="LASTTIME")
    private String lastTime; // 数据最后更新时间
    @Column(name="DATAEXPLAIN")
    private String dataExplain; // 数据说明
    @Column(name="REMARK")
    private String remark; // 备注
    @Column(name="SKIPNUM")
    private BigDecimal skipNum; // 跃层数
    @Column(name="MERGENUM")
    private BigDecimal mergeNum; // 横跨数
    @Column(name="BUYNAMES")
    private String buyNames;//房屋买受人（仅用于合同的查询）
    @Column(name="ISPLEDGE")
    private String isPledge; // 是否在建工程抵押
    @Column(name="ISPLEDGENO")
    private String isPledgeNo; // 在建工程抵押编号
    @Column(name="ISPLEDGEDATE")
    private String isPledgeDate; // 在建工程抵押时间
    @Column(name="BUYCERTNOS")
    private String buyCertNos;//房屋买受人证件号（仅用于合同查询）
    @Column
    private BigDecimal versionNumber;
    @Column
    private Date versionTime;
    @Column
    private String dataCenterId;
    @Column(name="CONTRACTGUID")
    private String contractGuid; // 合同信息表的guid
    @Column(name="PLANDELIVERDATE")
    private String planDeliverDate;//房屋交付日期
    @Column(name="REALPRICE")
    private BigDecimal realPrice; // 房屋签订合同时，实际的单价
    @Column(name="REALOWNAREA")
    private BigDecimal realOwnArea; // 实测套内面积
    @Column(name="REALSHAREAREA")
    private BigDecimal realShareArea; // 实测分摊面积
    @Column(name="REALBALCONYAREA")
    private BigDecimal realBalconyArea; // 实测阳台面积
    @Column(name="REALCONSTRUCTAREA")
    private BigDecimal realConstructArea; // 实测阳台面积
    @Column(name = "ORDERNO" )
    private String orderNo;//查封顺序

    @Column
    private String depState; // 房屋交存状态
    private String cellNoTitle ;
    private String cellNameTitle ;
    private String floorNoTitle ;
    private String floorNameTitle ;
    private String roomNoTitle ;
    private String houseUseTitle ;
    private String housePropTitle ;
    private String addressTitle ;
    private String constructAreaTitle ;
    private String ownAreaTitle ;
    private String shareAreaTitle ;
    private String balaonyAreaTitle ;
    private String mappingUser;
    private String mappingUserId;
    private String mappingUserTitle;
    private String mappingUserIdTitle ;
    private String allFloor;
    private String allFloorTitle ;
    private String bldNoTitle ;
    private String cellName  ;
    // 该房屋所在单元的每层房屋数
    private int unitNum;
    // 该房屋所在单元的楼层数
    private int floorNum;
    private String projectName;
    private String bldName;
    private String title; // 房屋批量维护时，如果不可维护房屋信息提示
    private String saleType; //销售方式
    private BigDecimal price; //房屋售价
    private String isMain; // 是否主房

    private String houseTypeName;
    //private List<AnMetaData> anMetaDataList;
    private int startNum;
    private String elevatorFlag; // 有无电梯0_无，1_有
    private String naturalId; // datacenter中的自然幢表主键（sys_guid）
    //房屋交付日期的年月日
    private String planDeliverYear;
    private String planDeliverMonth;
    private String planDeliverDay;
    //房屋单价
    private BigDecimal unitPrice;
    private String rstctID;

    private String corpName;

    public ArcHouseInfo() {
    }

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

    public String getDivisionCode() {
        return divisionCode;
    }

    public void setDivisionCode(String divisionCode) {
        this.divisionCode = divisionCode;
    }

    public String getCorpNo() {
        return corpNo;
    }

    public void setCorpNo(String corpNo) {
        this.corpNo = corpNo;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public String getBldNo() {
        return bldNo;
    }

    public void setBldNo(String bldNo) {
        this.bldNo = bldNo;
    }

    public String getCellNo() {
        return cellNo;
    }

    public void setCellNo(String cellNo) {
        this.cellNo = cellNo;
    }

    public String getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(String floorNo) {
        this.floorNo = floorNo;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getShowNameType() {
        return showNameType;
    }

    public void setShowNameType(String showNameType) {
        this.showNameType = showNameType;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getHouseUse() {
        return houseUse;
    }

    public void setHouseUse(String houseUse) {
        this.houseUse = houseUse;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getHouseProp() {
        return houseProp;
    }

    public void setHouseProp(String houseProp) {
        this.houseProp = houseProp;
    }

    public BigDecimal getConstructArea() {
        return constructArea;
    }

    public void setConstructArea(BigDecimal constructArea) {
        this.constructArea = constructArea;
    }

    public BigDecimal getOwnArea() {
        return ownArea;
    }

    public void setOwnArea(BigDecimal ownArea) {
        this.ownArea = ownArea;
    }

    public BigDecimal getShareArea() {
        return shareArea;
    }

    public void setShareArea(BigDecimal shareArea) {
        this.shareArea = shareArea;
    }

    public BigDecimal getBalconyArea() {
        return balconyArea;
    }

    public void setBalconyArea(BigDecimal balconyArea) {
        this.balconyArea = balconyArea;
    }

    public BigDecimal getOtherArea() {
        return otherArea;
    }

    public void setOtherArea(BigDecimal otherArea) {
        this.otherArea = otherArea;
    }

    public String getHouseStructure() {
        return houseStructure;
    }

    public void setHouseStructure(String houseStructure) {
        this.houseStructure = houseStructure;
    }

    public String getHouseHold() {
        return houseHold;
    }

    public void setHouseHold(String houseHold) {
        this.houseHold = houseHold;
    }

    public BigDecimal getBalconyNum() {
        return balconyNum;
    }

    public void setBalconyNum(BigDecimal balconyNum) {
        this.balconyNum = balconyNum;
    }

    public String getRoomDirection() {
        return roomDirection;
    }

    public void setRoomDirection(String roomDirection) {
        this.roomDirection = roomDirection;
    }

    public BigDecimal getPresellPrice() {
        return presellPrice;
    }

    public void setPresellPrice(BigDecimal presellPrice) {
        this.presellPrice = presellPrice;
    }

    public String getPresellState() {
        return presellState;
    }

    public void setPresellState(String presellState) {
        this.presellState = presellState;
    }

    public String getIsMortgage() {
        return isMortgage;
    }

    public void setIsMortgage(String isMortgage) {
        this.isMortgage = isMortgage;
    }

    public String getMortgageNo() {
        return mortgageNo;
    }

    public void setMortgageNo(String mortgageNo) {
        this.mortgageNo = mortgageNo;
    }

    public String getMortgageDate() {
        return mortgageDate;
    }

    public void setMortgageDate(String mortgageDate) {
        this.mortgageDate = mortgageDate;
    }

    public String getIsClose() {
        return isClose;
    }

    public void setIsClose(String isClose) {
        this.isClose = isClose;
    }

    public String getIsFrozen() {
        return isFrozen;
    }

    public void setIsFrozen(String isFrozen) {
        this.isFrozen = isFrozen;
    }

    public String getIsPreReg() {
        return isPreReg;
    }

    public void setIsPreReg(String isPreReg) {
        this.isPreReg = isPreReg;
    }

    public String getPreRegDate() {
        return preRegDate;
    }

    public void setPreRegDate(String preRegDate) {
        this.preRegDate = preRegDate;
    }

    public String getIsProvCard() {
        return isProvCard;
    }

    public void setIsProvCard(String isProvCard) {
        this.isProvCard = isProvCard;
    }

    public String getProvCardDate() {
        return provCardDate;
    }

    public void setProvCardDate(String provCardDate) {
        this.provCardDate = provCardDate;
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

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public String getDataExplain() {
        return dataExplain;
    }

    public void setDataExplain(String dataExplain) {
        this.dataExplain = dataExplain;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getSkipNum() {
        return skipNum;
    }

    public void setSkipNum(BigDecimal skipNum) {
        this.skipNum = skipNum;
    }

    public BigDecimal getMergeNum() {
        return mergeNum;
    }

    public void setMergeNum(BigDecimal mergeNum) {
        this.mergeNum = mergeNum;
    }

    public String getBuyNames() {
        return buyNames;
    }

    public void setBuyNames(String buyNames) {
        this.buyNames = buyNames;
    }

    public String getIsPledge() {
        return isPledge;
    }

    public void setIsPledge(String isPledge) {
        this.isPledge = isPledge;
    }

    public String getIsPledgeNo() {
        return isPledgeNo;
    }

    public void setIsPledgeNo(String isPledgeNo) {
        this.isPledgeNo = isPledgeNo;
    }

    public String getIsPledgeDate() {
        return isPledgeDate;
    }

    public void setIsPledgeDate(String isPledgeDate) {
        this.isPledgeDate = isPledgeDate;
    }

    public String getBuyCertNos() {
        return buyCertNos;
    }

    public void setBuyCertNos(String buyCertNos) {
        this.buyCertNos = buyCertNos;
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

    public String getPlanDeliverDate() {
        return planDeliverDate;
    }

    public void setPlanDeliverDate(String planDeliverDate) {
        this.planDeliverDate = planDeliverDate;
    }

    public BigDecimal getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(BigDecimal realPrice) {
        this.realPrice = realPrice;
    }

    public BigDecimal getRealOwnArea() {
        return realOwnArea;
    }

    public void setRealOwnArea(BigDecimal realOwnArea) {
        this.realOwnArea = realOwnArea;
    }

    public BigDecimal getRealShareArea() {
        return realShareArea;
    }

    public void setRealShareArea(BigDecimal realShareArea) {
        this.realShareArea = realShareArea;
    }

    public BigDecimal getRealBalconyArea() {
        return realBalconyArea;
    }

    public void setRealBalconyArea(BigDecimal realBalconyArea) {
        this.realBalconyArea = realBalconyArea;
    }

    public BigDecimal getRealConstructArea() {
        return realConstructArea;
    }

    public void setRealConstructArea(BigDecimal realConstructArea) {
        this.realConstructArea = realConstructArea;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getDepState() {
        return depState;
    }

    public void setDepState(String depState) {
        this.depState = depState;
    }

    public String getCellNoTitle() {
        return cellNoTitle;
    }

    public void setCellNoTitle(String cellNoTitle) {
        this.cellNoTitle = cellNoTitle;
    }

    public String getCellNameTitle() {
        return cellNameTitle;
    }

    public void setCellNameTitle(String cellNameTitle) {
        this.cellNameTitle = cellNameTitle;
    }

    public String getFloorNoTitle() {
        return floorNoTitle;
    }

    public void setFloorNoTitle(String floorNoTitle) {
        this.floorNoTitle = floorNoTitle;
    }

    public String getFloorNameTitle() {
        return floorNameTitle;
    }

    public void setFloorNameTitle(String floorNameTitle) {
        this.floorNameTitle = floorNameTitle;
    }

    public String getRoomNoTitle() {
        return roomNoTitle;
    }

    public void setRoomNoTitle(String roomNoTitle) {
        this.roomNoTitle = roomNoTitle;
    }

    public String getHouseUseTitle() {
        return houseUseTitle;
    }

    public void setHouseUseTitle(String houseUseTitle) {
        this.houseUseTitle = houseUseTitle;
    }

    public String getHousePropTitle() {
        return housePropTitle;
    }

    public void setHousePropTitle(String housePropTitle) {
        this.housePropTitle = housePropTitle;
    }

    public String getAddressTitle() {
        return addressTitle;
    }

    public void setAddressTitle(String addressTitle) {
        this.addressTitle = addressTitle;
    }

    public String getConstructAreaTitle() {
        return constructAreaTitle;
    }

    public void setConstructAreaTitle(String constructAreaTitle) {
        this.constructAreaTitle = constructAreaTitle;
    }

    public String getOwnAreaTitle() {
        return ownAreaTitle;
    }

    public void setOwnAreaTitle(String ownAreaTitle) {
        this.ownAreaTitle = ownAreaTitle;
    }

    public String getShareAreaTitle() {
        return shareAreaTitle;
    }

    public void setShareAreaTitle(String shareAreaTitle) {
        this.shareAreaTitle = shareAreaTitle;
    }

    public String getBalaonyAreaTitle() {
        return balaonyAreaTitle;
    }

    public void setBalaonyAreaTitle(String balaonyAreaTitle) {
        this.balaonyAreaTitle = balaonyAreaTitle;
    }

    public String getMappingUser() {
        return mappingUser;
    }

    public void setMappingUser(String mappingUser) {
        this.mappingUser = mappingUser;
    }

    public String getMappingUserId() {
        return mappingUserId;
    }

    public void setMappingUserId(String mappingUserId) {
        this.mappingUserId = mappingUserId;
    }

    public String getMappingUserTitle() {
        return mappingUserTitle;
    }

    public void setMappingUserTitle(String mappingUserTitle) {
        this.mappingUserTitle = mappingUserTitle;
    }

    public String getMappingUserIdTitle() {
        return mappingUserIdTitle;
    }

    public void setMappingUserIdTitle(String mappingUserIdTitle) {
        this.mappingUserIdTitle = mappingUserIdTitle;
    }

    public String getAllFloor() {
        return allFloor;
    }

    public void setAllFloor(String allFloor) {
        this.allFloor = allFloor;
    }

    public String getAllFloorTitle() {
        return allFloorTitle;
    }

    public void setAllFloorTitle(String allFloorTitle) {
        this.allFloorTitle = allFloorTitle;
    }

    public String getBldNoTitle() {
        return bldNoTitle;
    }

    public void setBldNoTitle(String bldNoTitle) {
        this.bldNoTitle = bldNoTitle;
    }

    public String getCellName() {
        return cellName;
    }

    public void setCellName(String cellName) {
        this.cellName = cellName;
    }

    public int getUnitNum() {
        return unitNum;
    }

    public void setUnitNum(int unitNum) {
        this.unitNum = unitNum;
    }

    public int getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(int floorNum) {
        this.floorNum = floorNum;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBldName() {
        return bldName;
    }

    public void setBldName(String bldName) {
        this.bldName = bldName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSaleType() {
        return saleType;
    }

    public void setSaleType(String saleType) {
        this.saleType = saleType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getIsMain() {
        return isMain;
    }

    public void setIsMain(String isMain) {
        this.isMain = isMain;
    }

    public String getHouseTypeName() {
        return houseTypeName;
    }

    public void setHouseTypeName(String houseTypeName) {
        this.houseTypeName = houseTypeName;
    }

  /*  public List<AnMetaData> getAnMetaDataList() {
        return anMetaDataList;
    }

    public void setAnMetaDataList(List<AnMetaData> anMetaDataList) {
        this.anMetaDataList = anMetaDataList;
    }*/

    public int getStartNum() {
        return startNum;
    }

    public void setStartNum(int startNum) {
        this.startNum = startNum;
    }

    public String getElevatorFlag() {
        return elevatorFlag;
    }

    public void setElevatorFlag(String elevatorFlag) {
        this.elevatorFlag = elevatorFlag;
    }

    public String getNaturalId() {
        return naturalId;
    }

    public void setNaturalId(String naturalId) {
        this.naturalId = naturalId;
    }

    public String getPlanDeliverYear() {
        return planDeliverYear;
    }

    public void setPlanDeliverYear(String planDeliverYear) {
        this.planDeliverYear = planDeliverYear;
    }

    public String getPlanDeliverMonth() {
        return planDeliverMonth;
    }

    public void setPlanDeliverMonth(String planDeliverMonth) {
        this.planDeliverMonth = planDeliverMonth;
    }

    public String getPlanDeliverDay() {
        return planDeliverDay;
    }

    public void setPlanDeliverDay(String planDeliverDay) {
        this.planDeliverDay = planDeliverDay;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getRstctID() {
        return rstctID;
    }

    public void setRstctID(String rstctID) {
        this.rstctID = rstctID;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }
}
