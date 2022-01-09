package com.lxt.demo.oss.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ResultJson {
    private final static int  OK = 200;
    private final static int  ERR = 500;

    private int code;
    private String msg;
    private Object data;

    private ResultJson(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResultJson ok(String msg, Object data){
        return new ResultJson(OK,msg,data);
    }
    public static ResultJson ok(Object data){
        return ok(null,data);
    }
    public static ResultJson ok(){
        return ok(null,null);
    }

    public static ResultJson fail(String msg,Object data){
        return new ResultJson(ERR,msg,data);
    }
    public static ResultJson fail(Object data){
        return ok(null,data);
    }
    public static ResultJson fail(){
        return ok(null,null);
    }

}
