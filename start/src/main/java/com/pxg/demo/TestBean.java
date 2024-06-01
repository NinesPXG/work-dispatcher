package com.pxg.demo;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

@Slf4j
public class TestBean {

    @PostConstruct
    public void start() {
        log.info("testBean started in application");
    }
}
