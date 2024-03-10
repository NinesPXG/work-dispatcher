package com.pxg.dispatcher.core.model;

public interface Worker extends Workman {

    boolean receive(WareHouse data);

    Result doWork(WareHouse task);

    void stopWork();
}
