package com.pxg.dispatcher.client.handle;

import com.pxg.dispatcher.core.exception.BaseErrorCode;
import com.pxg.dispatcher.core.exception.PerformException;
import com.pxg.dispatcher.core.model.Result;
import com.pxg.dispatcher.core.proxy.Invocation;
import com.pxg.dispatcher.core.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class PerformHandler {

    private final ApplicationContext applicationContext;

    public PerformHandler(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public boolean heartbeat() {
        return true;
    }

    public Object run(Invocation invocation) {
        try {
            Class<?> clazz = Class.forName(invocation.getInterfaceName());
            Method method = clazz.getMethod(invocation.getMethodName(), invocation.getParameterTypes());
            Object target = applicationContext.getBean(clazz);

            Class<?>[] types = invocation.getParameterTypes();
            Object[] values = invocation.getParameterValues();
            for (int i = 0; i < types.length; i++) {
                values[i] = JsonUtil.convertValue(values[i], types[i]);
            }

            return method.invoke(target, invocation.getParameterValues());
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | BeansException e) {
            log.error("target not found!", e);
            return Result.buildFailure(BaseErrorCode.TARGET_EXCEPTION.getCode(), BaseErrorCode.TARGET_EXCEPTION.getMessage());
        } catch (IllegalAccessException | IllegalArgumentException e) {
            log.error("invoke failed!", e);
            return Result.buildFailure(BaseErrorCode.INVOKE_EXCEPTION.getCode(), BaseErrorCode.INVOKE_EXCEPTION.getMessage());
        } catch (InvocationTargetException e) {
            if (e.getTargetException() instanceof PerformException) {
                PerformException exception = (PerformException) e.getTargetException();
                return Result.buildFailure(exception.getErrorCode(), exception.getMessage());
            } else {
                log.error("undefined exception!", e);
                return Result.buildFailure(BaseErrorCode.UNDEFINED_EXCEPTION.getCode(), BaseErrorCode.UNDEFINED_EXCEPTION.getMessage());
            }
        }
    }
}
