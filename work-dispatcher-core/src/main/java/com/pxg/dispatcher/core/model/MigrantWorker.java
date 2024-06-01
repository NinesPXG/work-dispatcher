package com.pxg.dispatcher.core.model;

import com.pxg.dispatcher.core.common.RemoteConst;
import com.pxg.dispatcher.core.proxy.Invocation;
import com.pxg.dispatcher.core.utils.HttpHelper;
import com.pxg.dispatcher.core.utils.JsonUtil;
import org.springframework.util.StringUtils;

public class MigrantWorker extends BaseWorker {

    private String workerJarName;


    public void setWorkerJar(Class<?> clazz) {
        setWorkerJar(clazz.getName());
    }

    public void setWorkerJar(String workerJarName) {
        this.workerJarName = workerJarName;
    }

    @Override
    public String getWorkerJar() {
        return StringUtils.hasText(workerJarName) ? workerJarName : getClass().getName();
    }


    @Override
    public boolean receive(WareHouse data) {
        Invocation invocation = new Invocation(getWorkerJar(), "receive", new Class[]{WareHouse.class}, new Object[]{data});
        String result = HttpHelper.post(getUrl(RemoteConst.getRemoteWorkPath()), invocation);
        return JsonUtil.readValue(result, Boolean.class);
    }

    @Override
    public Result doWork(WareHouse task) {
        Invocation invocation = new Invocation(getWorkerJar(), "doWork", new Class[]{WareHouse.class}, new Object[]{task});
        String result = HttpHelper.post(getUrl(RemoteConst.getRemoteWorkPath()), invocation);
        return JsonUtil.readValue(result, Result.class);
    }

    @Override
    public void stopWork() {
        Invocation invocation = new Invocation(getWorkerJar(), "stopWork", new Class[]{}, new Object[]{});
        HttpHelper.post(getUrl(RemoteConst.getRemoteWorkPath()), invocation);
    }

    private String getUrl(String path) {
        return String.format("%s:%d%s", getHost(), getPort(), path);
    }

}
