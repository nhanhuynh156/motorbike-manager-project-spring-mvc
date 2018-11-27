package com.nhan.dao;

import com.nhan.entity.Motorbike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MotorbikeRepository extends JpaRepository<Motorbike,Integer> {

    public List<Motorbike> findByAvailable(boolean available);

//    public List<Motorbike> findMotorbikeByPriceGreaterThanEqual(Long money);

//    public List<Motorbike> findAllByOrderByDate();

//    public Motorbike findTopByOrderByPriceDesc();
//
//    @Query(value = "select * from motorbike m where m.manu_id = ?1",nativeQuery = true)
//    public List<Motorbike> findMotorbikeByManufactory_Id(int id);

//    public List<Motorbike> findAllByOrderByPrice();

//    public List<Motorbike> findByPriceBetween(Long from, Long to);
//
//    public List<Motorbike> findByPriceLessThanAndAndManufactory_Id(Long money, int id);
}
