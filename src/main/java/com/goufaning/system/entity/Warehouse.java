package com.goufaning.system.entity;

/**
 * Created by gfn on 2017-01-01.
 */
public class Warehouse {
    /** id*/
    private int id;

    /** 名字*/
    private String name;

    /** 地址 */
    private String address;

    /** 面积 */
    private double area;

    /**  描述 */
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
