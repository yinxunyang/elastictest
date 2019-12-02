package com.bestvike.portal.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "demo_range")
public class DemoRange implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String demoId;
    private String demoName;
    private String areaCode;
    private String deptId;
    private String demoState;
    private BigDecimal demoAmount;
    private Integer demoAge;
    private String remark;

    public String getDemoId() {
        return demoId;
    }

    public void setDemoId(String demoId) {
        this.demoId = demoId;
    }

    public String getDemoName() {
        return demoName;
    }

    public void setDemoName(String demoName) {
        this.demoName = demoName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDemoState() {
        return demoState;
    }

    public void setDemoState(String demoState) {
        this.demoState = demoState;
    }

    public BigDecimal getDemoAmount() {
        return demoAmount;
    }

    public void setDemoAmount(BigDecimal demoAmount) {
        this.demoAmount = demoAmount;
    }

    public Integer getDemoAge() {
        return demoAge;
    }

    public void setDemoAge(Integer demoAge) {
        this.demoAge = demoAge;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}