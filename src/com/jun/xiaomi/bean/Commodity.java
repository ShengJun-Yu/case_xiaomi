package com.jun.xiaomi.bean;

import java.util.Date;

/**
 * 商品表
 */
public class Commodity {
    private Integer cid;// 主键
    private Integer gid;// 所属商品分类的主键
    private String name;//名称
    private String color;//颜色
    private String size; //型号
    private Double price;//价格
    private String description;//描述
    private String full_description;//详细描述
    private String pic;//图片
    private Integer state;// 0 正常  1 热门产品 2 明星单品
    private String version;//版本
    private Date product_date;//生产日期

    //用来存储对应的商品分类信息
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Commodity() {
    }

    public Commodity(Integer gid, String name, String color, String size, Double price, String description, String full_description, Integer state, String version, Date product_date) {
        this.gid = gid;
        this.name = name;
        this.color = color;
        this.size = size;
        this.price = price;
        this.description = description;
        this.full_description = full_description;
        this.state = state;
        this.version = version;
        this.product_date = product_date;

    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFull_description() {
        return full_description;
    }

    public void setFull_description(String full_description) {
        this.full_description = full_description;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getProduct_date() {
        return product_date;
    }

    public void setProduct_date(Date product_date) {
        this.product_date = product_date;
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "cid=" + cid +
                ", gid=" + gid +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", full_description='" + full_description + '\'' +
                ", pic='" + pic + '\'' +
                ", state=" + state +
                ", version='" + version + '\'' +
                ", product_date=" + product_date +
                ", category=" + category +
                '}';
    }
}
