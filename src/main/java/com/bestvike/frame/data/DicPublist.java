package com.bestvike.frame.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by bv_RD on 2018/9/21.
 */
@Entity
@Table(name = "Dic_Publist")
public class DicPublist {
    @Id
    @Column(name="sysGuid")
    private String sysGuid;
    @Column(name="dicName")
    private String dicName;
    @Column(name="itemName")
    private String itemName;
    @Column(name="value")
    private String value;
    @Column(name="describe")
    private String describe;
    @Column(name="disOrder")
    private BigDecimal disOrder;
    @Column(name="remark")
    private String remark;

    public String getSysGuid() {
        return sysGuid;
    }

    public void setSysGuid(String sysGuid) {
        this.sysGuid = sysGuid;
    }

    public String getDicName() {
        return dicName;
    }

    public void setDicName(String dicName) {
        this.dicName = dicName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public BigDecimal getDisOrder() {
        return disOrder;
    }

    public void setDisOrder(BigDecimal disOrder) {
        this.disOrder = disOrder;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
