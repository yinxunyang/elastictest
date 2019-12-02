package com.bestvike.bvch.service.impl;

import com.bestvike.bvch.param.*;
import com.bestvike.bvch.sequence.ITradeSequenceService;
import com.bestvike.bvch.service.ICommitTransService;
import com.bestvike.bvch.service.ILogDataService;
import com.bestvike.bvch.service.IRecodeTimeService;
import com.bestvike.bvch.service.ZjbService;
import com.bestvike.bvch.utils.MidTableConvert;
import com.bestvike.portal.dao.TradeRecordDao;
import com.bestvike.portal.data.TradeRecord;
import com.bestvike.portal.service.BaseService;
import com.bestvike.rfis.dao.*;
import com.bestvike.rfis.data.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ZjbServiceImpl extends BaseService implements ZjbService {

    @Autowired
    private ArcBuildInfoDao arcBuildInfoDao;
    //将所有表的dao注入进来用于查询
    @Autowired
    private ArcHouseInfoDao arcHouseInfoDao;  //房屋信息表
    @Autowired
    private ConBaseInfoDao conBaseInfoDao; //合同信息表
    @Autowired
    private ArcProjectInfoDao arcProjectInfoDao;//项目信息表表
    @Autowired
    private ArcCorpInfoDao arcCorpInfoDao;//企业信息表
    @Autowired
    private ConBuyerInfoDao conBuyerInfoDao;//买受人信息表
    @Autowired
    private ConHouseListDao conHouseListDao;//合同房屋信息表
    @Autowired
    private ILogDataService iLogDataService;
    @Autowired
    private TradeRecordDao tradeRecordDao;  //标准表
    @Autowired
    private ITradeSequenceService iTradeSequenceService;
    @Autowired
    private ICommitTransService iCommitTransService;
	@Autowired
	private IRecodeTimeService iRecodeTimeService;
	@Autowired
	private MidTableConvert midTableConvert;

    @Value("${config.XZQHDM}")
    private String XZQHDM;
    @Value("${config.LLYWLB}")
    private String LLYWLB;
    @Value("${config.JYYWZMZSMC}")
    private String JYYWZMZSMC;
    @Value("${config.JYZLB}")
    private String JYZLB;
    @Value("${config.JYZLBBM}")
    private String JYZLBBM;
    @Value("${config.QYJYZLB}")
    private String QYJYZLB;
    @Value("${config.QYJYZLBBM}")
    private String QYJYZLBBM;

    @Value("${config.KSXH}")
    private Integer KSXH;

    @Value("${config.portpassword}")
    private String portpassword;  //接口密码
    /**
     * 接口返回成功的key值
     */
    public static final String RESPONSE_SUCCESS = "success";
    /**
     * 接口返回信息的key值
     */
    public static final String RESPONSE_MESSAGE = "message";
    /**
     * 是否主房，Y_是;N_否
     */
    public static final String CONHOUSELIST_ISMAIN = "Y";
    /**
     * 中间表批量提交事务的数量
     */
    public static final Integer TRADE_BATCH_NUM = 100;
    /**
     * conBaseInfo是否已迁移到tradeRecord表
     */
    public static final String IS_MERGE_TRADE_N = "N";
    /**
     * 备案状态3
     */
    public static final String CONSTRACT_STATE_RECORD = "3";
    /**
     * 合同信息表(Con_BaseInfo)最大查询条数
     */
    public static final Integer CON_BASE_NUM = 15000;
    /**
     * 房屋状态 正常
     */
    public static final String HOUSE_STATE_NORMAL = "0";
    /**
     * 系统时间前5分钟
     */
    public static final Integer MINUS_MINUTES = 5;
    /**
     * 无需要迁移的数据
     */
    public static final String NOTHING_TO_MERGE = "没有需要迁移的数据";
    /**
     * 房屋朝向固定为南
     */
    public static final String ROOM_DIRECTION_SOUTH = "南";
    /**
     * 房屋朝向固定为03
     */
    public static final String ROOM_DIRECTION_SOUTH_BM = "03";
    /**
     * 数据库共有人状态:2
     */
    public static final String CONBUYERINFO_PARTOWNER = "2";

    /*
     * 共同共有和共同共有编码
     * */
    public static final String HAVE_OWNER = "共同共有";
    public static final String HAVE_OWNER_BM = "02";
    /*
     * 单独占有和单独占有编码
     * */
    public static final String HAVE_LONER = "单独占有";
    public static final String HAVE_LONER_BM = "01";
     //共有人
    private String ownerWay;
    //共有人编码
    private String ownerWayBm ;
    //期房房屋状态为01
    String FWZT = "01";
    //交易状态
    String JYZT = "02";

	public static final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Scheduled(fixedRate = 1000 * 60 * 120)  //5分钟一次,开局自动执行
    public Map<String, String> fillData() {
	    try {
		    // 系统时间
		    LocalDateTime localDate = LocalDateTime.now();
		    // 取数开始执行时间
		    String beginTime = df.format(localDate);
		    HashMap<String, String> mapRsp = new HashMap<>();
		    // 查询日期最新的RecodeTime表
		    RecodeTime recodeTime = iRecodeTimeService.selectRecodeTimeByLastTime();
		    String logId;
		    int numbers;
		    if (recodeTime == null) {  //第一次
			    // 取数范围，结束时间，5分钟前的系统时间
			    String scopeEndTime = df.format(localDate.minusMinutes(MINUS_MINUTES));
			    // 查询 lasttime<scopeEndTime的数据
			    List<ConBaseInfo> allConBaseInfoList = queryConBaseInfoByTime(null, scopeEndTime);
			    if (allConBaseInfoList.isEmpty()) {
				    // 返回成功信息
				    mapRsp.put(RESPONSE_SUCCESS, "1");
				    mapRsp.put(RESPONSE_MESSAGE, NOTHING_TO_MERGE);
				    return mapRsp;
			    }
			    List<TradeRecord> tradeRecordList = new ArrayList<>();
			    // 生成logId
			    logId = iTradeSequenceService.generateLogId(scopeEndTime);
			    // 分批执行中间表
			    conBaseInfoInBatch(allConBaseInfoList, tradeRecordList, logId);
			    numbers = tradeRecordList.size(); //记录数
			    // 取数结束执行时间
			    String endTime = df.format(LocalDateTime.now());
			    iLogDataService.addLog(new LogData(logId, beginTime, endTime, numbers, "起始", scopeEndTime));//日志表
			    int notMergeNum = countNotMergeConBaseInfo(null, scopeEndTime);
			    // 没有需要迁移的数据时生成RecodeTime表
			    if (notMergeNum == 0) {
				    log.info("迁移完数据，生成RecodeTime表");
				    iRecodeTimeService.saveRecode(new RecodeTime(1, scopeEndTime, MINUS_MINUTES));
			    } else {
				    log.info("还有未迁移完的数据，不生成RecodeTime表，logId为{}", logId);
			    }
		    } else {
			    Integer recodexh = recodeTime.getXh();
			    // 取数范围，开始时间，大于等于scopeBeginTime
			    String scopeBeginTime = recodeTime.getEndExecuteTime();
			    // 提前多少分钟执行
			    Integer beforeTimeInt = recodeTime.getBeforeTime();
			    // 取数范围，结束时间，5分钟前的系统时间
			    String scopeEndTime = df.format(localDate.minusMinutes(beforeTimeInt));
			    //查询 scopeBeginTime<=lasttime<scopeEndTime之间的数据
			    List<ConBaseInfo> allConBaseInfoList = queryConBaseInfoByTime(scopeBeginTime, scopeEndTime);
			    if (allConBaseInfoList.isEmpty()) {
				    // 返回成功信息
				    mapRsp.put(RESPONSE_SUCCESS, "1");
				    mapRsp.put(RESPONSE_MESSAGE, NOTHING_TO_MERGE);
				    return mapRsp;
			    }
			    List<TradeRecord> tradeRecordList = new ArrayList<>();
			    // 生成logId
			    logId = iTradeSequenceService.generateLogId(scopeEndTime);
			    // 分批执行中间表
			    conBaseInfoInBatch(allConBaseInfoList, tradeRecordList, logId);
			    numbers = tradeRecordList.size(); //记录数
			    // 取数结束执行时间
			    String endTime = df.format(LocalDateTime.now());
			    iLogDataService.addLog(new LogData(logId, beginTime, endTime, numbers, scopeBeginTime, scopeEndTime));//日志表
			    // 没有需要迁移的数据时更新时间
			    int notMergeNum = countNotMergeConBaseInfo(scopeBeginTime, scopeEndTime);
			    if (notMergeNum == 0) {
				    log.info("迁移完数据，更新RecodeTime表");
				    iRecodeTimeService.updateRecode(new RecodeTime(recodexh, scopeEndTime, beforeTimeInt));
			    } else {
				    log.info("还有未迁移完的数据，不更新RecodeTime表，logId为{}", logId);
			    }
		    }
		    if (numbers > 0) {
			    LocalDateTime end = LocalDateTime.now();
			    Duration duration = Duration.between(localDate, end);
			    // 相差秒数
			    long seconds = duration.toMillis() / 1000;
			    log.info("成功迁移，生成{}条tradeRecord数据，logId为{}，耗时{}秒", numbers, logId, seconds);
			    // 返回成功信息
			    mapRsp.put(RESPONSE_SUCCESS, "1");
			    mapRsp.put(RESPONSE_MESSAGE, "数据迁移成功");
		    } else {
			    // 返回成功信息
			    log.error("迁移失败，生成0条tradeRecord数据，logId为{}", logId);
			    mapRsp.put(RESPONSE_SUCCESS, "0");
			    mapRsp.put(RESPONSE_MESSAGE, NOTHING_TO_MERGE);
		    }
		    return mapRsp;
	    } catch (Exception e) {
		    log.error("数据迁移失败", e);
		    HashMap<String, String> mapRsp = new HashMap<>();
		    // 返回失败信息
		    mapRsp.put(RESPONSE_SUCCESS, "0");
		    mapRsp.put(RESPONSE_MESSAGE, "数据迁移失败");
		    return mapRsp;
	    }
    }

	/**
	 * @Author: yinxunyang
	 * @Description: 根据时间查询合同表的数据
	 * @Date: 2019/9/17 11:40
	 */
	private List<ConBaseInfo> queryConBaseInfoByTime(String scopeBeginTime, String scopeEndTime) {
		ConBaseInfoParam conBaseInfoParam = new ConBaseInfoParam();
		// 取数范围，开始时间
		conBaseInfoParam.setScopeBeginTime(scopeBeginTime);
		// 取数范围，结束时间
		conBaseInfoParam.setScopeEndTime(scopeEndTime);
		// 查询3备案的
		conBaseInfoParam.setConstractState(CONSTRACT_STATE_RECORD);
		// 最多查询CON_BASE_NUM条，防止内存溢出
		conBaseInfoParam.setConBaseNum(CON_BASE_NUM);
		// 查询未迁移的数据
		conBaseInfoParam.setIsMergeTrade(IS_MERGE_TRADE_N);
		// 查询主房
		conBaseInfoParam.setHouseFlag(CONHOUSELIST_ISMAIN);
		// 房屋状态正常
		conBaseInfoParam.setHouseState(HOUSE_STATE_NORMAL);
		// 根据备案时间升序
		conBaseInfoParam.setByRecordTime("Y");
		return conBaseInfoDao.queryConBaseInfoByTime(conBaseInfoParam);
	}

	/**
	 * @Author: yinxunyang
	 * @Description: 根据时间查询未迁移的数量
	 * @Date: 2019/9/17 12:44
	 */
	private int countNotMergeConBaseInfo(String scopeBeginTime, String scopeEndTime) {
		ConBaseInfoParam conBaseInfoParam = new ConBaseInfoParam();
		// 取数范围，开始时间
		conBaseInfoParam.setScopeBeginTime(scopeBeginTime);
		// 取数范围，结束时间
		conBaseInfoParam.setScopeEndTime(scopeEndTime);
		// 查询4备案的
		conBaseInfoParam.setConstractState(CONSTRACT_STATE_RECORD);
		// 查询未迁移的数据
		conBaseInfoParam.setIsMergeTrade(IS_MERGE_TRADE_N);
		// 查询主房
		conBaseInfoParam.setHouseFlag(CONHOUSELIST_ISMAIN);
		// 房屋状态正常
		conBaseInfoParam.setHouseState(HOUSE_STATE_NORMAL);
		return conBaseInfoDao.countConBaseInfo(conBaseInfoParam);
	}

    /**
     * @Author: yinxunyang
     * @Description: 分批执行中间表
     * @Date: 2019/9/12 14:54
     */
    public void conBaseInfoInBatch(List<ConBaseInfo> allConBaseInfoList, List<TradeRecord> tradeRecodeList, String logId) {
        // 小于TRADE_BATCH_NUM条时，直接调用organizeAndAddTradeRecode
        if (!allConBaseInfoList.isEmpty() && allConBaseInfoList.size() <= TRADE_BATCH_NUM) {
            List<ConBaseInfoParam> conBaseInfoParamList = new ArrayList<>();
            organizeAndAddTradeRecode(allConBaseInfoList, conBaseInfoParamList, tradeRecodeList, logId);
            // 新增tradeRecode和更新Con_BaseInfo，同事务处理
            iCommitTransService.addTradeAndUpdateContract(conBaseInfoParamList, tradeRecodeList);
        } else {
            // 分批执行
            int numbers = allConBaseInfoList.size();
            int pageNum = (numbers + TRADE_BATCH_NUM - 1) / TRADE_BATCH_NUM;
            for (int i = 1; i <= pageNum; i++) {
                List<ConBaseInfoParam> conBaseInfoParamList = new ArrayList<>();
                // 每批次eachTradeRecodeList
                List<TradeRecord> eachTradeRecodeList = new ArrayList<>();
                // 每批次eachConBaseInfoList
                List<ConBaseInfo> eachConBaseInfoList = allConBaseInfoList.stream()
                        .skip(TRADE_BATCH_NUM * (i - 1))
                        .limit(TRADE_BATCH_NUM)
                        .collect(Collectors.toList());
                organizeAndAddTradeRecode(eachConBaseInfoList, conBaseInfoParamList, eachTradeRecodeList, logId);
                // 新增tradeRecode和更新Con_BaseInfo，同事务处理
                iCommitTransService.addTradeAndUpdateContract(conBaseInfoParamList, eachTradeRecodeList);
                // 每批次eachTradeRecodeList添加到总的tradeRecodeList
                tradeRecodeList.addAll(eachTradeRecodeList);
            }
        }
    }


    /**
     * @Author: yinxunyang
     * @Description: 组织生成中间表
     * @Date: 2019/9/10 9:55
     */
    public void organizeAndAddTradeRecode(List<ConBaseInfo> allConBaseInfoList, List<ConBaseInfoParam> conBaseInfoParamList, List<TradeRecord> eachTradeRecodeList, String logId) {
        allConBaseInfoList.stream().forEach(conBaseInfo -> {
	        ConBaseInfoParam conBaseInfoParam = new ConBaseInfoParam();
	        // 已迁移
	        conBaseInfoParam.setIsMergeTrade("Y");
	        conBaseInfoParam.setLogId(logId);
	        conBaseInfoParam.setSysGuid(conBaseInfo.getSysGuid());
	        conBaseInfoParamList.add(conBaseInfoParam);

	        // 合同编号
	        String contractNo = conBaseInfo.getContractNo();

	        ArcHouseInfo arcHouseInfo = arcHouseInfoDao.getHouseBySysGuid(conBaseInfo.getHouseGuid());
	        if (null == arcHouseInfo) {
		        return;
	        }
	        ArcBuildInfo arcBuildInfo = arcBuildInfoDao.queryArcBuildInfoBybldNo(conBaseInfo.getBldNo());
	        if (null == arcBuildInfo) {
		        return;
	        }

            List<ConBuyerInfo> conBuyerInfoList = conBuyerInfoDao.queryConBuyerInfoBycontractNo(contractNo);
	        if (!conBuyerInfoList.isEmpty()) {
		        // 通过买方组织生成中间表
		        addTradeRecodeByBuyer(conBaseInfo, conBuyerInfoList, eachTradeRecodeList, arcHouseInfo, arcBuildInfo);
	        }

            // 通过企业组织生成中间表
            ArcProjectInfo arcProjectInfo = arcProjectInfoDao.queryArcProjectInfoByProjectNo(conBaseInfo.getProjectNo());
            if (arcProjectInfo == null) {
                return;
            }
	        ArcCorpInfo arcCorpInfo = arcCorpInfoDao.queryArcCorpInfoBycorpNo(arcProjectInfo.getCorpNo());
	        if (null != arcCorpInfo) {
		        addTradeRecodeByCorp(arcCorpInfo, conBaseInfo, eachTradeRecodeList, arcHouseInfo, arcBuildInfo);
            }
        });
    }


    /**
     * @Author: yinxunyang
     * @Description: 通过买方组织生成中间表
     * @Date: 2019/9/18 14:52
     */
    private void addTradeRecodeByBuyer(ConBaseInfo conBaseInfo, List<ConBuyerInfo> conBuyerInfoList,
                                       List<TradeRecord> eachTradeRecodeList, ArcHouseInfo arcHouseInfo, ArcBuildInfo arcBuildInfo) {


        conBuyerInfoList.forEach(conBuyerInfo -> {
            //判断共有方式和共有方式编码 如果一个合同下存在共有人 (状态:2) 设置 (共同共有 BM02) 否则为 (单独占有 BM01)
            HashMap<Object, Object> conBuyerMap = new HashMap<>();
            conBuyerMap.put("buyType",CONBUYERINFO_PARTOWNER);
            conBuyerMap.put("contractNo",conBaseInfo.getContractNo());
            Integer ownerNumber = conBuyerInfoDao.queryConBuyerInfoCountBycontractNo(conBuyerMap);
            ownerWay = HAVE_LONER;
            ownerWayBm = HAVE_LONER_BM;
            if (ownerNumber > 0){
                ownerWay = HAVE_OWNER;
                ownerWayBm = HAVE_OWNER_BM;
            }


            if (conBuyerInfo == null) {
                // 如果为空执行下一次遍历
                return;
            }
            String buyName = conBuyerInfo.getBuyName();//交易者全称
	        if (StringUtils.isEmpty(buyName)) {
                buyName = "无";
            }
	        BuyCertTypeParam buyCertTypeParam = new BuyCertTypeParam();
	        buyCertTypeParam.setBuyCertType(conBuyerInfo.getBuyCertType());
	        midTableConvert.buyCertTypeConvert(buyCertTypeParam);
	        //交易者证件名称
	        String buyCertType = buyCertTypeParam.getBuyCertType();
	        String buyCertTypeBm = buyCertTypeParam.getBuyCertTypeBm();

	        BuyXzParam buyXzParam = new BuyXzParam();
	        buyXzParam.setBuyCertNo(conBuyerInfo.getBuyCertNo());
	        midTableConvert.buyXzConvert(buyXzParam);
	        // 交易者证件号码
	        String buyCertNo = buyXzParam.getBuyCertNo();
	        // 交易者性质
	        String buyXz = buyXzParam.getBuyXz();
	        // 交易者性质编码
	        String buyXzBm = buyXzParam.getBuyXzBm();
	        if ((!"01".equals(buyCertTypeBm)&&(!"03".equals(buyCertTypeBm)))){
	            buyXzBm = "99";
                buyXz = "无";
            }
	        //交易者户籍行政区划
            String buyXzqh = "999999";
            if("01".equals(buyXzBm)){
             buyXzqh = "371400";
            }

            String contractType = conBaseInfo.getContractType();
	        // 房屋状态
	        //String FWZT = midTableConvert.fWZTConvert(contractType);
            //String FWZT = "01";
	        String houseGuid = arcHouseInfo.getSysGuid();  //房屋编码
            //层房序号
            String floorNo = arcHouseInfo.getFloorNo();
            String bldNo = conBaseInfo.getBldNo();  //楼号
	        if (StringUtils.isEmpty(bldNo)) {
		        bldNo = "无";
	        }
            Integer underFloor = arcBuildInfo.getUnderFloor();//所在起始层
            if (underFloor == 0) {
                underFloor = 1;
            }
            underFloor = underFloor - (underFloor + underFloor);
            Integer overFloor = arcBuildInfo.getOverFloor();//所在终止层
            String roomNo = arcHouseInfo.getRoomNo(); //房号
            String address = arcHouseInfo.getAddress();//房屋坐落

	        HouseHoldParam houseHoldParam = new HouseHoldParam();
	        //户型居室
	        houseHoldParam.setHouseHold(arcHouseInfo.getHouseHold());
	        midTableConvert.houseHoldConvert(houseHoldParam);
	        String houseHold = houseHoldParam.getHouseHold();
	        String houseHoldBM = houseHoldParam.getHouseHoldBM();

	        HouseStructureParam houseStructureParam = new HouseStructureParam();
	        //户型结构
	        houseStructureParam.setHouseStructure(arcHouseInfo.getHouseStructure());
	        midTableConvert.houseStructureConvert(houseStructureParam);
	        String houseStructure = houseStructureParam.getHouseStructure();
	        String houseStructureMB = houseStructureParam.getHouseStructureMB();

	        RoomDirectionParam roomDirectionParam = new RoomDirectionParam();
	        roomDirectionParam.setRoomDirection(arcHouseInfo.getRoomDirection());
	        midTableConvert.roomDirectionConvert(roomDirectionParam);
	        // 房屋朝向
	        String roomDirection = roomDirectionParam.getRoomDirection();
	        String roomDirectionBM = roomDirectionParam.getRoomDirectionBM();

	      /*  //房屋朝向不再判断,固定设置全部朝南  BM为03
            String roomDirection = ROOM_DIRECTION_SOUTH;
            String roomDirectionBM = ROOM_DIRECTION_SOUTH_BM;*/

            BigDecimal constructArea = arcHouseInfo.getConstructArea();//建筑面积

            BigDecimal ownArea = arcHouseInfo.getOwnArea();//套内建筑面积
            BigDecimal shareArea = arcHouseInfo.getShareArea();//公摊建筑面积
            if (shareArea == null) {
                shareArea = new BigDecimal(0);
                if (ownArea == null){
                    ownArea = constructArea;
                } else {
                    shareArea = constructArea.subtract(ownArea);
                }
            } else {
                if (ownArea == null){
                    ownArea = constructArea.subtract(shareArea);
                }
            }


	        FrameAttrParam frameAttrParam = new FrameAttrParam();
	        frameAttrParam.setFrameAttr(arcBuildInfo.getFrameAttr());
	        midTableConvert.frameAttrParamConvert(frameAttrParam);
	        //建筑结构
	        String frameAttr = frameAttrParam.getFrameAttr();
	        String frameAttrBM = frameAttrParam.getFrameAttrBM();

	        HouseUseParam houseUseParam = new HouseUseParam();
	        houseUseParam.setHouseUse(arcHouseInfo.getHouseUse());
	        midTableConvert.houseUseConvert(houseUseParam);
	        //房屋用途
	        String houseUse = houseUseParam.getHouseUse();
	        String houseUseBM = houseUseParam.getHouseUseBM();

	        HousePropParam housePropParam = new HousePropParam();
	        housePropParam.setHouseProp(arcHouseInfo.getHouseProp());
	        midTableConvert.housePropConvert(housePropParam);
	        // 房屋性质
	        String houseProp = housePropParam.getHouseProp();
	        String housePropBM = housePropParam.getHousePropBM();

	        HouseTypeParam houseTypeParam = new HouseTypeParam();
	        houseTypeParam.setHouseType(arcHouseInfo.getHouseType());
	        midTableConvert.houseTypeConvert(houseTypeParam);
	        // 房屋类型
	        String houseType = houseTypeParam.getHouseType();
	        String houseTypeBM = houseTypeParam.getHouseTypeBM();

            BigDecimal totalPrice = conBaseInfo.getTotalPrice();//成交金额
            if (totalPrice == null) {
                totalPrice = new BigDecimal(0);
            }

	        PayTypeParam payTypeParam = new PayTypeParam();
	        payTypeParam.setPayType(conBaseInfo.getPayType());
	        midTableConvert.payTypeConvert(payTypeParam);
	        // 付款类型
	        String payType = payTypeParam.getPayType();
	        // 付款类型编码
	        String payTypeBM = payTypeParam.getPayTypeBM();
	        // 付款方式
	        String DKFS = payTypeParam.getPayWay();
	        // 付款方式编码
	        String DKFSBM = payTypeParam.getPayWayBM();

            String recordDate = conBaseInfo.getRecordDate();//合同生效日期
            String recordTime = conBaseInfo.getRecordTime();
            if (StringUtils.isEmpty(recordDate)) {
                recordDate = "无";
            } else {
                if(StringUtils.isEmpty(recordTime)){
                    recordDate = recordDate + " 00:00:01";
                }else {
                    recordDate = recordDate + " " + recordTime;
                }
            }
            String signingPersonnelCertcode = conBaseInfo.getSigningPersonnelCertcode();// 业务办结人身份证号
            if (signingPersonnelCertcode != null && signingPersonnelCertcode != "") {
	            signingPersonnelCertcode = midTableConvert.IDnumber15to18(signingPersonnelCertcode);
            }
            //for循环填充数据,并且将填好的对象加入到list集合中
            TradeRecord tradeRecord = new TradeRecord();
            tradeRecord.setXH(iTradeSequenceService.generateTradeXH());
	        tradeRecord.setYWBH(conBaseInfo.getContractNo());
            tradeRecord.setLLYWLB(LLYWLB);
            tradeRecord.setLLYWLBBM("005");
            tradeRecord.setSJYWLB("无");
            tradeRecord.setYYWBH(null);
            tradeRecord.setJYZT(JYZT);
            tradeRecord.setJYYWZMZSMC(JYYWZMZSMC);
            tradeRecord.setJYYWZMZSMCBM("002");
            tradeRecord.setJYYWZMZSH("无");
            tradeRecord.setDJYWZMZSMC("无");
            tradeRecord.setDJYWZMZSMCBM("999");
            tradeRecord.setDJYWZMZSH("无");
            tradeRecord.setJYZLB(JYZLB);
            tradeRecord.setJYZLBBM(JYZLBBM);
            tradeRecord.setJYZQC(buyName);
            tradeRecord.setJYZZJMC(buyCertType);
            tradeRecord.setJYZZJMCBM(buyCertTypeBm);
            tradeRecord.setJYZZJHM(buyCertNo);
            tradeRecord.setJYZXZ(buyXz);
            tradeRecord.setJYZXZBM(buyXzBm);
            tradeRecord.setJYZHJ("无");
            tradeRecord.setJYZHJXZQH(buyXzqh);
            tradeRecord.setBDCDYH("无");
            tradeRecord.setBDCDYH("无");
            tradeRecord.setFWBM(houseGuid);
            tradeRecord.setFWZT(FWZT);
            tradeRecord.setXZQHDM(XZQHDM); //平原行政区代码
            tradeRecord.setQX("无");
            tradeRecord.setXZJDB("无");
            tradeRecord.setJJXZQH("无");
            tradeRecord.setJJXZQHDM("无");
            tradeRecord.setLJX("无");
            tradeRecord.setXQ("无");
            tradeRecord.setLH(bldNo);
            tradeRecord.setSZQSC(underFloor);
            tradeRecord.setSZZZC(overFloor);
            tradeRecord.setMYC("无");
            tradeRecord.setDY(floorNo);
            tradeRecord.setFH(roomNo);
            tradeRecord.setFWZL(address);
            tradeRecord.setHXJS(houseHold);
            tradeRecord.setHXJSBM(houseHoldBM);
            tradeRecord.setHXJG(houseStructure);
            tradeRecord.setHXJGBM(houseStructureMB);
            tradeRecord.setFWCX(roomDirection);
            tradeRecord.setFWCXBM(roomDirectionBM);
            tradeRecord.setJZMJ(constructArea);
            tradeRecord.setTNJZMJ(ownArea);
            tradeRecord.setGTJZMJ(shareArea);
            tradeRecord.setGHYT("无");
            tradeRecord.setJZJG(frameAttr);
            tradeRecord.setJZJGBM(frameAttrBM);
            tradeRecord.setFWYT(houseUse);
            tradeRecord.setFWYTBM(houseUseBM);
            tradeRecord.setFWXZ(houseProp);
            tradeRecord.setFWXZBM(housePropBM);
            tradeRecord.setFWLX(houseType);
            tradeRecord.setFWLXBM(houseTypeBM);
            tradeRecord.setGYFS(ownerWay);
            tradeRecord.setGYFSBM(ownerWayBm);
            tradeRecord.setSZFE("无");
            tradeRecord.setCJJE(totalPrice);
            tradeRecord.setFKLX(payType);
            tradeRecord.setFKLXBM(payTypeBM);
            tradeRecord.setDKFS(DKFS);
            tradeRecord.setDKFSBM(DKFSBM);
            tradeRecord.setHTSXRQ(recordDate);
            tradeRecord.setYWBJSJ(recordDate);
            tradeRecord.setYWBJRSFZH(signingPersonnelCertcode);
            tradeRecord.setYWBLSZXZQHDM(null);
            eachTradeRecodeList.add(tradeRecord);
        });
    }

    /**
     * @Author: yinxunyang
     * @Description: 通过企业组织生成中间表
     * @Date: 2019/9/18 15:00
     */
    private void addTradeRecodeByCorp(ArcCorpInfo arcCorpInfo, ConBaseInfo conBaseInfo,
                                      List<TradeRecord> eachTradeRecodeList, ArcHouseInfo arcHouseInfo, ArcBuildInfo arcBuildInfo) {
        String corpName = arcCorpInfo.getCorpName();//企业人名称
        String buyName = corpName;//交易者全称
	    if (StringUtils.isEmpty(buyName)) {
            buyName = "无";
        }
        String buyCertType = "组织机构代码";//交易者证件名称
        String buyCertTypeBm = "07";//交易者证件名称编码

	    BuyXzParam buyXzParam = new BuyXzParam();
	    buyXzParam.setBuyCertNo(arcCorpInfo.getCertificateNo());
	    midTableConvert.buyXzConvert(buyXzParam);
        // 交易者证件号码
        String buyCertNo = buyXzParam.getBuyCertNo();
        // 交易者性质
        String buyXz = buyXzParam.getBuyXz();
        // 交易者性质编码
        String buyXzBm = buyXzParam.getBuyXzBm();
        if ((!"01".equals(buyCertTypeBm)&&(!"03".equals(buyCertTypeBm)))){
            buyXzBm = "99";
            buyXz = "无";
        }
        //交易者户籍行政区划
        String buyXzqh = "999999";
        if("01".equals(buyXzBm)){
            buyXzqh = "371400";
        }


	    String houseGuid = arcHouseInfo.getSysGuid();  //房屋编码
        //层房序号
        String floorNo = arcHouseInfo.getFloorNo();
        String contractType = conBaseInfo.getContractType();
	    // 房屋状态
	    //String FWZT = midTableConvert.fWZTConvert(contractType);
        //String FWZT = "01";
        String bldNo = conBaseInfo.getBldNo();  //楼号
	    if (StringUtils.isEmpty(bldNo)) {
            bldNo = "无";
        }

	    Integer underFloor = arcBuildInfo.getUnderFloor();//所在起始层
        if (underFloor == 0) {
            underFloor = 1;
        }
        underFloor = underFloor - (underFloor + underFloor);
        Integer overFloor = arcBuildInfo.getOverFloor();//所在终止层
        String roomNo = arcHouseInfo.getRoomNo(); //房号
        String address = arcHouseInfo.getAddress();//房屋坐落

	    HouseHoldParam houseHoldParam = new HouseHoldParam();
	    //户型居室
	    houseHoldParam.setHouseHold(arcHouseInfo.getHouseHold());
	    midTableConvert.houseHoldConvert(houseHoldParam);
	    String houseHold = houseHoldParam.getHouseHold();
	    String houseHoldBM = houseHoldParam.getHouseHoldBM();

	    HouseStructureParam houseStructureParam = new HouseStructureParam();
	    //户型结构
	    houseStructureParam.setHouseStructure(arcHouseInfo.getHouseStructure());
	    midTableConvert.houseStructureConvert(houseStructureParam);
	    String houseStructure = houseStructureParam.getHouseStructure();
	    String houseStructureMB = houseStructureParam.getHouseStructureMB();

	    RoomDirectionParam roomDirectionParam = new RoomDirectionParam();
	    roomDirectionParam.setRoomDirection(arcHouseInfo.getRoomDirection());
	    midTableConvert.roomDirectionConvert(roomDirectionParam);
	    // 房屋朝向
	    String roomDirection = roomDirectionParam.getRoomDirection();
	    String roomDirectionBM = roomDirectionParam.getRoomDirectionBM();

        //房屋朝向不再判断,固定设置全部朝南  BM为03
      /*  String roomDirection = ROOM_DIRECTION_SOUTH;
        String roomDirectionBM = ROOM_DIRECTION_SOUTH_BM;*/

        BigDecimal constructArea = arcHouseInfo.getConstructArea();//建筑面积

        BigDecimal ownArea = arcHouseInfo.getOwnArea();//套内建筑面积
        BigDecimal shareArea = arcHouseInfo.getShareArea();//公摊建筑面积
        if (shareArea == null) {
            shareArea = new BigDecimal(0);
            if (ownArea == null){
                ownArea = constructArea;
            } else {
                shareArea = constructArea.subtract(ownArea);
            }
        } else {
            if (ownArea == null){
                ownArea = constructArea.subtract(shareArea);
            }
        }

	    FrameAttrParam frameAttrParam = new FrameAttrParam();
	    frameAttrParam.setFrameAttr(arcBuildInfo.getFrameAttr());
	    midTableConvert.frameAttrParamConvert(frameAttrParam);
	    //建筑结构
	    String frameAttr = frameAttrParam.getFrameAttr();
	    String frameAttrBM = frameAttrParam.getFrameAttrBM();

	    HouseUseParam houseUseParam = new HouseUseParam();
	    houseUseParam.setHouseUse(arcHouseInfo.getHouseUse());
	    midTableConvert.houseUseConvert(houseUseParam);
	    //房屋用途
	    String houseUse = houseUseParam.getHouseUse();
	    String houseUseBM = houseUseParam.getHouseUseBM();

	    HousePropParam housePropParam = new HousePropParam();
	    housePropParam.setHouseProp(arcHouseInfo.getHouseProp());
	    midTableConvert.housePropConvert(housePropParam);
	    // 房屋性质
	    String houseProp = housePropParam.getHouseProp();
	    String housePropBM = housePropParam.getHousePropBM();

	    HouseTypeParam houseTypeParam = new HouseTypeParam();
	    houseTypeParam.setHouseType(arcHouseInfo.getHouseType());
	    midTableConvert.houseTypeConvert(houseTypeParam);
	    // 房屋类型
	    String houseType = houseTypeParam.getHouseType();
	    String houseTypeBM = houseTypeParam.getHouseTypeBM();

        BigDecimal totalPrice = conBaseInfo.getTotalPrice();//成交金额
        if (totalPrice == null) {
            totalPrice = new BigDecimal(0);
        }

	    PayTypeParam payTypeParam = new PayTypeParam();
	    payTypeParam.setPayType(conBaseInfo.getPayType());
	    midTableConvert.payTypeConvert(payTypeParam);
	    // 付款类型
	    String payType = payTypeParam.getPayType();
	    // 付款类型编码
	    String payTypeBM = payTypeParam.getPayTypeBM();
	    // 付款方式
	    String DKFS = payTypeParam.getPayWay();
	    // 付款方式编码
	    String DKFSBM = payTypeParam.getPayWayBM();

        String recordDate = conBaseInfo.getRecordDate();//合同生效日期
        String recordTime = conBaseInfo.getRecordTime();
	    if (StringUtils.isEmpty(recordDate)) {
            recordDate = "无";
        } else {
	         if(StringUtils.isEmpty(recordTime)){
                 recordDate = recordDate + " 00:00:01";
             }else {
                 recordDate = recordDate + " " + recordTime;
             }
        }
        String signingPersonnelCertcode = conBaseInfo.getSigningPersonnelCertcode();// 业务办结人身份证号
        if (signingPersonnelCertcode != null && signingPersonnelCertcode != "") {
	        signingPersonnelCertcode = midTableConvert.IDnumber15to18(signingPersonnelCertcode);
        } else {
            signingPersonnelCertcode = null;
        }


        //for循环填充数据,并且将填好的对象加入到list集合中
        //创建对象,装载数据,应该是一条数据
        TradeRecord tradeRecord = new TradeRecord();
        tradeRecord.setXH(iTradeSequenceService.generateTradeXH());
	    tradeRecord.setYWBH(conBaseInfo.getContractNo());
        tradeRecord.setLLYWLB(LLYWLB);
        tradeRecord.setLLYWLBBM("020");
        tradeRecord.setSJYWLB("无");
        tradeRecord.setYYWBH(null);
        tradeRecord.setJYZT(JYZT);
        tradeRecord.setJYYWZMZSMC(JYYWZMZSMC);
        tradeRecord.setJYYWZMZSMCBM("002");
        tradeRecord.setJYYWZMZSH("无");
        tradeRecord.setDJYWZMZSMC("无");
        tradeRecord.setDJYWZMZSMCBM("999");
        tradeRecord.setDJYWZMZSH("无");
        tradeRecord.setJYZLB(QYJYZLB);
        tradeRecord.setJYZLBBM(QYJYZLBBM);
        tradeRecord.setJYZQC(buyName);
        tradeRecord.setJYZZJMC(buyCertType);
        tradeRecord.setJYZZJMCBM(buyCertTypeBm);
        tradeRecord.setJYZZJHM(buyCertNo);
        tradeRecord.setJYZXZ(buyXz);
        tradeRecord.setJYZXZBM(buyXzBm);
        tradeRecord.setJYZHJ("无");
        tradeRecord.setJYZHJXZQH(buyXzqh);
        tradeRecord.setBDCDYH("无");
        tradeRecord.setFWBM(houseGuid);
        tradeRecord.setFWZT(FWZT);
        tradeRecord.setXZQHDM(XZQHDM); //平原行政区代码
        tradeRecord.setQX("无");
        tradeRecord.setXZJDB("无");
        tradeRecord.setJJXZQH("无");
        tradeRecord.setJJXZQHDM("无");
        tradeRecord.setLJX("无");
        tradeRecord.setXQ("无");
        tradeRecord.setLH(bldNo);
        tradeRecord.setSZQSC(underFloor);
        tradeRecord.setSZZZC(overFloor);
        tradeRecord.setMYC("无");
        tradeRecord.setDY(floorNo);
        tradeRecord.setFH(roomNo);
        tradeRecord.setFWZL(address);
        tradeRecord.setHXJS(houseHold);
        tradeRecord.setHXJSBM(houseHoldBM);
        tradeRecord.setHXJG(houseStructure);
        tradeRecord.setHXJGBM(houseStructureMB);
        tradeRecord.setFWCX(roomDirection);
        tradeRecord.setFWCXBM(roomDirectionBM);
        tradeRecord.setJZMJ(constructArea);
        tradeRecord.setTNJZMJ(ownArea);
        tradeRecord.setGTJZMJ(shareArea);
        tradeRecord.setGHYT("无");
        tradeRecord.setJZJG(frameAttr);
        tradeRecord.setJZJGBM(frameAttrBM);
        tradeRecord.setFWYT(houseUse);
        tradeRecord.setFWYTBM(houseUseBM);
        tradeRecord.setFWXZ(houseProp);
        tradeRecord.setFWXZBM(housePropBM);
        tradeRecord.setFWLX(houseType);
        tradeRecord.setFWLXBM(houseTypeBM);
        tradeRecord.setGYFS(ownerWay);
        tradeRecord.setGYFSBM(ownerWayBm);
        tradeRecord.setSZFE("无");
        tradeRecord.setCJJE(totalPrice);
        tradeRecord.setFKLX(payType);
        tradeRecord.setFKLXBM(payTypeBM);
        tradeRecord.setDKFS(DKFS);
        tradeRecord.setDKFSBM(DKFSBM);
        tradeRecord.setHTSXRQ(recordDate);
        tradeRecord.setYWBJSJ(recordDate);
        tradeRecord.setYWBJRSFZH(signingPersonnelCertcode);
        tradeRecord.setYWBLSZXZQHDM(null);
        eachTradeRecodeList.add(tradeRecord);
    }
    /**
     * @Title: query
     * @Description: 查询接口
     * @author jiaguangyuan
     * @date 2019年8月24日15:48:41
     * @param begin_xh 检索数据，按xh升序排列，返回大于begin_xh的前count条数据
     * @param count    本次拉取数据条数
     * @param password 接口密码
     * @return map
     */
    //查询接口
    public Map<String, Object> query(Integer begin_xh, Integer count, String password) {
        List<TradeRecord> tradeRecords = null;
        try {
            //验证密码
            if (!portpassword.equals(password)){
                HashMap<String, Object> mapRsp = new HashMap<>();
                mapRsp.put(RESPONSE_SUCCESS, 0);
                mapRsp.put(RESPONSE_MESSAGE, "查询失败");
                mapRsp.put("data", tradeRecords);
                return mapRsp;
            }

            HashMap<Object, Object> map = new HashMap<>();
            map.put("begin_xh", begin_xh);
            map.put("count", count);
            map.put("password", password);
            //查出来count条 按begin_xh进行排列
            tradeRecords = tradeRecordDao.selectByParamter(map);
        } catch (Exception e) {
            HashMap<String, Object> mapRsp = new HashMap<>();
            mapRsp.put(RESPONSE_SUCCESS, 0);
            mapRsp.put(RESPONSE_MESSAGE, "查询失败");
            mapRsp.put("data", tradeRecords);
            return mapRsp;
        }
        HashMap<String, Object> mapRsp = new HashMap<>();
        mapRsp.put(RESPONSE_SUCCESS, 1);
        mapRsp.put(RESPONSE_MESSAGE, "查询成功");
        mapRsp.put("data", tradeRecords);
        return mapRsp;
    }


    /**
     * @Title: queryCLF
     * @Description: 查询接口
     * @author jiaguangyuan
     * @date 2019年9月26日09:23:21
     * @param begin_xh 检索数据，按xh升序排列，返回大于begin_xh的前count条数据
     * @param count    本次拉取数据条数
     * @param password 接口密码
     * @return map
     */
    //查询接口
    public Map<String, Object> queryCLF(Integer begin_xh, Integer count, String password) {
        List<TradeRecord> tradeRecords = null;
        try {
            //验证密码
            if (!portpassword.equals(password)){
                HashMap<String, Object> mapRsp = new HashMap<>();
                mapRsp.put(RESPONSE_SUCCESS, 0);
                mapRsp.put(RESPONSE_MESSAGE, "查询失败");
                mapRsp.put("data", tradeRecords);
                return mapRsp;
            }

            HashMap<Object, Object> map = new HashMap<>();
            map.put("begin_xh", begin_xh);
            map.put("count", count);
            map.put("password", password);
            //查出来count条 按begin_xh进行排列
            tradeRecords = tradeRecordDao.selectByParamterCLF(map);
        } catch (Exception e) {
            HashMap<String, Object> mapRsp = new HashMap<>();
            mapRsp.put(RESPONSE_SUCCESS, 0);
            mapRsp.put(RESPONSE_MESSAGE, "查询失败");
            mapRsp.put("data", tradeRecords);
            return mapRsp;
        }
        HashMap<String, Object> mapRsp = new HashMap<>();
        mapRsp.put(RESPONSE_SUCCESS, 1);
        mapRsp.put(RESPONSE_MESSAGE, "查询成功");
        mapRsp.put("data", tradeRecords);
        return mapRsp;
    }
}
