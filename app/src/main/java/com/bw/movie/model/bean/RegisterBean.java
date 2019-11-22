package com.bw.movie.model.bean;

public class RegisterBean {
    /**
     * message : 注册失败,邮箱已存在
     * status : 1005
     */

    private String message;
    private String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
