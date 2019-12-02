package com.bestvike.portal.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "sys_task")
public class SysTask implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String taskType;
    private Date taskTimestamp;
    private Date manageTime;
    private String manageUser;
    private String remark;

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public Date getTaskTimestamp() {
        return taskTimestamp;
    }

    public void setTaskTimestamp(Date taskTimestamp) {
        this.taskTimestamp = taskTimestamp;
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
}