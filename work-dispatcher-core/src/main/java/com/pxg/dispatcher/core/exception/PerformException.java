package com.pxg.dispatcher.core.exception;

public class PerformException extends RuntimeException {

    private String errorCode;

    public PerformException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode.getCode();
    }

    public PerformException(IErrorCode errorCode, Object... args) {
        super(errorCode.getMessage(args));
        this.errorCode = errorCode.getCode();
    }

    public PerformException(IErrorCode errorCode, Throwable e) {
        super(errorCode.getMessage(), e);
        this.errorCode = errorCode.getCode();
    }

    public PerformException(String errorCode, String errMessage, Throwable e) {
        super(errMessage, e);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
