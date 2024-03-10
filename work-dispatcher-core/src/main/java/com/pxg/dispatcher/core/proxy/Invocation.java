package com.pxg.dispatcher.core.proxy;

import com.pxg.dispatcher.core.common.ParkStatement;

public class Invocation implements ParkStatement {

    private String interfaceName;

    private String methodName;

    private Class[] parameterTypes;

    private Object[] parameterValues;

    public Invocation() {
    }

    public Invocation(String interfaceName, String methodName, Class[] parameterTypes, Object[] parameterValues) {
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
        this.parameterValues = parameterValues;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getParameterValues() {
        return parameterValues;
    }

    public void setParameterValues(Object[] parameterValues) {
        this.parameterValues = parameterValues;
    }
}
