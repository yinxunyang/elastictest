package com.bestvike.frame.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by bv_RD on 2018/9/6.
 */

@Entity
@Table(name = "Dic_UserInfo")
public class DicUserInfo {
    @Id
    @Column(name="userId")
    private String userId;
    @Column(name="cityCode")
    private String cityCode;
    @Column(name="divisionCode")
    private String divisionCode;
    @Column(name="corpType")
    private String corpType;
    @Column(name="branchNo")
    private String branchNo;
    @Column(name="aliasName")
    private String aliasName;
    @Column(name="userName")
    private String userName;
    @Column(name="userPass")
    private String userPass;
    @Column(name="corpNo")
    private String corpNo;
    @Column(name="state")
    private String state;
    @Column(name="passFlag")
    private String passFlag;
    @Column(name="checkKey")
    private String checkKey;
    @Column(name="isShow")
    private String isShow;
    @Column(name="isLogin")
    private String isLogin;
    @Column(name="lastSysCode")
    private String lastSysCode;
    @Column(name="lastRoleCode")
    private String lastRoleCode;
    @Column(name="lastPassDate")
    private String lastPassDate;
    @Column(name="adminUser")
    private String adminUser;
    @Column(name="adminDate")
    private String adminDate;
    @Column(name="adminTime")
    private String adminTime;
    @Column(name="quanpin")
    private String quanpin;
    @Column(name="jianpin")
    private String jianpin;
    @Column(name="remark")
    private String remark;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getCorpType() {
        return corpType;
    }

    public void setCorpType(String corpType) {
        this.corpType = corpType;
    }

    public String getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(String branchNo) {
        this.branchNo = branchNo;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getCorpNo() {
        return corpNo;
    }

    public void setCorpNo(String corpNo) {
        this.corpNo = corpNo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPassFlag() {
        return passFlag;
    }

    public void setPassFlag(String passFlag) {
        this.passFlag = passFlag;
    }

    public String getCheckKey() {
        return checkKey;
    }

    public void setCheckKey(String checkKey) {
        this.checkKey = checkKey;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    public String getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(String isLogin) {
        this.isLogin = isLogin;
    }

    public String getLastSysCode() {
        return lastSysCode;
    }

    public void setLastSysCode(String lastSysCode) {
        this.lastSysCode = lastSysCode;
    }

    public String getLastRoleCode() {
        return lastRoleCode;
    }

    public void setLastRoleCode(String lastRoleCode) {
        this.lastRoleCode = lastRoleCode;
    }

    public String getLastPassDate() {
        return lastPassDate;
    }

    public void setLastPassDate(String lastPassDate) {
        this.lastPassDate = lastPassDate;
    }

    public String getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(String adminUser) {
        this.adminUser = adminUser;
    }

    public String getAdminDate() {
        return adminDate;
    }

    public void setAdminDate(String adminDate) {
        this.adminDate = adminDate;
    }

    public String getAdminTime() {
        return adminTime;
    }

    public void setAdminTime(String adminTime) {
        this.adminTime = adminTime;
    }

    public String getQuanpin() {
        return quanpin;
    }

    public void setQuanpin(String quanpin) {
        this.quanpin = quanpin;
    }

    public String getJianpin() {
        return jianpin;
    }

    public void setJianpin(String jianpin) {
        this.jianpin = jianpin;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
