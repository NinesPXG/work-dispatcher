package com.pxg.dispatcher.core.exception;

public enum BaseErrorCode implements IErrorCode {

    UNREACHABLE("no resources to complete"),
    AUTH_EXCEPTION("auth fail, may send to incorrect application!"),
    TARGET_EXCEPTION("target not found!"),
    INVOKE_EXCEPTION("invoke meet exception!"),
    BUSINESS_EXCEPTION("{0}"),
    UNDEFINED_EXCEPTION("meet unexpected exception while running");

    private final String errorMsg;

    BaseErrorCode(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }
}
