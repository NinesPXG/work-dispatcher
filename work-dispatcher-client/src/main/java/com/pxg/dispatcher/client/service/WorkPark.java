package com.pxg.dispatcher.client.service;

import cn.hutool.core.net.NetUtil;
import com.pxg.dispatcher.client.configuration.WorkerConfig;
import com.pxg.dispatcher.core.entity.WorkerNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class WorkPark implements ApplicationRunner, DisposableBean {

    private static final ScheduledExecutorService REGISTER = Executors.newSingleThreadScheduledExecutor();

    private static WorkerNode local;

    private final WorkerSupport workerSupport;

    private final WorkerConfig workerConfig;

    public WorkPark(WorkerConfig workerConfig, WorkerSupport workerSupport) {
        this.workerConfig = workerConfig;
        this.workerSupport = workerSupport;
    }

    private static void initLocalWorker(WorkerConfig workerConfig) {
        local = new WorkerNode();
        local.setHandlerCode(workerConfig.getHandlerCode());
        local.setCluster("");
        local.setHost(NetUtil.getLocalhostStr());
        local.setPort(workerConfig.getPort());
        local.setCreatedTime(LocalDateTime.now());
    }

    public static WorkerNode getLocalWorker() {
        return local;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initLocalWorker(workerConfig);
        Runnable runnable = () -> {
            try {
                local.setLastTime(System.currentTimeMillis());
                String workerId = workerSupport.register(local);
                local.setWorkerId(workerId);
            } catch (Exception e) {
                log.error("register worker failed", e);
            }
        };
        REGISTER.scheduleAtFixedRate(runnable, workerConfig.getInitDelay(), workerConfig.getHeartbeatTime(), TimeUnit.SECONDS);
    }

    @Override
    public void destroy() throws Exception {
        workerSupport.cancel(local.getWorkerId());
    }

}
