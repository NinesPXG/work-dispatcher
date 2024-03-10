package com.pxg.dispatcher.core.model;

import com.pxg.dispatcher.core.exception.BaseErrorCode;
import lombok.SneakyThrows;

import java.util.Collection;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class CompeteContractor extends BaseContractor {

    protected ExecutorService executorService;

    protected BlockingQueue<WareHouse> taskQueue;
    protected BlockingQueue<Worker> workerQueue;
    protected AtomicInteger taskTotal;
    protected AtomicInteger workerCount;

    protected List<Result> results;


    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }


    @SneakyThrows
    private void doTask(WareHouse task, Worker worker) {
        try {
            Result result = worker.doWork(task);
            results.add(result);
            workerQueue.put(worker);
            taskTotal.getAndDecrement();
        } catch (Exception e) {
            taskQueue.put(task);
            workerCount.getAndDecrement();
        }
    }

    @SneakyThrows
    private void giveTask() {
        if (executorService == null) {
            while (!taskQueue.isEmpty() && !workerQueue.isEmpty()) {
                WareHouse task = taskQueue.poll();
                Worker worker = workerQueue.poll();
                CompletableFuture.runAsync(() -> doTask(task, worker));
            }
        } else {
            while (!taskQueue.isEmpty() && !workerQueue.isEmpty()) {
                WareHouse task = taskQueue.poll();
                Worker worker = workerQueue.poll();
                CompletableFuture.runAsync(() -> doTask(task, worker), executorService);
            }
        }
    }

    @Override
    public List<Result> giveTask(Collection<WareHouse> tasks, Collection<Worker> workers) {
        taskQueue = new LinkedBlockingQueue<>(tasks);
        workerQueue = new LinkedBlockingQueue<>(workers);
        taskTotal = new AtomicInteger(tasks.size());
        workerCount = new AtomicInteger(workers.size());
        results = new Vector<>(tasks.size());
        while (taskTotal.get() > 0 && workerCount.get() > 0) {
            giveTask();
        }
        return results;
    }

    @Override
    public Result doTask(WareHouse task) {
        if (workerQueue.isEmpty()) {
            Result result = Result.buildFailure(BaseErrorCode.UNREACHABLE.getCode(), BaseErrorCode.UNREACHABLE.getMessage());
            result.setContent(task.getContent());
            return result;
        }
        Worker worker = workerQueue.poll();
        try {
            return worker.doWork(task);
        } catch (Exception e) {
            return doTask(task);
        }
    }

}
