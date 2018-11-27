package com.nhan.service;

import com.nhan.entity.Motorbike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MotorbikeService {
    public List<Motorbike> getMotorbikeAvailable(boolean available);

//    public List<Motorbike> getMotorbikeWithPriceGreaterThanEqual(Long money);

//    public List<Motorbike> getMotorbikeOrderByDate();

//    public List<Motorbike> getMotorbikeByManuId(int id);

//    public List<Motorbike> getMotorbikeOrderByPrice();

//    public List<Motorbike> getMotorbikeByPriceBetween(Long from, Long to);
//
//    public List<Motorbike> getByPriceLessThanAndFactoryId(Long money, int id);
//
//    public Motorbike getTopByOrderByPriceDesc();

    public Page<Motorbike> getMotorbikeLimitNumberRow(Pageable pageable);

    public void deleteMotorbike(int id);

    public Motorbike saveMotorbike(Motorbike motorbike);
}
