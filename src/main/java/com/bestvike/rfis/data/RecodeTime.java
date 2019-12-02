package com.bestvike.rfis.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "RecodeTime")
public class RecodeTime {
    @Column(name = "XH")
    private Integer xh;
    @Column(name = "ENDEXECUTETIME")
    private String endExecuteTime;
    @Column(name = "BEFORETIME")
    private Integer beforeTime;  //分钟

    public RecodeTime() {
    }

    public Integer getXh() {
        return xh;
    }

    public void setXh(Integer xh) {
        this.xh = xh;
    }

    public String getEndExecuteTime() {
        return endExecuteTime;
    }

    public void setEndExecuteTime(String endExecuteTime) {
        this.endExecuteTime = endExecuteTime;
    }

    public Integer getBeforeTime() {
        return beforeTime;
    }

    public void setBeforeTime(Integer beforeTime) {
        this.beforeTime = beforeTime;
    }

    public RecodeTime(Integer xh, String endExecuteTime, Integer beforeTime) {

        this.xh = xh;
        this.endExecuteTime = endExecuteTime;
        this.beforeTime = beforeTime;
    }
}
