package com.pxg.dispatcher.core.model;

import com.pxg.dispatcher.core.proxy.Delegate;
import org.springframework.util.StringUtils;

public class MigrantWorker extends BaseWorker {

    private String workerJarName;


    public void setWorkerJar(String workerJarName) {
        this.workerJarName = workerJarName;
    }

    @Override
    public String getWorkerJar() {
        return StringUtils.hasText(workerJarName) ? workerJarName : getClass().getName();
    }


    @Delegate
    @Override
    public boolean receive(WareHouse data) {
        return false;
    }

    @Delegate
    @Override
    public Result doWork(WareHouse task) {
        return null;
    }

    @Delegate
    @Override
    public void stopWork() {

    }

}
