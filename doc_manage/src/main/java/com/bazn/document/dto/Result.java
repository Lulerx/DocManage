package com.bazn.document.dto;

/**
 * @author itle
 * @version 1.0
 * @date 2020/11/13
 */
public class Result<T> {
    private T Data;
    private Integer status;
    private String msg;

    public Result() {
    }

    public Result(T data, Integer status, String msg) {
        Data = data;
        this.status = status;
        this.msg = msg;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
