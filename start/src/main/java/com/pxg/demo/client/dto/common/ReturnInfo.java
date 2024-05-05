package com.pxg.demo.client.dto.common;

public class ReturnInfo {

    private static final long serialVersionUID = 1L;

    private boolean success;
    private String errCode;
    private String errMessage;

    public ReturnInfo() {
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrCode() {
        return this.errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMessage() {
        return this.errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public static ReturnInfo buildSuccess() {
        ReturnInfo response = new ReturnInfo();
        response.setSuccess(true);
        return response;
    }

    public static ReturnInfo buildFailure(String errCode, String errMessage) {
        ReturnInfo response = new ReturnInfo();
        response.setSuccess(false);
        response.setErrCode(errCode);
        response.setErrMessage(errMessage);
        return response;
    }
}
