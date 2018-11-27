package com.nhan.entity;


import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "motorbike")
public class Motorbike {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "motor_name")
    private String name;

    @Column(name = "available")
    private boolean available;

    @Column(name = "date_import")
    private Date date;

    @Column(name = "price")
    private Long price;

    @ManyToOne(fetch = FetchType.EAGER)
//            ,cascade = {CascadeType.DETACH,CascadeType.MERGE,
//            CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "manu_id")
    private Manufactory manufactory;

    public Motorbike() {
    }

    public Motorbike(String name, boolean available, Date date, Long price) {
        this.name = name;
        this.available = available;
        this.date = date;
        this.price = price;
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

    public Manufactory getManufactory() {
        return manufactory;
    }

    public void setManufactory(Manufactory manufactory) {
        this.manufactory = manufactory;
    }

    @Override
    public String toString() {
        return "Motorbike{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", available=" + available +
                ", date=" + date +
                ", price=" + price +
                ", manufactory=" + manufactory +
                '}';
    }
}
