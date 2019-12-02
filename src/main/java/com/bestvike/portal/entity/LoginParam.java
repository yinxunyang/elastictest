package com.bestvike.portal.entity;

import java.io.Serializable;

public class LoginParam implements Serializable {
    private static final long serialVersionUID = 1L;

    private String appCode;
    private String simulate;

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getSimulate() {
        return simulate;
    }

    public void setSimulate(String simulate) {
        this.simulate = simulate;
    }
}