package com.nhan.model;


import java.sql.Date;

public class MotorbikeVO {
    private int id;

    private String name;

    private boolean available;

    private Date date;

    private Long price;

    private int manuId;

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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public int getManuId() {
        return manuId;
    }

    public void setManuId(int manuId) {
        this.manuId = manuId;
    }

    @Override
    public String toString() {
        return "MotorbikeVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", available=" + available +
                ", date=" + date +
                ", price=" + price +
                ", manuId=" + manuId +
                '}';
    }
}
