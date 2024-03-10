package com.pxg.dispatcher.test;

import cn.hutool.core.net.NetUtil;
import com.pxg.dispatcher.core.common.RemoteConst;
import com.pxg.dispatcher.core.entity.WorkNode;
import com.pxg.dispatcher.core.utils.HttpHelper;

public class Test {

    public static void main(String[] args) {
        HttpHelper.post("localhost:8060/test/reg", new WorkNode());
        WorkNode workNode = new WorkNode();
        workNode.setHost("192.168.1.2");
        workNode.setPort(8080);
        System.out.println(workNode.getUrl(RemoteConst.getHeartBeatPath()));
        System.out.println(NetUtil.getLocalhostStr());
        System.out.println(NetUtil.getLocalHostName());
        System.out.println(NetUtil.getLocalhost());
    }
}
