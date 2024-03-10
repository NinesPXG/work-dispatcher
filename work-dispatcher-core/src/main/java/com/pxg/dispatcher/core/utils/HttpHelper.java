package com.pxg.dispatcher.core.utils;

import lombok.SneakyThrows;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.BasicHttpClientResponseHandler;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.util.Map;
import java.util.stream.Collectors;


public class HttpHelper {

    private static final String SCHEMA_HTTP = "http://";

    private static final String SCHEMA_HTTPS = "https://";

    private static final HttpClientResponseHandler<String> DEFAULT_RESPONSE_HANDLER = new BasicHttpClientResponseHandler();

    private static HttpClient defaultHttpClient = HttpClientBuilder.create().build();


    public static String get(String url) {
        HttpGet httpGet = new HttpGet(ofUrl(url));
        return execute(httpGet);
    }

    public static String post(String url, Object body) {
        HttpPost httpPost = new HttpPost(ofUrl(url));
        if (body != null) {
            httpPost.setEntity(new StringEntity(JsonUtil.writeJson(body), ContentType.APPLICATION_JSON));
        }
        return execute(httpPost);
    }

    @SneakyThrows
    public static String execute(ClassicHttpRequest request) {
        return defaultHttpClient.execute(request, DEFAULT_RESPONSE_HANDLER);
    }


    private static String ofUrl(String url) {
        if (!url.startsWith(SCHEMA_HTTP) && !url.startsWith(SCHEMA_HTTPS)) {
            return SCHEMA_HTTP + url;
        }
        return url;
    }

    public static String resolveQueryString(Map<String, Object> params) {
        if (params == null || params.isEmpty()) {
            return "";
        }
        String s = params.entrySet()
                .stream()
                .map(e -> String.format("%s=%s", e.getKey(), e.getValue().toString()))
                .collect(Collectors.joining("&"));
        return "?" + s;
    }


    public static void setDefaultHttpClient(HttpClient defaultHttpClient) {
        HttpHelper.defaultHttpClient = defaultHttpClient;
    }

}
