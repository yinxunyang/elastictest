package com.bestvike.portal.data;

import com.bestvike.commons.util.DateUtil;
import com.bestvike.datacenter.support.Datacenter;
import com.bestvike.datacenter.util.DatacenterUtil;
import com.bestvike.portal.util.GCC;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "sys_dept")
public class SysDept implements Datacenter, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String deptId;
    private String deptName;
    private Integer deptLevel;
    private String parentDept;
    private String deptAddress;
    private String contactName;
    private String contactPhone;
    private Integer showOrder;
    private String dataRange;
    private String deptState;
    private String areaCode;
    private Date manageTime;
    private String manageUser;
    @Column(updatable = false)
    private String datacenterId;
    @Column(updatable = false)
    private Integer versionNumber;
    @Column(updatable = false)
    private Date versionTime;
    private String remark;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getDeptLevel() {
        return deptLevel;
    }

    public void setDeptLevel(Integer deptLevel) {
        this.deptLevel = deptLevel;
    }

    public String getParentDept() {
        return parentDept;
    }

    public void setParentDept(String parentDept) {
        this.parentDept = parentDept;
    }

    public String getDeptAddress() {
        return deptAddress;
    }

    public void setDeptAddress(String deptAddress) {
        this.deptAddress = deptAddress;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Integer getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Integer showOrder) {
        this.showOrder = showOrder;
    }

    public String getDataRange() {
        return dataRange;
    }

    public void setDataRange(String dataRange) {
        this.dataRange = dataRange;
    }

    public String getDeptState() {
        return deptState;
    }

    public void setDeptState(String deptState) {
        this.deptState = deptState;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public Date getManageTime() {
        return manageTime;
    }

    public void setManageTime(Date manageTime) {
        this.manageTime = manageTime;
    }

    public String getManageUser() {
        return manageUser;
    }

    public void setManageUser(String manageUser) {
        this.manageUser = manageUser;
    }

    public String getDatacenterId() {
        return datacenterId;
    }

    public void setDatacenterId(String datacenterId) {
        this.datacenterId = datacenterId;
    }

    public Integer getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(Integer versionNumber) {
        this.versionNumber = versionNumber;
    }

    public Date getVersionTime() {
        return versionTime;
    }

    public void setVersionTime(Date versionTime) {
        this.versionTime = versionTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public Map<String, Object> toDatacenter(String operateType, String appCode, String resultRoute) {
        Map<String, Object> messageMap = new HashMap<>();
        messageMap.put("appCode", appCode);
        messageMap.put("dataType", "corpInfo");
        messageMap.put("dataVersion", "corpVersion");
        messageMap.put("resultType", "sysDept");
        messageMap.put("operate", operateType);
        if (!StringUtils.isEmpty(resultRoute)) {
            messageMap.put("resultRoute", resultRoute);
        }

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("corpId", this.getDatacenterId());
        dataMap.put("corpName", this.getDeptName());
        dataMap.put("corpAddress", this.getDeptAddress());
        String corpState = "normal";
        String deptState = this.getDeptState();
        if (!StringUtils.isEmpty(deptState) && !deptState.equals("0000")) {
            corpState = "delete";
        }
        dataMap.put("corpState", corpState);
        dataMap.put("operateType", operateType);
        dataMap.put("versionNumber", DatacenterUtil.initVersionNumber(this.getVersionNumber(), true));
        dataMap.put("appCode", appCode);
        dataMap.put("manageTime", DateUtil.getDateDate());
        dataMap.put("manageUser", this.getManageUser());

        messageMap.put("data", dataMap);

        return messageMap;
    }

    @Override
    public Map<String, Object> fromDatacenter(Map<String, Object> dataMap) {

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("dataType", "sysDept");

        Map<String, Object> dataColumnMap = new HashMap<>();

        dataColumnMap.put("deptId", this.getDeptId());
        dataColumnMap.put("deptName", dataMap.get("corpName"));
        dataColumnMap.put("deptLevel", 2);
        dataColumnMap.put("parentDept", dataMap.get("corpType"));
        dataColumnMap.put("deptAddress", dataMap.get("contactAddress"));
        dataColumnMap.put("showOrder", this.getDeptId());
        dataColumnMap.put("dataRange", "");
        String deptState = GCC.DEPT_DEPTSTATE_NORMAL;
        String corpState = (String) dataMap.get("state");
        if (!StringUtils.isEmpty(corpState) && !corpState.equals(GCC.DATACENTER_DATASTATE_NORMAL)) {
            deptState = GCC.DEPT_DEPTSTATE_DELETE;
        }
        dataColumnMap.put("deptState", deptState);
        dataColumnMap.put("areaCode", dataMap.get("cityCode"));
        Date versionTime = DatacenterUtil.initVersionTime(dataMap.get("manageTime"));
        dataColumnMap.put("manageTime", versionTime);
        dataColumnMap.put("manageUser", dataMap.get("manageUser"));
        dataColumnMap.put("datacenterId", dataMap.get("sysGuid"));
        dataColumnMap.put("versionNumber", DatacenterUtil.initVersionNumber(dataMap.get("versionNumber"), false));
        dataColumnMap.put("versionTime", versionTime);

        resultMap.put("data", dataColumnMap);

        Map<String, Object> versionMap = new HashMap<>();
        versionMap.put("tableName", "sys_dept");
        versionMap.put("datacenterId", dataMap.get("sysGuid"));
        versionMap.put("versionNumber", dataColumnMap.get("versionNumber"));
        versionMap.put("versionTime", versionTime);
        versionMap.put("datacenterIdName", "datacenter_id");
        versionMap.put("versionNumberName", "version_number");

        resultMap.put("versionMap", versionMap);

        return resultMap;
    }

	public SysDept init(String deptId) {
		this.deptId = deptId;
		return this;
	}

	/**
	 * @Title：fromBankDatacenter
	 * @Description：转换业务系统上传的银行数据
	 * @Author：ybb
	 * @Date：2018/3/19 11:59
	 * @param：
	 * @return
	 */
    public Map<String, Object> fromBankDatacenter(Map<String, Object> dataMap) {

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("dataType", "sysDept");

        Map<String, Object> dataColumnMap = new HashMap<>();

        dataColumnMap.put("deptId", dataMap.get("bankCode"));
        dataColumnMap.put("deptName", dataMap.get("bankName"));
        dataColumnMap.put("deptLevel", dataMap.get("bankLevel"));
        dataColumnMap.put("parentDept", dataMap.get("parentBankNo"));
        dataColumnMap.put("dataRange", "");
        String deptState = GCC.DEPT_DEPTSTATE_NORMAL;
        String corpState = (String) dataMap.get("state");
        if (!StringUtils.isEmpty(corpState) && !corpState.equals(GCC.DATACENTER_DATASTATE_NORMAL)) {
            deptState = GCC.DEPT_DEPTSTATE_DELETE;
        }
        dataColumnMap.put("deptState", deptState);
        Date versionTime = DatacenterUtil.initVersionTime(dataMap.get("manageTime"));
        dataColumnMap.put("manageTime", versionTime);
        dataColumnMap.put("manageUser", dataMap.get("manageUser"));
        dataColumnMap.put("datacenterId", dataMap.get("bankId"));
        dataColumnMap.put("versionNumber", DatacenterUtil.initVersionNumber(dataMap.get("versionNumber"), false));
        dataColumnMap.put("versionTime", versionTime);

        resultMap.put("data", dataColumnMap);

        Map<String, Object> versionMap = new HashMap<>();
        versionMap.put("tableName", "sys_dept");
        versionMap.put("datacenterId", dataMap.get("bankId"));
        versionMap.put("versionNumber", dataColumnMap.get("versionNumber"));
        versionMap.put("versionTime", versionTime);
        versionMap.put("datacenterIdName", "datacenter_id");
        versionMap.put("versionNumberName", "version_number");

        resultMap.put("versionMap", versionMap);

        return resultMap;
    }
}