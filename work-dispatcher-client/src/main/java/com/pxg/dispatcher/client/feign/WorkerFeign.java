package com.pxg.dispatcher.client.feign;

import com.pxg.dispatcher.core.entity.WorkerNode;
import com.pxg.dispatcher.core.service.WorkerServiceI;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface WorkerFeign extends WorkerServiceI {

    @PostMapping("/worker/register")
    String register(@RequestBody WorkerNode workerInfo);

    @GetMapping("/worker/cancel")
    boolean cancel(@RequestParam String workerId);

    @GetMapping("/worker/list")
    List<WorkerNode> getWorkers(@RequestParam String handlerCode);

}
