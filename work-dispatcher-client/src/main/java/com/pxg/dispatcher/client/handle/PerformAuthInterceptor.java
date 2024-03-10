package com.pxg.dispatcher.client.handle;

import com.pxg.dispatcher.core.common.RemoteConst;
import org.apache.hc.core5.http.EntityDetails;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.http.HttpRequestInterceptor;
import org.apache.hc.core5.http.protocol.HttpContext;

import java.io.IOException;

public class PerformAuthInterceptor implements HttpRequestInterceptor {

    private String code;

    public PerformAuthInterceptor() {
    }

    public PerformAuthInterceptor(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public void process(HttpRequest httpRequest, EntityDetails entityDetails, HttpContext httpContext) throws HttpException, IOException {
        httpRequest.setHeader(RemoteConst.RPC_SCRIP, code);
    }

}
