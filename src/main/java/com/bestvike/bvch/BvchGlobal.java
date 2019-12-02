package com.bestvike.bvch;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class BvchGlobal {
    //注册相关常量
    //用户密码
    public static String User_Pass = "666666";
    //物业公司类型
    public static String CORP_TYPE = "02";
    //申请个人类型
    public static String A_CORP_TYPE = "07";
    //城市编码
    public static String CITY_CODE = "130500";
    //物业公司角色编码
    public static String ROLE_CODE = "20";
    //申请个人角色编码
    public static String A_ROLE_CODE = "60";
    //系统编码
    public static String SYS_CODE = "02";
    //外网用户注册默认状态
    public static String SYS_USER_DEFAULT_STATE = "0000";
    public static String SYS_USER_STATE = "9999";

    // 图档类型
    public static String C_PRO_FILE_TYPE = "0001";
    public static String A_PRO_FILE_TYPE = "0008";
    public static String PRO_SUB_TYPE_20 = "20";
    public static String PRO_SUB_TYPE_21 = "21";
    public static String PRO_SUB_TYPE_9916 = "9916";
    public static String PRO_SUB_TYPE_9917 = "9917";

    // 项目申请相关常量
    // step_办理阶段：字典（1_临时保存、2_项目申请、3_项目申报、4_费用分摊、5_签字公示、6_拨款审核、7_竣工验收、8_全部支付）
    // state_办理状态：字典（0_完成、1_待办、2_不通过）
    public static String STEP_TEMP_SAVE = "1";
    public static String STEP_PRE_REQ = "2";
    public static String STEP_REQ = "3";
    public static String STEP_AMOUNT = "4";
    public static String STEP_SIGN_PUBLIC = "5";
    public static String STEP_PAY_CONFIRM = "6";
    public static String STEP_COMPLET = "7";
    public static String PAY_COMPLET = "8";

    public static String STATE_FINISH = "0";
    public static String STATE_NEED_DEAL = "1";
    public static String STATE_NOT_THROUGH = "2";
    // step_办理阶段：字典（1_临时保存、2_项目申请、3_拨款申请、4_全部支付）
    // state_办理状态：字典（0_完成、1_待办、2_受理中、3_不通过）
    public static String STEP_REQ_ALL = "2";
    public static String PAY_REQ = "3";
    public static String ALL_PAY_COMPLET = "4";

    public static String STATE_ACCEPT = "2";
    public static String STATE_NOT_PASS = "3";

    // 项目受理申请编号类型
    public static String REP_TYPE = "31";

    // 图档类型
    public static String FILE_STATE = "0000"; //图档正常状态

    public static String FILE_BUSI_PATH = "13050002"; //图档文件业务路径
    public static String REP_DOC_TYPE = "09";
    public static String PRO_FILE_TYPE = "0901"; //业务类型
    public static String PRO_FILE_TYPE_0908 = "0908"; //业务类型

    public static String PRO_SUB_TYPE_9918 = "9918"; //待维修部位现场照片

    public static String PRO_SUB_TYPE_09 = "09"; //使用申请表
    public static String PRO_SUB_TYPE_9919 = "9919";//委托书
    public static String PRO_SUB_TYPE_9920 = "9920";//维修单位相关证件
    public static String PRO_SUB_TYPE_12 = "12";//工程造价
    public static String PRO_SUB_TYPE_14 = "14";//施工合同

    public static String PRO_SUB_TYPE_9921 = "9921";//资金分摊签字表
    public static String PRO_SUB_TYPE_9922 = "9922";//公示书
    public static String PRO_SUB_TYPE_13 = "13";//现场公示图片

    public static String PRO_SUB_TYPE_17 = "17"; //竣工验收报告

    // 流程名称
    public static String FLOW_PRE_REQ = "expend_process_projectapply";
    public static String FLOW_REQ = "expend_process_projectdeclare";
    public static String FLOW_SIGN = "expend_process_projectpublic";
    public static String FLOW_COMPLET = "expend_process_appropriateAccept";

    // 图档下载url
    //public static String PROJECT_ALLOT_EXPORT = "http://172.10.10.51:8082/bvrfis/accept/ExpendActionJasper-projectAllotListForExport.bv";
    //public static String PROJECT_SIGN_EXPORT = "http://172.10.10.51:8082/bvrfis/accept/ExpendActionJasper-printPublicAnnouncementAction.bv";
    //public static String PROJECT_COMPLETE_EXPORT = "http://172.10.10.51:8082/bvrfis/accept/ExpendActionJasper-projectCompleteForExport.bv";
    // 邢台测试环境
    //public static String PROJECT_ALLOT_EXPORT = "http://172.16.9.2:9090/bvrfis/accept/ExpendActionJasper-projectAllotListForExport.bv";
    //public static String PROJECT_SIGN_EXPORT = "http://172.16.9.2:9090/bvrfis/accept/ExpendActionJasper-printPublicAnnouncementAction.bv";
    //public static String PROJECT_COMPLETE_EXPORT = "http://172.16.9.2:9090/bvrfis/accept/ExpendActionJasper-projectCompleteForExport.bv";
    // 邢台生产环境
    public static String PROJECT_ALLOT_EXPORT = "http://172.16.9.1:8080/bvrfis/accept/ExpendActionJasper-projectAllotListForExport.bv";
    public static String PROJECT_SIGN_EXPORT = "http://172.16.9.1:8080/bvrfis/accept/ExpendActionJasper-printPublicAnnouncementAction.bv";
    public static String PROJECT_COMPLETE_EXPORT = "http://172.16.9.1:8080/bvrfis/accept/ExpendActionJasper-projectCompleteForExport.bv";
    // 内部测试人员
    //public static String PROJECT_ALLOT_EXPORT = "http://127.0.0.1:9093/bvrfis/accept/ExpendActionJasper-projectAllotListForExport.bv";
    //public static String PROJECT_SIGN_EXPORT = "http://127.0.0.1:9093/bvrfis/accept/ExpendActionJasper-printPublicAnnouncementAction.bv";
    //public static String PROJECT_COMPLETE_EXPORT = "http://127.0.0.1:9093/bvrfis/accept/ExpendActionJasper-projectCompleteForExport.bv";


    // 签字通过比例
    public static BigDecimal PASS_SCALE = BigDecimal.valueOf(66);

    private static Map<String, String> stepMap = new HashMap<String, String>();
    private static Map<String, String> stateMap = new HashMap<String, String>();

    private static Map<String, String> newStepMap = new HashMap<String, String>();
    private static Map<String, String> newStateMap = new HashMap<String, String>();

    static {
        stepMap.put("1", "项目保存");
        stepMap.put("2", "项目申请");
        stepMap.put("3", "项目申报");
        stepMap.put("4", "费用分摊");
        stepMap.put("5", "签字公示");
        stepMap.put("6", "拨款审核");
        stepMap.put("7", "竣工验收");
        stepMap.put("8", "全部支付");

        stateMap.put("0", "完成");
        stateMap.put("1", "待办");
        stateMap.put("2", "不通过");

        newStepMap.put("1", "临时保存");
        newStepMap.put("2", "项目申请");
        newStepMap.put("3", "拨款申请");
        newStepMap.put("4", "全部支付");

        newStateMap.put("0", "完成");
        newStateMap.put("1", "待办");
        newStateMap.put("2", "受理中");
        newStateMap.put("3", "不通过");
    }

    public static String getStepState(String step, String state) {
        //return stepMap.get(step) + "_" + stateMap.get(state);
        String stepName = newStepMap.get(step);
        if(stepName==null) stepName="未知阶段";
        String stateName = newStateMap.get(state);
        if(stateName==null) stateName="未知状态";
        return stepName + "_" + stateName;
    }

    // 编号生成最后一位校验位
    public static String getCheckDigit(String str) {
        int[][] table = {{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, {0, 2, 4, 6, 8, 1, 3, 5, 7, 9}};
        int sum = 0;
        int i = str.length() - 1;
        for (int odd = 0; i >= 0; i--) {
            sum += table[(odd = 1 - odd)][(str.charAt(i) - '0')];
        }
        sum %= 10;
        return String.valueOf(sum != 0 ? 10 - sum : 0);
    }
}
