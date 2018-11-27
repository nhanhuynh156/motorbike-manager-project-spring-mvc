package com.nhan.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="manufactory")
public class Manufactory {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Integer id;

    @Column(name = "manufactory_name")
    private String manufactoryName;

    @JsonIgnore
    @OneToMany(mappedBy = "manufactory")
//            ,
//    cascade = {CascadeType.DETACH, CascadeType.MERGE,
//    CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Motorbike> motorbikes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getManufactoryName() {
        return manufactoryName;
    }

    public void setManufactoryName(String manufactoryName) {
        this.manufactoryName = manufactoryName;
    }

    public List<Motorbike> getMotorbikes() {
        return motorbikes;
    }

    public void setMotorbikes(List<Motorbike> motorbikes) {
        this.motorbikes = motorbikes;
    }

    @Override
    public String toString() {
        return "Manufactory{" +
                "id=" + id +
                ", manufactoryName='" + manufactoryName + '\'' +
                '}';
    }
}
