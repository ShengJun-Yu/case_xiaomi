package com.jun.xiaomi.bean;

import java.util.Date;

public class Category {
    private Integer  gid;
    private String name;
    private Integer state;
    private Integer order_number;
    private String description;
    private Date create_time;

    public Category() {
    }

    public Category(Integer gid, String name, Integer state, Integer order_number, String description, Date create_time) {
        this.gid = gid;
        this.name = name;
        this.state = state;
        this.order_number = order_number;
        this.description = description;
        this.create_time = create_time;
    }

    public Category(String name, Integer state, Integer order_number, String description, Date create_time) {
        this.name = name;
        this.state = state;
        this.order_number = order_number;
        this.description = description;
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "Category{" +
                "gid=" + gid +
                ", name='" + name + '\'' +
                ", state=" + state +
                ", order_number=" + order_number +
                ", description='" + description + '\'' +
                ", create_time=" + create_time +
                '}';
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getOrder_number() {
        return order_number;
    }

    public void setOrder_number(Integer order_number) {
        this.order_number = order_number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
