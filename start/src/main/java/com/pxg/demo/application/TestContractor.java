package com.pxg.demo.application;

import com.pxg.demo.client.api.TermServiceI;
import com.pxg.demo.client.dto.request.TermSaveCmd;
import com.pxg.dispatcher.client.service.WorkerSupport;
import com.pxg.dispatcher.core.entity.WorkerNode;
import com.pxg.dispatcher.core.model.*;
import com.pxg.dispatcher.core.proxy.DelegateHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Slf4j
@Component
@RequiredArgsConstructor
public class TestContractor extends CompeteContractor {

    private final WorkerSupport workerSupport;

    public Result execute1(Integer n) {
        List<WorkerNode> workerNodes = workerSupport.getWorkers();
        List<Worker> workers = workerNodes.stream()
                .map(e -> DelegateHandler.bind(Worker.class, TestWorker.class.getName(), e))
                .collect(Collectors.toList());

        List<WareHouse> wareHouses = Stream.iterate(1, i -> i < n, i -> i + 1)
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

    public Result execute2(String s) {
        List<WorkerNode> workerNodes = workerSupport.getWorkers();
        List<Worker> workers = workerNodes.stream().map(e -> {
            MigrantWorker worker = new MigrantWorker();
            worker.bindWorkerNode(e);
            worker.setWorkerJar(TestWorker.class);
            return worker;
        }).collect(Collectors.toList());

        List<WareHouse> wareHouses = new ArrayList<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            WareHouse wareHouse = new WareHouse();
            wareHouse.writeData(String.valueOf(s.charAt(i)));
            wareHouses.add(wareHouse);
        }

        giveTask(wareHouses, workers);
        if (results.size() < wareHouses.size()) {
            log.error("task left, not completed");
        }
        results.forEach(e -> log.info(e.readData(String.class)));
        return Result.buildSuccess();
    }

    public void failSecure() {
        List<WorkerNode> workerNodes = workerSupport.getWorkers();
        Random random = new Random();
        WorkerNode node = workerNodes.get(random.nextInt(workerNodes.size()));
        TermServiceI termServiceI = DelegateHandler.bind(TermServiceI.class, TermServiceI.class.getName(), node);

        try {
            termServiceI.create(new TermSaveCmd());
        } catch (Exception e) {
            log.error("fail", e);
        }

    }
}
