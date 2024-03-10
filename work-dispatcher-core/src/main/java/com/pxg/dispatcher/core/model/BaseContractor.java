package com.pxg.dispatcher.core.model;

import java.util.Collection;
import java.util.List;

public abstract class BaseContractor {

    private Collection<Worker> workers;

    private BaseContractor next;


    public Collection<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(Collection<Worker> workers) {
        this.workers = workers;
    }

    public BaseContractor getNext() {
        return next;
    }

    public BaseContractor toNext(BaseContractor contractor) {
        next = contractor;
        return next;
    }

    public abstract List<Result> giveTask(Collection<WareHouse> tasks, Collection<Worker> workers);

    public abstract Result doTask(WareHouse task);

}
