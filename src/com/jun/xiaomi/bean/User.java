package com.jun.xiaomi.bean;

import java.util.Date;

/**
 * user表对应的实体类
 * 无参构造
 * get set方法
 * toString方法
 */

public class User {
    private Integer id; //主键
    private String name;//姓名
    private Integer sex;//性别  1 男  0 女
    private String phone_number;//手机号
    private String area;//地区
    private Integer manager;//权限  1 普通用户 0 管理员
    private String username;//用户名
    private String password;//密码
    private String photo;//头像
    private Date create_time;//创建时间

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getManager() {
        return manager;
    }

    public void setManager(Integer manager) {
        this.manager = manager;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", phone_number='" + phone_number + '\'' +
                ", area='" + area + '\'' +
                ", manager=" + manager +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", photo='" + photo + '\'' +
                ", create_time=" + create_time +
                '}';
    }
}
