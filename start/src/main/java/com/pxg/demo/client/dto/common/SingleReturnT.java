package com.pxg.demo.client.dto.common;

public class SingleReturnT<T> extends ReturnInfo {

    private static final long serialVersionUID = 1L;
    private T data;

    public SingleReturnT() {
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static SingleReturnT buildSuccess() {
        SingleReturnT response = new SingleReturnT();
        response.setSuccess(true);
        return response;
    }

    public static SingleReturnT buildFailure(String errCode, String errMessage) {
        SingleReturnT response = new SingleReturnT();
        response.setSuccess(false);
        response.setErrCode(errCode);
        response.setErrMessage(errMessage);
        return response;
    }

    public static <T> SingleReturnT<T> of(T data) {
        SingleReturnT<T> response = new SingleReturnT<>();
        response.setSuccess(true);
        response.setData(data);
        return response;
    }

}
