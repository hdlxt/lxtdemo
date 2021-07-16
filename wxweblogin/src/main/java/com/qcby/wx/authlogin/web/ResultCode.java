package com.qcby.wx.authlogin.web;

/**
 * @ClassName ResultCode
 * @Description TODO
 * @Author lxt
 * @Date 2021/7/16 11:08
 */
public enum ResultCode {
    /*
    请求返回状态码和说明信息
     */
    SUCCESS(200, "成功"),

    BAD_REQUEST(400, "参数或者语法不对"),
    UNAUTHORIZED(401, "认证失败"),
    LOGIN_ERROR(402, "登录失败，用户名或密码无效"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "请求的资源不存在"),
    OPERATE_ERROR(405, "操作失败，请求操作的资源不存在"),
    TIME_OUT(408, "请求超时"),

    SERVER_ERROR(500, "服务器内部错误"),

    GENERATOR_FAILURE(501,"生成失败"),

    CHILD_DATA_EXIST(20001, "数据中包含子数据"),
    ROLE_LOAD_RESOURCES(20002, "资源已经使用，请在角色管理中解绑后，在操作"),
    DATA_EXIST(20003, "数据已经存在"),
    NOT_UPDATE(20004, "数据没有更新"),
    REPEAT_SUBMIT(20005, "数据提交重复，请勿重复操作"),
;
    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
