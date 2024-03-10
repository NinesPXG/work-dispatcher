package com.pxg.dispatcher.client.configuration;

import com.pxg.dispatcher.client.handle.PerformFilter;
import com.pxg.dispatcher.client.handle.PerformHandler;
import com.pxg.dispatcher.client.service.WorkPark;
import com.pxg.dispatcher.client.service.WorkerSupport;
import com.pxg.dispatcher.client.service.WorkerSupportImpl;
import com.pxg.dispatcher.core.service.WorkerServiceI;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

public class ComponentConfig {

    @Bean
    @ConditionalOnMissingBean({WorkerSupport.class})
    public WorkerSupport workerSupport(WorkerServiceI workerServiceI, WorkerConfig workerConfig) {
        return new WorkerSupportImpl(workerServiceI, workerConfig);
    }

    @Bean
    @ConditionalOnMissingBean({WorkPark.class})
    public WorkPark workPark(WorkerConfig workerConfig, WorkerSupport workerSupport) {
        return new WorkPark(workerConfig, workerSupport);
    }


    @Bean
    @ConditionalOnMissingBean({PerformHandler.class})
    public PerformHandler performHandler(ApplicationContext context) {
        return new PerformHandler(context);
    }

    @Bean
    @ConditionalOnBean({PerformHandler.class})
    public FilterRegistrationBean<PerformFilter> performFilter(WorkerConfig workerConfig, PerformHandler performHandler) {
        FilterRegistrationBean<PerformFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new PerformFilter(workerConfig, performHandler));
        registration.addUrlPatterns("/*");
        return registration;
    }

    @Bean("RemoteCallConfig")
    @ConditionalOnMissingBean(name = "RemoteCallConfig")
    public RemoteCallConfig remoteCallConfig(WorkerConfig workerConfig) {
        return new RemoteCallConfig(workerConfig);
    }

}
