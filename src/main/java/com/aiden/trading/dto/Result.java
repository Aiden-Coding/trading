package com.aiden.trading.dto;

import com.aiden.trading.constant.ResultConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class Result<T> implements Serializable {

    private Integer code;
    private String message;
    private T result;

    public static  Result<?> ok() {
        return new Result<>(ResultConstant.CODE_SUCCESS, "ok", (Object) null);
    }

    public static Result<?> ok(String msg) {
        return new Result<>(ResultConstant.CODE_SUCCESS, msg, (Object) null);
    }

    public static Result<?> code(int code) {
        return new Result<>(code, (String) null, (Object) null);
    }

    public static <T> Result<T> data(T result) {
        return new Result<T>(ResultConstant.CODE_SUCCESS, "ok", result);
    }

    public static Result<?> error() {
        return new Result<>(ResultConstant.CODE_ERROR, "error", (Object) null);
    }

    public static Result<?> error(String msg) {
        return new Result<>(ResultConstant.CODE_ERROR, msg, (Object) null);
    }

    public static <T> Result<T> get(int code, String msg, T data) {
        return new Result<T>(code, msg, data);
    }

}
