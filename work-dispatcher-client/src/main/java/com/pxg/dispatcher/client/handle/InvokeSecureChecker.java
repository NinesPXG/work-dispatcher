package com.pxg.dispatcher.client.handle;

import com.pxg.dispatcher.client.configuration.WorkerConfig;
import com.pxg.dispatcher.core.proxy.Invocation;
import lombok.SneakyThrows;

import java.util.List;

public class InvokeSecureChecker {

    private Class<?>[] whiteList;
    private boolean enable;

    public InvokeSecureChecker() {
    }

    public InvokeSecureChecker(WorkerConfig workerConfig) {
        bindSecurityConfig(workerConfig.getSecurity());
    }


    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Class<?>[] getWhiteList() {
        return whiteList;
    }

    public void setWhiteList(List<String> whiteList) throws Exception {
        int len = whiteList.size();
        this.whiteList = new Class[len];
        for (int i = 0; i < len; i++) {
            this.whiteList[i] = Class.forName(whiteList.get(i));
        }
    }

    @SneakyThrows
    public void bindSecurityConfig(WorkerConfig.SecurityConfig securityConfig) {
        setWhiteList(securityConfig.getWhiteList());
        setEnable(securityConfig.isEnable());
    }


    public boolean check(Invocation invocation) {
        if (!enable) {
            return true;
        }
        try {
            Class<?> arg = Class.forName(invocation.getInterfaceName());
            for (Class<?> tmp : whiteList) {
                if (tmp.isAssignableFrom(arg)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

}
