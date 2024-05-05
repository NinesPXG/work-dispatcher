package com.pxg.demo.client.dto.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MultiReturnT<T> extends ReturnInfo {

    private static final long serialVersionUID = 1L;
    private List<T> data;

    public MultiReturnT() {
    }

    public List<T> getData() {
        if (null == this.data) {
            return Collections.emptyList();
        } else {
            return data;
        }
    }

    public void setData(Collection<T> data) {
        if (data == null) {
            return;
        }
        this.data = new ArrayList<>(data);
    }

    public boolean isEmpty() {
        return this.data == null || this.data.isEmpty();
    }

    public boolean isNotEmpty() {
        return !this.isEmpty();
    }

    public static MultiReturnT buildSuccess() {
        MultiReturnT response = new MultiReturnT();
        response.setSuccess(true);
        return response;
    }

    public static MultiReturnT buildFailure(String errCode, String errMessage) {
        MultiReturnT response = new MultiReturnT();
        response.setSuccess(false);
        response.setErrCode(errCode);
        response.setErrMessage(errMessage);
        return response;
    }

    public static <T> MultiReturnT<T> of(Collection<T> data) {
        MultiReturnT<T> response = new MultiReturnT<>();
        response.setSuccess(true);
        response.setData(data);
        return response;
    }

    public static <E, T> MultiReturnT<T> of(Collection<E> data, Function<E, T> convert) {
        MultiReturnT<T> response = new MultiReturnT<>();
        response.setSuccess(true);
        if (data != null) {
            response.setData(data.stream().map(convert).collect(Collectors.toList()));
        }
        return response;
    }
}
