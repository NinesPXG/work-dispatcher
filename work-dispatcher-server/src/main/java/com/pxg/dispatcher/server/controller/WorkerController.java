package com.pxg.dispatcher.server.controller;

import com.pxg.dispatcher.core.entity.WorkerNode;
import com.pxg.dispatcher.core.service.WorkerServiceI;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("worker")
@RequiredArgsConstructor
public class WorkerController {

    private final WorkerServiceI workerService;

    @PostMapping("register")
    public String register(@RequestBody WorkerNode workerNode) {
        return workerService.register(workerNode);
    }

    @GetMapping("cancel")
    public boolean cancel(@RequestParam String workerId) {
        return workerService.cancel(workerId);
    }

    @GetMapping("list")
    public List<WorkerNode> getWorkers(@RequestParam String handlerCode) {
        return workerService.getWorkers(handlerCode);
    }

}
