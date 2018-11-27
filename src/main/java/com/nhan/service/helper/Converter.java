package com.nhan.service.helper;

import com.nhan.entity.Motorbike;
import com.nhan.model.MotorbikeVO;

public class Converter {
    public static Motorbike convertMotorbikeVOToMotorbike(MotorbikeVO motorbikeVO){
        Motorbike motorbike = new Motorbike();
        motorbike.setName(motorbikeVO.getName());
        motorbike.setAvailable(motorbikeVO.isAvailable());
        motorbike.setDate(motorbikeVO.getDate());
        motorbike.setPrice(motorbikeVO.getPrice());
        return motorbike;
    }
}
