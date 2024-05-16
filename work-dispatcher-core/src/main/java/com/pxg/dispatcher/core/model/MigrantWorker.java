package com.pxg.dispatcher.core.model;

import org.springframework.util.StringUtils;

public class MigrantWorker extends BaseWorker {

    private String workerJarName;


    public void setWorkerJar(Class<?> clazz) {
        setWorkerJar(clazz.getName());
    }

    public void setWorkerJar(String workerJarName) {
        this.workerJarName = workerJarName;
    }

    @Override
    public String getWorkerJar() {
        return StringUtils.hasText(workerJarName) ? workerJarName : getClass().getName();
    }


    @Override
    public boolean receive(WareHouse data) {
        return false;
    }

    @Override
    public Result doWork(WareHouse task) {
        return null;
    }

    @Override
    public void stopWork() {

    }

}
