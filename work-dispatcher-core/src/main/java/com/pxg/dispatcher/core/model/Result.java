package com.pxg.dispatcher.core.model;

import lombok.Data;

@Data
public class Result extends WareHouse {

    public static int RUNNING = 1;
    public static int SUCCESS = 0;
    public static int FAILED = -1;


    private int status;

    private String code;

    private String message;


    public static Result buildSuccess() {
        Result result = new Result();
        result.setStatus(Result.SUCCESS);
        return result;
    }

    public static <T> Result buildSuccess(T data) {
        Result result = new Result();
        result.setStatus(Result.SUCCESS);
        result.writeData(data);
        return result;
    }

    public static Result buildFailure(String code, String message) {
        Result result = new Result();
        result.setStatus(Result.FAILED);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> Result build(String code, String message, T data) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.writeData(data);
        return result;
    }

}
