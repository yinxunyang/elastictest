package com.bestvike.portal.mq;

import com.bestvike.commons.util.StringUtil;
import com.bestvike.datacenter.support.Datacenter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Publisher {
    private Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${app.instance.code}")
    private String appCode;
    @Value("${app.mq.enable}")
    private Boolean enable;
    @Value("${app.mq.exchange:}")
    private String exchangeName;
    @Value("${app.mq.routes.data:}")
    private String routeName;
    @Value("${app.mq.routes.result:}")
    private String resultRoute;

    /*public Publisher(RabbitTemplate rabbitTemplate, Cache cache, ConfigService configService) {
        this.rabbitTemplate = rabbitTemplate;
        /// this.rabbitTemplate.setConfirmCallback(new CorpConfirmCallback(cache, configService));
    }*/

    public void send(String operate, Datacenter datacenter) {
        if (enable && datacenter != null) {
            CorrelationData correlationData = new CorrelationData(StringUtil.guid());
            // cache.set(correlationData.getId(), sysDept);
            rabbitTemplate.convertAndSend(exchangeName, routeName, datacenter.toDatacenter(operate, appCode, resultRoute), correlationData);
        }
    }

    /*public class CorpConfirmCallback implements RabbitTemplate.ConfirmCallback {
        private Cache cache;
        private ConfigService configService;
        //private SysDept sysDept;

        public CorpConfirmCallback(Cache cache, ConfigService configService) {
            this.cache = cache;
            this.configService = configService;
            //this.sysDept = sysDept;
        }
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            if (ack) {
                // 成功
                logger.info("success");
                SysDept sysDept = cache.get(correlationData.getId());
                configService.updateSysDeptVersion(sysDept);
                cache.del(correlationData.getId());
            } else {
                logger.info("error:" + cause);
            }
        }
    }*/
}
