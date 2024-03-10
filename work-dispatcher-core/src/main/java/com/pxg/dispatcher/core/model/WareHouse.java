package com.pxg.dispatcher.core.model;

import com.pxg.dispatcher.core.common.ParkStatement;
import com.pxg.dispatcher.core.utils.JsonUtil;
import lombok.Data;

@Data
public class WareHouse implements ParkStatement {

    private String content;


    public <T> void writeData(T data) {
        content = JsonUtil.writeJson(data);
    }

    public <T> T readData(Class<T> tClass) {
        return JsonUtil.readValue(content, tClass);
    }

}
