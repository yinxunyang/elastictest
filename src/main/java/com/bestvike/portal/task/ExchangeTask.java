package com.bestvike.portal.task;

import com.bestvike.commons.rest.EurekaRest;
import com.bestvike.commons.support.Timestamp;
import com.bestvike.datacenter.service.DatacenterService;
import com.bestvike.datacenter.support.Datacenter;
import com.bestvike.datacenter.util.DatacenterUtil;
import com.bestvike.portal.data.SysDept;
import com.bestvike.portal.service.TaskService;
import org.apache.ibatis.utils.PaginationList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

@Service
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class ExchangeTask {
    private Date date = null;

    @Autowired
    private TaskService taskService;
    @Autowired
    private EurekaRest eurekaRest;
    @Autowired
    private DatacenterService datacenterService;

    //@Scheduled(cron = "${app.task.exchange}")
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void executeExchangeCorp() throws Exception {
        if (date == null) {
            date = taskService.selectTimestamp();
        }
        Timestamp timestamp = new Timestamp();
        timestamp.setDataType("corpInfo");
        timestamp.setOffset(0);
        timestamp.setLimit(10);
        timestamp.setDate(date);
        PaginationList<Map<String, Object>> resultList = eurekaRest.parse(eurekaRest.post("datacenter", "/pub/exchange/pageByTimestamp", timestamp, PaginationList.class));
        if (resultList != null && resultList.getData() != null && resultList.getData().size() > 0) {
            for (Map<String, Object> map : resultList.getData()) {
                String operate = (String) map.get("operateType");
                if (operate != null) {
                    Datacenter datacenter = new SysDept();
                    Map<String, Object> resultMap = datacenter.fromDatacenter(map);
                    Map<String, Object> versionMap = (Map<String, Object>) resultMap.get("versionMap");
                    Date versionTime = (Date) versionMap.get("versionTime");
                    Integer datacenterVersion = (Integer) versionMap.get("versionNumber");
                    Integer currentVersion = datacenterService.selectVersion(versionMap);
                    if (currentVersion != null && datacenterVersion != null && currentVersion < datacenterVersion) {
                        if (operate.equalsIgnoreCase("insert")) {
                            datacenterService.insertFromDatacenter(DatacenterUtil.initFromResult(resultMap));
                        } else if (operate.equalsIgnoreCase("update")) {
                            datacenterService.updateFromDatacenter(DatacenterUtil.initFromResult(resultMap));
                        } else if (operate.equalsIgnoreCase("delete")) {
                            datacenterService.deleteFromDatacenter(DatacenterUtil.initFromResult(resultMap));
                        }
                    }
                    if (versionTime != null) {
                        date = versionTime;
                    }
                }
            }
            // 更新时间戳
            taskService.updateTimestamp(date);
        }
    }
}
