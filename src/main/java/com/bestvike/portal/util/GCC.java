package com.bestvike.portal.util;

/**
 * @ClassName：GCC
 * @Description：全局静态变量 GlobalConstCode
 * @Author：ybb
 * @Date：2018/1/25 11:28
 * @Version：1.0
 */
public class GCC {

    /**
     * 系统 城市代码
     */
    public static String BORTAL_CITYCODE = "371403";

    /**
     * 系统 区域配置 上级区域  ROOT_根节点
     */
    public static String BORTAL_PARENTAREA_ROOTNODE = "ROOT";

    /**
     * 系统 默认机构代码
     */
    public static String BORTAL_DEPTID = "00";

    /** ******************基础数据表  数据状态  start ************************** */
    /**
     * 基础数据表  数据状态  normal_正常
     */
    public static String DATACENTER_DATASTATE_NORMAL = "normal";
    /**
     * 基础数据表  数据状态  delete_删除
     */
    public static String DATACENTER_DATASTATE_DELETE = "delete";
    /** ******************基础数据表  数据状态  end   ************************** */


    /** ******************基础数据表  操作类型  start ************************** */
    /**
     * 基础数据表  操作类型  insert_新增
     */
    public static String DATACENTER_OPERATETYPE_INSERT = "insert";
    /**
     * 基础数据表  操作类型  update_修改
     */
    public static String DATACENTER_OPERATETYPE_UPDATE = "update";
    /**
     * 基础数据表  操作类型  delete_删除
     */
    public static String DATACENTER_OPERATETYPE_DELETE = "delete";
    /** ******************基础数据表  操作类型  end   ************************** */


    /** ******************基础数据表  数据来源  start ************************** */
    /**
     * 基础数据表  数据来源  bvdf_网签系统
     */
    public static String DATACENTER_APPCODE_BVDF = "bvdf";
    /**
     * 基础数据表  数据来源  bvys_监管系统
     */
    public static String DATACENTER_APPCODE_BVYS = "bvys";
    /**
     * 基础数据表  数据来源  bvfris_维修资金系统
     */
    public static String DATACENTER_APPCODE_BVFRIS = "bvfris";
    /**
     * 基础数据表  数据来源  bvpims_物业系统
     */
    public static String DATACENTER_APPCODE_BVPIMS = "bvpims";
    /**
     * 基础数据表  数据来源  bvpqis_保修金系统
     */
    public static String DATACENTER_APPCODE_BVPQIS = "bvpqis";
    /**
     * 基础数据表  数据来源  datacenter_数据中心
     */
    public static String DATACENTER_APPCODE_DATACENTER = "datacenter";
    /** ******************基础数据表  数据来源 end   ************************** */

    /** ******************机构信息表  机构状态  start ************************** */
    /**
     * 机构信息表  机构状态  0000_正常
     */
    public static String DEPT_DEPTSTATE_NORMAL = "0000";
    /**
     * 机构信息表  机构状态  9999_删除
     */
    public static String DEPT_DEPTSTATE_DELETE = "9999";
    /** ******************机构信息表  机构状态 end  ************************** */

    /** ******************基础数据表  数据传输类型  start ************************** */
    /**
     * 基础数据表  数据传输类型  corpInfo_企业数据
     */
    public static String DATACENTER_DATATYPE_CORPINFO = "corpInfo";
    /**
     * 基础数据表  数据传输类型  projectInfo_项目数据
     */
    public static String DATACENTER_DATATYPE_PROJECTINFO = "projectInfo";
    /**
     * 基础数据表  数据传输类型  bankInfo_银行数据
     */
    public static String DATACENTER_DATATYPE_BANKINFO = "bankInfo";
    /** ******************基础数据表  数据传输类型  end   ************************** */

    /** ******************用户信息表  用户状态  start ************************** */
    /**
     * 用户信息表  用户状态  0000_正常
     */
    public static String USER_STATE_NORMAL = "0000";
    /**
     * 用户信息表  用户状态  9999_删除
     */
    public static String USER_STATE_DELETE = "9999";
    /** ******************用户信息表  用户状态 end  ************************** */
}
