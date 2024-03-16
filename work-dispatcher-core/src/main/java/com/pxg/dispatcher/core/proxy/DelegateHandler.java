package com.pxg.dispatcher.core.proxy;

import com.pxg.dispatcher.core.common.RemoteConst;
import com.pxg.dispatcher.core.entity.Address;
import com.pxg.dispatcher.core.utils.HttpHelper;
import com.pxg.dispatcher.core.utils.JsonUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DelegateHandler implements InvocationHandler {

    private final String jar;
    private final Address address;

    private DelegateHandler(String jar, Address address) {
        this.jar = jar;
        this.address = address;
    }

    public static Object bind(Class[] clazz, String jar, Address address) {
        return Proxy.newProxyInstance(clazz[0].getClassLoader(), clazz, new DelegateHandler(jar, address));
    }

    public static <T> T bind(Class<T> clazz, String jar, Address address) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new DelegateHandler(jar, address));
    }

    /**
     * if worker without ip or is local, continue
     * else send request to remote
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Invocation invocation = new Invocation(jar, method.getName(), method.getParameterTypes(), args);
        String result = HttpHelper.post(address.getUrl(RemoteConst.getRemoteWorkPath()), invocation);
        return JsonUtil.readValue(result, method.getReturnType());
    }
}
