package com.pxg.demo.application;

import com.pxg.dispatcher.core.model.BaseWorker;
import com.pxg.dispatcher.core.model.Result;
import com.pxg.dispatcher.core.model.WareHouse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestWorker extends BaseWorker {

    @Value("${sleep}")
    private long sleep;

    @Override
    public boolean receive(WareHouse data) {
        return false;
    }

    @SneakyThrows
    @Override
    public Result doWork(WareHouse task) {
        Thread.sleep(sleep);
        String data = task.readData(String.class);
        log.info("[read data] {}", data);
        return Result.buildSuccess(palindrome(data));
    }

    @Override
    public void stopWork() {

    }

    private String palindrome(String data) {
        StringBuilder sb = new StringBuilder();
        sb.append(data);
        for (int i = data.length() - 1; i >= 0; i--) {
            sb.append(data.charAt(i));
        }
        return sb.toString();
    }
}
