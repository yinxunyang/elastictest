package com.bestvike.frame.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by bv_RD on 2018/9/6.
 */

@Entity
@Table(name = "Dic_UserRole")
public class DicUserRole {
    @Id
    @Column(name="sysGuid")
    private String sysGuid;
    @Column(name="userId")
    private String userId;
    @Column(name="sysCode")
    private String sysCode;
    @Column(name="roleCode")
    private String roleCode;
    @Column(name="remark")
    private String remark;

    public String getSysGuid() {
        return sysGuid;
    }

    public void setSysGuid(String sysGuid) {
        this.sysGuid = sysGuid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
