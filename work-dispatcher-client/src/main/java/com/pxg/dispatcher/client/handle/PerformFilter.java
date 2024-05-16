package com.pxg.dispatcher.client.handle;

import com.pxg.dispatcher.client.configuration.WorkerConfig;
import com.pxg.dispatcher.core.common.RemoteConst;
import com.pxg.dispatcher.core.exception.BaseErrorCode;
import com.pxg.dispatcher.core.model.Result;
import com.pxg.dispatcher.core.proxy.Invocation;
import com.pxg.dispatcher.core.utils.JsonUtil;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

public class PerformFilter implements Filter {

    private final WorkerConfig workerConfig;
    private final PerformHandler performHandler;
    private final InvokeSecureChecker secureChecker;

    public PerformFilter(WorkerConfig workerConfig, PerformHandler performHandler) {
        this.workerConfig = workerConfig;
        this.performHandler = performHandler;
        this.secureChecker = new InvokeSecureChecker(workerConfig);
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        if (!isHeartBeat(httpRequest) && !isRemoteRun(httpRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (!isAuthorized(httpRequest)) {
            failAuth(httpResponse);
        } else {
            perform(httpRequest, httpResponse);
        }
    }

    private boolean isHeartBeat(HttpServletRequest request) {
        return RemoteConst.getHeartBeatPath().equals(request.getRequestURI());
    }

    private boolean isRemoteRun(HttpServletRequest request) {
        return RemoteConst.getRemoteWorkPath().equals(request.getRequestURI());
    }

    private boolean isAuthorized(HttpServletRequest request) {
        return Objects.equals(workerConfig.getHandlerCode(), request.getHeader(RemoteConst.RPC_SCRIP));
    }

    private void failAuth(HttpServletResponse response) {
        Result result = Result.buildFailure(BaseErrorCode.AUTH_EXCEPTION.getCode(), BaseErrorCode.AUTH_EXCEPTION.getMessage());
        writeResponse(response, result);
    }

    private void failSecurity(HttpServletResponse response) {
        Result result = Result.buildFailure(BaseErrorCode.SECURITY_FORBIDDEN.getCode(), BaseErrorCode.SECURITY_FORBIDDEN.getMessage());
        writeResponse(response, result);
    }

    private void perform(HttpServletRequest request, HttpServletResponse response) {
        if (isHeartBeat(request)) {
            writeResponse(response, performHandler.heartbeat());
            return;
        }
        Invocation invocation = readRequest(request, Invocation.class);
        if (secureChecker.check(invocation)) {
            writeResponse(response, performHandler.run(invocation));
        } else {
            failSecurity(response);
        }
    }

    @SneakyThrows
    private <T> T readRequest(HttpServletRequest request, Class<T> clazz) {
        BufferedReader reader = request.getReader();
        String body = IOUtils.toString(reader);
        return JsonUtil.readValue(body, clazz);
    }

    @SneakyThrows
    private void writeResponse(HttpServletResponse response, Object data) {
        response.getWriter().write(JsonUtil.writeJson(data));
    }

}
