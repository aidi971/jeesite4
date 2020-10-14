package com.jeesite.modules.response;

import lombok.Data;

/**
 * @Author: eddie
 * @Date: 2020/10/13 13:28
 **/
@Data
public class Response {


    private int status;
    private String msg;
    private Object str;

    public Response(int status, String msg, Object str) {
        this.status = status;
        this.msg = msg;
        this.str = str;
    }
}


