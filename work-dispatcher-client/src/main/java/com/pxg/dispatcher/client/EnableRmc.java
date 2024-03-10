package com.pxg.dispatcher.client;

import com.pxg.dispatcher.client.configuration.ComponentConfig;
import com.pxg.dispatcher.client.configuration.FeignConfig;
import com.pxg.dispatcher.client.configuration.WorkerConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({WorkerConfig.class, FeignConfig.class, ComponentConfig.class})
public @interface EnableRmc {

}
