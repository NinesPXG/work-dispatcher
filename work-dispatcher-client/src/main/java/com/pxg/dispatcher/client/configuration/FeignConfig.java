package com.pxg.dispatcher.client.configuration;

import com.pxg.dispatcher.client.feign.WorkerFeign;
import com.pxg.dispatcher.core.service.WorkerServiceI;
import feign.Contract;
import feign.Feign;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;


@ConditionalOnMissingClass(value = {"com.pxg.dispatcher.server.service.impl.WorkerServiceImpl"})
@Import({FeignClientsConfiguration.class})
public class FeignConfig {

    @Bean
    @ConditionalOnMissingBean(value = {WorkerServiceI.class})
    public WorkerFeign workerFeign(Decoder decoder, Encoder encoder, Contract contract, WorkerConfig workerConfig) {
        return Feign.builder()
                .decoder(decoder)
                .encoder(encoder)
                .contract(contract)
                .target(WorkerFeign.class, workerConfig.getCenterUri());
    }
}
