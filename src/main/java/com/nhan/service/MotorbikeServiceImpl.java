package com.nhan.service;

import com.nhan.dao.MotorbikeRepository;
import com.nhan.entity.Motorbike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotorbikeServiceImpl implements MotorbikeService{

    @Autowired
    MotorbikeRepository motorbikeRepository;

    @Override
    public List<Motorbike> getMotorbikeAvailable(boolean available) {
        return motorbikeRepository.findByAvailable(available);
    }

//    @Override
//    public List<Motorbike> getMotorbikeWithPriceGreaterThanEqual(Long money) {
//        return motorbikeRepository.findMotorbikeByPriceGreaterThanEqual(money);
//    }

//    @Override
//    public List<Motorbike> getMotorbikeOrderByDate() {
//        return motorbikeRepository.findAllByOrderByDate();
//    }

//    @Override
//    public List<Motorbike> getMotorbikeByManuId(int id) {
//        return motorbikeRepository.findMotorbikeByManufactory_Id(id);
//    }

//    @Override
//    public List<Motorbike> getMotorbikeOrderByPrice() {
//        return motorbikeRepository.findAllByOrderByPrice();
//    }

//    @Override
//    public List<Motorbike> getMotorbikeByPriceBetween(Long from, Long to) {
//        return motorbikeRepository.findByPriceBetween(from, to);
//    }
//
//    @Override
//    public List<Motorbike> getByPriceLessThanAndFactoryId(Long money, int id) {
//        return motorbikeRepository.findByPriceLessThanAndAndManufactory_Id(money,id);
//    }
//
//    @Override
//    public Motorbike getTopByOrderByPriceDesc() {
//        return motorbikeRepository.findTopByOrderByPriceDesc();
//    }

    @Override
    public Page<Motorbike> getMotorbikeLimitNumberRow(Pageable pageable) {
        return motorbikeRepository.findAll(pageable);
    }


    @Override
    public void deleteMotorbike(int id) {
        motorbikeRepository.delete(id);
    }

    @Override
    public Motorbike saveMotorbike(Motorbike motorbike) {
        return motorbikeRepository.save(motorbike);
    }
}

