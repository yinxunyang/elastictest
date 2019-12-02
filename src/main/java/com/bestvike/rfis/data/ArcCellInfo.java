package com.bestvike.rfis.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 
 * @ClassName: ArcCellInfo
 * @Description: TODO
 * @author songhao
 * @date 2016-9-29 上午11:06:14
 * @version V1.0
 */
@Entity
@Table(name="Arc_CellInfo")
public class ArcCellInfo implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column
	private String sysGuid;
	@Column
	private String bldNo; // 自然幢编号
	@Column
	private String houseType; // 房屋类型
	@Column
	private String cellNo; // 单元编号
	@Column
	private String cellName; // 单元名称
	@Column
	private String elevatorFlag; // 有无电梯
	@Column
	private int floorNum; // 楼层数
	@Column
	private int initFloorNo; // 初始楼层
	@Column
	private int endFloorNo; // 终止楼层
	@Column
	private int unitNum; // 每层户数
	@Column
	private String recordDate; // 录入日期
	@Column
	private String recordTime; // 录入时间
	@Column
	private String recordUser; // 录入人
	@Column
	private String dataFrom; // 数据来源
	@Column
	private String orginNo; // 数据来源编号
	@Column
	private String dataExplain; // 数据来源说明
	@Column
	private String lastTime; // 上次操作日期
	@Column
	private String remark; // 备注
	
	private int floorHouseNum;
	private int roomNum;
	private String title;
	private int startNum;
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
	public String getHouseType() {
		return houseType;
	}
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	public String getCellNo() {
		return cellNo;
	}
	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}
	public String getCellName() {
		return cellName;
	}
	public void setCellName(String cellName) {
		this.cellName = cellName;
	}
	public String getElevatorFlag() {
		return elevatorFlag;
	}
	public void setElevatorFlag(String elevatorFlag) {
		this.elevatorFlag = elevatorFlag;
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
	public int getFloorNum() {
		return floorNum;
	}
	public void setFloorNum(int floorNum) {
		this.floorNum = floorNum;
	}
	public int getInitFloorNo() {
		return initFloorNo;
	}
	public void setInitFloorNo(int initFloorNo) {
		this.initFloorNo = initFloorNo;
	}
	public int getEndFloorNo() {
		return endFloorNo;
	}
	public void setEndFloorNo(int endFloorNo) {
		this.endFloorNo = endFloorNo;
	}
	public int getUnitNum() {
		return unitNum;
	}
	public void setUnitNum(int unitNum) {
		this.unitNum = unitNum;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getFloorHouseNum() {
		return floorHouseNum;
	}
	public void setFloorHouseNum(int floorHouseNum) {
		this.floorHouseNum = floorHouseNum;
	}
	public int getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getStartNum() {
		return startNum;
	}
	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}
}
