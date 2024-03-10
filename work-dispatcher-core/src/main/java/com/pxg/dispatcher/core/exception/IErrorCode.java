package com.pxg.dispatcher.core.exception;

import java.text.MessageFormat;

public interface IErrorCode {

    default String getCode() {
        return toString();
    }

    String getMessage();

    default String getMessage(Object... args) {
        return MessageFormat.format(getMessage(), args);
    }
}
