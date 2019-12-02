package com.bestvike.portal.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "TRADE_RECORD")
public class TradeRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="XH")
    private Integer XH;  //序号1
    @Column(name="YWBH")
    private String YWBH;  //业务编码2
    @Column(name="LLYWLB")
    private String LLYWLB;  //理论业务类别3
    @Column(name="LLYWLBBM")
    private String LLYWLBBM;  //理论业务类别编码4
    @Column(name="SJYWLB")
    private String SJYWLB;  //实际业务类别5
    @Column(name="YYWBH")
    private String YYWBH;  //上件业务编号6
    @Column(name="JYZT")
    private String JYZT;  //交易状态7
    @Column(name="JYYWZMZSMC")
    private String JYYWZMZSMC;  //交易业务证明证书名称8
    @Column(name="JYYWZMZSMCBM")
    private String JYYWZMZSMCBM;  //交易业务证明证书名称编码9
    @Column(name="JYYWZMZSH")
    private String JYYWZMZSH;  //交易业务证明证书号10
    @Column(name="DJYWZMZSMC")
    private String DJYWZMZSMC;  //登记业务证明证书名称11
    @Column(name="DJYWZMZSMCBM")
    private String DJYWZMZSMCBM;  //登记业务证明证书名称编码12
    @Column(name="DJYWZMZSH")
    private String DJYWZMZSH;  //登记业务证明证书号13
    @Column(name="JYZLB")
    private String JYZLB;  //交易者类别14
    @Column(name="JYZLBBM")
    private String JYZLBBM;  //交易者类别编码15
    @Column(name="JYZQC")
    private String JYZQC;  //交易者全称16
    @Column(name="JYZZJMC")
    private String JYZZJMC;  //交易者证件名称17
    @Column(name="JYZZJMCBM ")
    private String JYZZJMCBM;  //交易者证件名称编码18
    @Column(name="JYZZJHM")
    private String JYZZJHM;  //交易者证件号码19
    @Column(name="JYZXZ")
    private String JYZXZ;  //交易者性质20
    @Column(name="JYZXZBM")
    private String JYZXZBM;  //交易者性质编码21
    @Column(name="JYZHJ")
    private String JYZHJ;  //交易者户籍22
    @Column(name="JYZHJXZQH")
    private String JYZHJXZQH;  //交易者户籍行政区划23
    @Column(name="BDCDYH")
    private String BDCDYH;  //不动产单元24
    @Column(name="FWBM")
    private String FWBM;  //房屋编码25
    @Column(name="FWZT")
    private String FWZT;  //房屋状态26
    @Column(name="XZQHDM")
    private String XZQHDM;  //行政区划代码27
    @Column(name="QX")
    private String QX;  //区县28
    @Column(name="XZJDB")
    private String XZJDB;  //乡镇/街道办29
    @Column(name="JJXZQH")
    private String JJXZQH;  //交集行政区划30
    @Column(name="JJXZQHDM")
    private String JJXZQHDM;  //交集行政区划代码31
    @Column(name="LJX")
    private String LJX;  //路街巷32
    @Column(name="XQ")
    private String XQ;  //小区33
    @Column(name="LH")
    private String LH;  //楼号34
    @Column(name="SZQSC")
    private Integer SZQSC;  //所在起始层35
    @Column(name="SZZZC")
    private Integer SZZZC;  //所在终止层36
    @Column(name="MYC")
    private String MYC;  //名义层37
    @Column(name="DY")
    private String DY;  //层房序号38
    @Column(name="FH")
    private String FH;  //房号39
    @Column(name="FWZL")
    private String FWZL;  //房屋坐落40
    @Column(name="HXJS")
    private String HXJS;  //户型居室41
    @Column(name="HXJSBM")
    private String HXJSBM;  //户型居室编码42
    @Column(name="HXJG")
    private String HXJG;  //户型结构43
    @Column(name="HXJGBM")
    private String HXJGBM;  //户型结构编码44
    @Column(name="FWCX")
    private String FWCX;  //房屋朝向45
    @Column(name="FWCXBM")
    private String FWCXBM;  //房屋朝向编码46
    @Column(name="JZMJ")
    private BigDecimal JZMJ;  //建筑面积47
    @Column(name="TNJZMJ")
    private BigDecimal TNJZMJ;  //套内建筑面积48
    @Column(name="GTJZMJ")
    private BigDecimal GTJZMJ;  //公摊建筑面积49
    @Column(name="GHYT")
    private String GHYT;  //规划用途50
    @Column(name="JZJG")
    private String JZJG;  //建筑结构51
    @Column(name="JZJGBM")
    private String JZJGBM;  //建筑结构编码52
    @Column(name="FWYT")
    private String FWYT;  //房屋用途53
    @Column(name="FWYTBM")
    private String FWYTBM;  //房屋用途编码54
    @Column(name="FWXZ")
    private String FWXZ;  //房屋性质55
    @Column(name="FWXZBM")
    private String FWXZBM;  //房屋性质编码56
    @Column(name="FWLX")
    private String FWLX;  //房屋类型57
    @Column(name="FWLXBM")
    private String FWLXBM;  //房屋类型编码58
    @Column(name="GYFS")
    private String GYFS;  //共有方式59
    @Column(name="GYFSBM")
    private String GYFSBM;  //共有方式编码60
    @Column(name="SZFE")
    private String SZFE;  //所占份额61
    @Column(name="CJJE")
    private BigDecimal CJJE;  //成交金额62
    @Column(name="FKLX")
    private String FKLX;  //付款类型63
    @Column(name="FKLXBM")
    private String FKLXBM;  //付款类型编码64
    @Column(name="DKFS")
    private String DKFS;  //贷款方式65
    @Column(name="DKFSBM")
    private String DKFSBM;  //贷款方式编码66
    @Column(name="HTSXRQ")
    private String HTSXRQ;  //合同生效日期67
    @Column(name="YWBJSJ")
    private String YWBJSJ;  //业务办结时间68
    @Column(name="YWBJRSFZH")
    private String YWBJRSFZH;  //业务办结人身份证号69
    @Column(name="YWBLSZXZQHDM")
    private String YWBLSZXZQHDM;  //业务办理所在行政区划代码70

    public TradeRecord() {
    }
    @JsonProperty("XH")
    public Integer getXH() {
        return XH;
    }

    public void setXH(Integer XH) {
        this.XH = XH;
    }
    @JsonProperty("YWBH")
    public String getYWBH() {
        return YWBH;
    }

    public void setYWBH(String YWBH) {
        this.YWBH = YWBH;
    }
    @JsonProperty("LLYWLB")
    public String getLLYWLB() {
        return LLYWLB;
    }

    public void setLLYWLB(String LLYWLB) {
        this.LLYWLB = LLYWLB;
    }
    @JsonProperty("LLYWLBBM")
    public String getLLYWLBBM() {
        return LLYWLBBM;
    }

    public void setLLYWLBBM(String LLYWLBBM) {
        this.LLYWLBBM = LLYWLBBM;
    }
    @JsonProperty("SJYWLB")
    public String getSJYWLB() {
        return SJYWLB;
    }

    public void setSJYWLB(String SJYWLB) {
        this.SJYWLB = SJYWLB;
    }
    @JsonProperty("YYWBH")
    public String getYYWBH() {
        return YYWBH;
    }

    public void setYYWBH(String YYWBH) {
        this.YYWBH = YYWBH;
    }
    @JsonProperty("JYZT")
    public String getJYZT() {
        return JYZT;
    }

    public void setJYZT(String JYZT) {
        this.JYZT = JYZT;
    }
    @JsonProperty("JYYWZMZSMC")
    public String getJYYWZMZSMC() {
        return JYYWZMZSMC;
    }

    public void setJYYWZMZSMC(String JYYWZMZSMC) {
        this.JYYWZMZSMC = JYYWZMZSMC;
    }
    @JsonProperty("JYYWZMZSMCBM")
    public String getJYYWZMZSMCBM() {
        return JYYWZMZSMCBM;
    }

    public void setJYYWZMZSMCBM(String JYYWZMZSMCBM) {
        this.JYYWZMZSMCBM = JYYWZMZSMCBM;
    }
    @JsonProperty("JYYWZMZSH")
    public String getJYYWZMZSH() {
        return JYYWZMZSH;
    }

    public void setJYYWZMZSH(String JYYWZMZSH) {
        this.JYYWZMZSH = JYYWZMZSH;
    }
    @JsonProperty("DJYWZMZSMC")
    public String getDJYWZMZSMC() {
        return DJYWZMZSMC;
    }

    public void setDJYWZMZSMC(String DJYWZMZSMC) {
        this.DJYWZMZSMC = DJYWZMZSMC;
    }
    @JsonProperty("DJYWZMZSMCBM")
    public String getDJYWZMZSMCBM() {
        return DJYWZMZSMCBM;
    }

    public void setDJYWZMZSMCBM(String DJYWZMZSMCBM) {
        this.DJYWZMZSMCBM = DJYWZMZSMCBM;
    }
    @JsonProperty("DJYWZMZSH")
    public String getDJYWZMZSH() {
        return DJYWZMZSH;
    }

    public void setDJYWZMZSH(String DJYWZMZSH) {
        this.DJYWZMZSH = DJYWZMZSH;
    }
    @JsonProperty("JYZLB")
    public String getJYZLB() {
        return JYZLB;
    }

    public void setJYZLB(String JYZLB) {
        this.JYZLB = JYZLB;
    }
    @JsonProperty("JYZLBBM")
    public String getJYZLBBM() {
        return JYZLBBM;
    }

    public void setJYZLBBM(String JYZLBBM) {
        this.JYZLBBM = JYZLBBM;
    }
    @JsonProperty("JYZQC")
    public String getJYZQC() {
        return JYZQC;
    }

    public void setJYZQC(String JYZQC) {
        this.JYZQC = JYZQC;
    }
    @JsonProperty("JYZZJMC")
    public String getJYZZJMC() {
        return JYZZJMC;
    }

    public void setJYZZJMC(String JYZZJMC) {
        this.JYZZJMC = JYZZJMC;
    }
    @JsonProperty("JYZZJMCBM")
    public String getJYZZJMCBM() {
        return JYZZJMCBM;
    }

    public void setJYZZJMCBM(String JYZZJMCBM) {
        this.JYZZJMCBM = JYZZJMCBM;
    }
    @JsonProperty("JYZZJHM")
    public String getJYZZJHM() {
        return JYZZJHM;
    }

    public void setJYZZJHM(String JYZZJHM) {
        this.JYZZJHM = JYZZJHM;
    }
    @JsonProperty("JYZXZ")
    public String getJYZXZ() {
        return JYZXZ;
    }

    public void setJYZXZ(String JYZXZ) {
        this.JYZXZ = JYZXZ;
    }
    @JsonProperty("JYZXZBM")
    public String getJYZXZBM() {
        return JYZXZBM;
    }

    public void setJYZXZBM(String JYZXZBM) {
        this.JYZXZBM = JYZXZBM;
    }
    @JsonProperty("JYZHJ")
    public String getJYZHJ() {
        return JYZHJ;
    }

    public void setJYZHJ(String JYZHJ) {
        this.JYZHJ = JYZHJ;
    }
    @JsonProperty("JYZHJXZQH")
    public String getJYZHJXZQH() {
        return JYZHJXZQH;
    }

    public void setJYZHJXZQH(String JYZHJXZQH) {
        this.JYZHJXZQH = JYZHJXZQH;
    }
    @JsonProperty("BDCDYH")
    public String getBDCDYH() {
        return BDCDYH;
    }

    public void setBDCDYH(String BDCDYH) {
        this.BDCDYH = BDCDYH;
    }
    @JsonProperty("FWBM")
    public String getFWBM() {
        return FWBM;
    }

    public void setFWBM(String FWBM) {
        this.FWBM = FWBM;
    }
    @JsonProperty("FWZT")
    public String getFWZT() {
        return FWZT;
    }

    public void setFWZT(String FWZT) {
        this.FWZT = FWZT;
    }
    @JsonProperty("XZQHDM")
    public String getXZQHDM() {
        return XZQHDM;
    }

    public void setXZQHDM(String XZQHDM) {
        this.XZQHDM = XZQHDM;
    }
    @JsonProperty("QX")
    public String getQX() {
        return QX;
    }

    public void setQX(String QX) {
        this.QX = QX;
    }
    @JsonProperty("XZJDB")
    public String getXZJDB() {
        return XZJDB;
    }

    public void setXZJDB(String XZJDB) {
        this.XZJDB = XZJDB;
    }
    @JsonProperty("JJXZQH")
    public String getJJXZQH() {
        return JJXZQH;
    }

    public void setJJXZQH(String JJXZQH) {
        this.JJXZQH = JJXZQH;
    }
    @JsonProperty("JJXZQHDM")
    public String getJJXZQHDM() {
        return JJXZQHDM;
    }

    public void setJJXZQHDM(String JJXZQHDM) {
        this.JJXZQHDM = JJXZQHDM;
    }
    @JsonProperty("LJX")
    public String getLJX() {
        return LJX;
    }

    public void setLJX(String LJX) {
        this.LJX = LJX;
    }
    @JsonProperty("XQ")
    public String getXQ() {
        return XQ;
    }

    public void setXQ(String XQ) {
        this.XQ = XQ;
    }
    @JsonProperty("LH")
    public String getLH() {
        return LH;
    }

    public void setLH(String LH) {
        this.LH = LH;
    }
    @JsonProperty("SZQSC")
    public Integer getSZQSC() {
        return SZQSC;
    }

    public void setSZQSC(Integer SZQSC) {
        this.SZQSC = SZQSC;
    }
    @JsonProperty("SZZZC")
    public Integer getSZZZC() {
        return SZZZC;
    }

    public void setSZZZC(Integer SZZZC) {
        this.SZZZC = SZZZC;
    }
    @JsonProperty("MYC")
    public String getMYC() {
        return MYC;
    }

    public void setMYC(String MYC) {
        this.MYC = MYC;
    }
    @JsonProperty("DY")
    public String getDY() {
        return DY;
    }

    public void setDY(String DY) {
        this.DY = DY;
    }
    @JsonProperty("FH")
    public String getFH() {
        return FH;
    }

    public void setFH(String FH) {
        this.FH = FH;
    }
    @JsonProperty("FWZL")
    public String getFWZL() {
        return FWZL;
    }

    public void setFWZL(String FWZL) {
        this.FWZL = FWZL;
    }
    @JsonProperty("HXJS")
    public String getHXJS() {
        return HXJS;
    }

    public void setHXJS(String HXJS) {
        this.HXJS = HXJS;
    }
    @JsonProperty("HXJSBM")
    public String getHXJSBM() {
        return HXJSBM;
    }

    public void setHXJSBM(String HXJSBM) {
        this.HXJSBM = HXJSBM;
    }
    @JsonProperty("HXJG")
    public String getHXJG() {
        return HXJG;
    }

    public void setHXJG(String HXJG) {
        this.HXJG = HXJG;
    }
    @JsonProperty("HXJGBM")
    public String getHXJGBM() {
        return HXJGBM;
    }

    public void setHXJGBM(String HXJGBM) {
        this.HXJGBM = HXJGBM;
    }
    @JsonProperty("FWCX")
    public String getFWCX() {
        return FWCX;
    }

    public void setFWCX(String FWCX) {
        this.FWCX = FWCX;
    }
    @JsonProperty("FWCXBM")
    public String getFWCXBM() {
        return FWCXBM;
    }

    public void setFWCXBM(String FWCXBM) {
        this.FWCXBM = FWCXBM;
    }
    @JsonProperty("JZMJ")
    public BigDecimal getJZMJ() {
        return JZMJ;
    }

    public void setJZMJ(BigDecimal JZMJ) {
        this.JZMJ = JZMJ;
    }
    @JsonProperty("TNJZMJ")
    public BigDecimal getTNJZMJ() {
        return TNJZMJ;
    }

    public void setTNJZMJ(BigDecimal TNJZMJ) {
        this.TNJZMJ = TNJZMJ;
    }
    @JsonProperty("GTJZMJ")
    public BigDecimal getGTJZMJ() {
        return GTJZMJ;
    }

    public void setGTJZMJ(BigDecimal GTJZMJ) {
        this.GTJZMJ = GTJZMJ;
    }
    @JsonProperty("GHYT")
    public String getGHYT() {
        return GHYT;
    }

    public void setGHYT(String GHYT) {
        this.GHYT = GHYT;
    }
    @JsonProperty("JZJG")
    public String getJZJG() {
        return JZJG;
    }

    public void setJZJG(String JZJG) {
        this.JZJG = JZJG;
    }
    @JsonProperty("JZJGBM")
    public String getJZJGBM() {
        return JZJGBM;
    }

    public void setJZJGBM(String JZJGBM) {
        this.JZJGBM = JZJGBM;
    }
    @JsonProperty("FWYT")
    public String getFWYT() {
        return FWYT;
    }

    public void setFWYT(String FWYT) {
        this.FWYT = FWYT;
    }
    @JsonProperty("FWYTBM")
    public String getFWYTBM() {
        return FWYTBM;
    }

    public void setFWYTBM(String FWYTBM) {
        this.FWYTBM = FWYTBM;
    }
    @JsonProperty("FWXZ")
    public String getFWXZ() {
        return FWXZ;
    }

    public void setFWXZ(String FWXZ) {
        this.FWXZ = FWXZ;
    }
    @JsonProperty("FWXZBM")
    public String getFWXZBM() {
        return FWXZBM;
    }

    public void setFWXZBM(String FWXZBM) {
        this.FWXZBM = FWXZBM;
    }
    @JsonProperty("FWLX")
    public String getFWLX() {
        return FWLX;
    }

    public void setFWLX(String FWLX) {
        this.FWLX = FWLX;
    }
    @JsonProperty("FWLXBM")
    public String getFWLXBM() {
        return FWLXBM;
    }

    public void setFWLXBM(String FWLXBM) {
        this.FWLXBM = FWLXBM;
    }
    @JsonProperty("GYFS")
    public String getGYFS() {
        return GYFS;
    }

    public void setGYFS(String GYFS) {
        this.GYFS = GYFS;
    }
    @JsonProperty("GYFSBM")
    public String getGYFSBM() {
        return GYFSBM;
    }

    public void setGYFSBM(String GYFSBM) {
        this.GYFSBM = GYFSBM;
    }
    @JsonProperty("SZFE")
    public String getSZFE() {
        return SZFE;
    }

    public void setSZFE(String SZFE) {
        this.SZFE = SZFE;
    }
    @JsonProperty("CJJE")
    public BigDecimal getCJJE() {
        return CJJE;
    }

    public void setCJJE(BigDecimal CJJE) {
        this.CJJE = CJJE;
    }
    @JsonProperty("FKLX")
    public String getFKLX() {
        return FKLX;
    }

    public void setFKLX(String FKLX) {
        this.FKLX = FKLX;
    }
    @JsonProperty("FKLXBM")
    public String getFKLXBM() {
        return FKLXBM;
    }

    public void setFKLXBM(String FKLXBM) {
        this.FKLXBM = FKLXBM;
    }
    @JsonProperty("DKFS")
    public String getDKFS() {
        return DKFS;
    }

    public void setDKFS(String DKFS) {
        this.DKFS = DKFS;
    }
    @JsonProperty("DKFSBM")
    public String getDKFSBM() {
        return DKFSBM;
    }

    public void setDKFSBM(String DKFSBM) {
        this.DKFSBM = DKFSBM;
    }
    @JsonProperty("HTSXRQ")
    public String getHTSXRQ() {
        return HTSXRQ;
    }

    public void setHTSXRQ(String HTSXRQ) {
        this.HTSXRQ = HTSXRQ;
    }
    @JsonProperty("YWBJSJ")
    public String getYWBJSJ() {
        return YWBJSJ;
    }

    public void setYWBJSJ(String YWBJSJ) {
        this.YWBJSJ = YWBJSJ;
    }
    @JsonProperty("YWBJRSFZH")
    public String getYWBJRSFZH() {
        return YWBJRSFZH;
    }

    public void setYWBJRSFZH(String YWBJRSFZH) {
        this.YWBJRSFZH = YWBJRSFZH;
    }
    @JsonProperty("YWBLSZXZQHDM")
    public String getYWBLSZXZQHDM() {
        return YWBLSZXZQHDM;
    }

    public void setYWBLSZXZQHDM(String YWBLSZXZQHDM) {
        this.YWBLSZXZQHDM = YWBLSZXZQHDM;
    }
}