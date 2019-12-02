package com.bestvike.portal.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by jun on 2018/8/18.
 */
@Service
@Scope("singleton")
public class ThreadPoolFactory {
    public Log log = LogFactory.getLog(getClass());
    private ThreadPoolTaskExecutor poolTaskExecutor;
    @PostConstruct
    public void init() {
        log.info("-------初始化线程池------");
        taskExecutor();
    }

    public ThreadPoolTaskExecutor taskExecutor() {
        if(poolTaskExecutor==null){
            poolTaskExecutor = new ThreadPoolTaskExecutor();
            //线程池维护线程的最少数量
            poolTaskExecutor.setCorePoolSize(10);
            //线程池维护线程的最大数量
            poolTaskExecutor.setMaxPoolSize(100);
            //线程池所使用的缓冲队列
            poolTaskExecutor.setQueueCapacity(5000);
            //等待线程执行完成
            poolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
            //线程池维护线程所允许的空闲时间
            poolTaskExecutor.setKeepAliveSeconds(30000);
            //线程池对拒绝任务的处理
            poolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
            poolTaskExecutor.initialize();
        }
        return poolTaskExecutor;

    }
}
