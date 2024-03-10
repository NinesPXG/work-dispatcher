package com.pxg.demo.app;

import com.pxg.dispatcher.client.service.WorkerSupport;
import com.pxg.dispatcher.core.entity.WorkNode;
import com.pxg.dispatcher.core.model.CompeteContractor;
import com.pxg.dispatcher.core.model.Result;
import com.pxg.dispatcher.core.model.WareHouse;
import com.pxg.dispatcher.core.model.Worker;
import com.pxg.dispatcher.core.proxy.DelegateHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Slf4j
@Component
@RequiredArgsConstructor
public class TestContractor extends CompeteContractor {

    private final WorkerSupport workerSupport;

    public Result execute() {
        List<WorkNode> workNodes = workerSupport.getWorkers();
        List<Worker> workers = workNodes.stream()
                .map(e -> DelegateHandler.bind(Worker.class, TestWorker.class.getName(), e))
                .collect(Collectors.toList());

        setWorkers(workers);
        workerQueue = new LinkedBlockingQueue<>(workers);
        WareHouse tmp = new WareHouse();
        tmp.writeData("asd");
        Result result = doTask(tmp);

        List<WareHouse> wareHouses = Stream.iterate(1, i -> i < 100, i -> i + 1)
                .map(e -> {
                    WareHouse wareHouse = new WareHouse();
                    wareHouse.writeData(String.valueOf(e));
                    return wareHouse;
                }).collect(Collectors.toList());
        giveTask(wareHouses, workers);
        if (results.size() < wareHouses.size()) {
            log.error("task left, not completed");
        }
        results.forEach(e -> log.info(e.readData(String.class)));
        return Result.buildSuccess();
    }
}
