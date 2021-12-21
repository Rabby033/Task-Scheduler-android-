package com.codegama.Taskscheduler.activity;

public class model_class {

    private Integer total;
    private String username;

    public model_class()
    {

    }
    public model_class(Integer total, String username) {
        this.total = total;
        this.username = username;
    }
    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
