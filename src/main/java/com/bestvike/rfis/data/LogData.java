package com.bestvike.rfis.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LOGDATA")
public class LogData {
    @Id
    @Column(name = "logId")
    private String logId;
    @Column(name="BEGINTIME")
    private String beginTime;
    @Column(name="ENDTIME")
    private String endTime;
    @Column(name="NUMBERS")
    private Integer numbers;
    @Column(name="SCOPEBEGINTIME")
    private String scopeBeginTime;
    @Column(name="SCOPEENDTIME")
    private String scopeEndTime;

    public LogData() {
    }

    public LogData(String logId, String beginTime, String endTime, Integer numbers, String scopeBeginTime, String scopeEndTime) {
        this.logId = logId;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.numbers = numbers;
        this.scopeBeginTime = scopeBeginTime;
        this.scopeEndTime = scopeEndTime;
    }

    public String getScopeBeginTime() {
        return scopeBeginTime;
    }

    public void setScopeBeginTime(String scopeBeginTime) {
        this.scopeBeginTime = scopeBeginTime;
    }

    public String getScopeEndTime() {
        return scopeEndTime;
    }

    public void setScopeEndTime(String scopeEndTime) {
        this.scopeEndTime = scopeEndTime;
    }

    public String getBeginTime() {

        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getNumbers() {
        return numbers;
    }

    public void setNumbers(Integer numbers) {
        this.numbers = numbers;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }
}
