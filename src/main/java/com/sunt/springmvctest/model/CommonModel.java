package com.sunt.springmvctest.model;

import com.sunt.springmvctest.util.ConstantUtil;

public class CommonModel {
    private int code;
    private String msg;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private Object data;

    public void setSuccess(){
        setCode(ConstantUtil.CODE_SUCCESS);
        setMsg(ConstantUtil.MSG_SUCCESS);
    }

    public void setFail(){
        setCode(ConstantUtil.CODE_FAIL);
        setMsg(ConstantUtil.MSG_FAIL);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
