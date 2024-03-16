package com.pxg.demo;

import cn.hutool.core.net.NetUtil;
import com.pxg.dispatcher.core.common.RemoteConst;
import com.pxg.dispatcher.core.entity.WorkerNode;
import com.pxg.dispatcher.core.utils.HttpHelper;

public class Test {

    public static void main(String[] args) {
        HttpHelper.post("localhost:8060/test/reg", new WorkerNode());
        WorkerNode workerNode = new WorkerNode();
        workerNode.setHost("192.168.1.2");
        workerNode.setPort(8080);
        System.out.println(workerNode.getUrl(RemoteConst.getHeartBeatPath()));
        System.out.println(NetUtil.getLocalhostStr());
        System.out.println(NetUtil.getLocalHostName());
        System.out.println(NetUtil.getLocalhost());
    }
}
