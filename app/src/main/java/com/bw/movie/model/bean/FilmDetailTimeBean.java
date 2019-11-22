package com.bw.movie.model.bean;

import java.util.List;

public class FilmDetailTimeBean {
    /**
     * result : ["08-22","08-23","08-24","08-25","08-26","08-27","08-28"]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<String> result;

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

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }
}
