package com.pxg.dispatcher.core.model;

public interface Workman {

    default String getWorkerJar() {
        return getClass().getName();
    }

    default String getWorkerType() {
        return getWorkerJar();
    }

    String getHost();

    int getPort();

}
