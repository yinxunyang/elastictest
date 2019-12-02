package com.bestvike.portal.mq;

import com.bestvike.datacenter.service.DatacenterService;
import com.bestvike.datacenter.support.Datacenter;
import com.bestvike.datacenter.util.DatacenterUtil;
import com.bestvike.portal.data.SysDept;
import com.bestvike.portal.service.ConfigService;

import com.bestvike.portal.util.GCC;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
//@RabbitListener(queues = "${app.mq.queues.data}")
public class Receiver {
    private Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private DatacenterService datacenterService;
    @Autowired
    private ConfigService configService;

    @Value("${app.instance.code}")
    private String appCode;

    @RabbitHandler
    public void process(Map<String, Object> messageMap) {

        try{

            if (messageMap != null) {

                String publishAppCode = (String) messageMap.get("appCode");
                if (publishAppCode == null || publishAppCode.equals(appCode)) {
                    return;
                }

                String dataType = (String) messageMap.get("dataType");
                String operate = (String) messageMap.get("operate");
                if (dataType != null && operate != null) {
                    Map<String, Object> dataMap = (Map<String, Object>) messageMap.get("data");
                    if (dataMap != null) {
                        Datacenter datacenter = null;
                        Map<String, Object> resultMap = null;
                        if (dataType.equals(GCC.DATACENTER_DATATYPE_CORPINFO)) {  //企业数据

                            String corpType = dataMap.get("corpType").toString();
                            datacenter = new SysDept().init(configService.initDeptId(corpType));
                            resultMap = datacenter.fromDatacenter(dataMap);

                        } else if (dataType.equals(GCC.DATACENTER_DATATYPE_PROJECTINFO)) { //项目数据

                        } else if (dataType.equals(GCC.DATACENTER_DATATYPE_BANKINFO)) { //银行数据

                            resultMap = new SysDept().fromBankDatacenter(dataMap);

                        }else {
                            return;
                        }

                        if (operate.equalsIgnoreCase(GCC.DATACENTER_OPERATETYPE_INSERT)) {
                            datacenterService.insertFromDatacenter(DatacenterUtil.initFromResult(resultMap));
                        } else if (operate.equalsIgnoreCase(GCC.DATACENTER_OPERATETYPE_UPDATE)) {
                            datacenterService.updateFromDatacenter(DatacenterUtil.initFromResult(resultMap));
                        } else if (operate.equalsIgnoreCase(GCC.DATACENTER_OPERATETYPE_DELETE)) {
                            datacenterService.deleteFromDatacenter(DatacenterUtil.initFromResult(resultMap));
                        }
                    }
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }


    }
}
