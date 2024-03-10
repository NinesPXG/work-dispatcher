package com.pxg.demo.config;

import com.pxg.dispatcher.core.model.CompeteContractor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration

public class ThreadPoolConfig implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    private int coreSize = 10;
    private int maxSize = 20;
    private int idleTime = 2000;

    private ExecutorService executorService;

    @PostConstruct
    public void init() {
        executorService = new ThreadPoolExecutor(coreSize, maxSize, idleTime, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        applicationContext.getBeansOfType(CompeteContractor.class).forEach((key, val) -> val.setExecutorService(executorService));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ThreadPoolConfig.applicationContext = applicationContext;
    }
}
