package com.pxg.dispatcher.client.configuration;

import com.pxg.dispatcher.client.handle.PerformAuthInterceptor;
import com.pxg.dispatcher.core.utils.HttpHelper;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import javax.annotation.PostConstruct;


public class RemoteCallConfig {

    private final WorkerConfig workerConfig;

    public RemoteCallConfig(WorkerConfig workerConfig) {
        this.workerConfig = workerConfig;
    }

    @PostConstruct
    public void init() {
        String code = workerConfig.getHandlerCode();
        HttpClient client = HttpClients.custom()
                .addRequestInterceptorFirst(new PerformAuthInterceptor(code))
                .build();
        HttpHelper.setDefaultHttpClient(client);

    }
}
