package com.pxg.dispatcher.core.entity;

import com.pxg.dispatcher.core.common.RemoteConst;
import com.pxg.dispatcher.core.utils.HttpHelper;
import com.pxg.dispatcher.core.utils.JsonUtil;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
public class WorkerNode extends Address {

    private String workerId;

    private String handlerCode;

    private String cluster;

    private long lastTime;

    private LocalDateTime createdTime;

    public boolean isAvailable() {
        return isAvailable(RemoteConst.getHeartbeatTimeout());
    }

    public boolean isAvailable(int timeout) {
        try {
            String response = HttpHelper.get(getUrl(RemoteConst.getHeartBeatPath()), timeout);
            return JsonUtil.readValue(response, Boolean.class);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        WorkerNode that = (WorkerNode) o;
        return Objects.equals(handlerCode, that.handlerCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), handlerCode);
    }
}
