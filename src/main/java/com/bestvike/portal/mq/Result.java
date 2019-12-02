package com.bestvike.portal.mq;

import com.bestvike.commons.util.EntityUtil;
import com.bestvike.datacenter.service.DatacenterService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.mapper.entity.EntityTable;
import org.mybatis.mapper.mapperhelper.EntityHelper;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
//@RabbitListener(queues = "${app.mq.queues.result}")
public class Result {
    private Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private DatacenterService datacenterService;

    @Value("${app.instance.code}")
    private String appCode;

    @RabbitHandler
    public void process(Map<String, Object> messageMap) {
        if (messageMap != null) {
            String publishAppCode = (String) messageMap.get("appCode");
            if (publishAppCode == null || !publishAppCode.equals(appCode)) {
                return;
            }
            String resultType = (String) messageMap.get("resultType");
            if (resultType != null) {
                EntityTable entityTable = EntityHelper.getEntityTable(resultType);
                if (entityTable != null) {
                    Map<String, Object> parameterMap = new HashMap<>();
                    parameterMap.put("tableName", entityTable.getName());
                    parameterMap.put("columns", EntityUtil.initColumnList(entityTable, (Map<String, Object>) messageMap.get("data"), "versionNumber, versionTime"));
                    parameterMap.put("keys", EntityUtil.initColumnList(entityTable, (Map<String, Object>) messageMap.get("data"), "datacenterId"));
                    datacenterService.updateVersion(parameterMap);
                }
            }
        }
    }
}
