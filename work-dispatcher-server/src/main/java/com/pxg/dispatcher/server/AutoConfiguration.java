package com.pxg.dispatcher.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.pxg.dispatcher.server")
@MapperScan(basePackages = "com.pxg.dispatcher.server.infra.repository.mapper")
public class AutoConfiguration {

}
