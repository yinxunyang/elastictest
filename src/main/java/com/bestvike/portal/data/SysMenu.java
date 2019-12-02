package com.bestvike.portal.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sys_menu")
public class SysMenu implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String menuId;
    private String menuName;
    private String menuDescribe;
    private String menuAddress;
    private String menuIcon;
    private Integer menuLevel;
    private String parentMenu;
    private Integer showOrder;
    private String menuState;
    private String areaCode;
    private String deptId;
    private String appCode;
    private Date manageTime;
    private String manageUser;
    private String remark;

    // menu-菜单 button-按钮
    @Transient
    private String menuType;
    @Transient
    private String userOperates;
    @Transient
    private List<SysMenuOperate> menuOperates;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuDescribe() {
        return menuDescribe;
    }

    public void setMenuDescribe(String menuDescribe) {
        this.menuDescribe = menuDescribe;
    }

    public String getMenuAddress() {
        return menuAddress;
    }

    public void setMenuAddress(String menuAddress) {
        this.menuAddress = menuAddress;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public Integer getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }

    public String getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(String parentMenu) {
        this.parentMenu = parentMenu;
    }

    public Integer getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Integer showOrder) {
        this.showOrder = showOrder;
    }

    public String getMenuState() {
        return menuState;
    }

    public void setMenuState(String menuState) {
        this.menuState = menuState;
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

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getUserOperates() {
        return userOperates;
    }

    public void setUserOperates(String userOperates) {
        this.userOperates = userOperates;
    }

    public List<SysMenuOperate> getMenuOperates() {
        return menuOperates;
    }

    public void setMenuOperates(List<SysMenuOperate> menuOperates) {
        this.menuOperates = menuOperates;
    }
}