package com.nhan.dao;

import com.nhan.entity.Manufactory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManufactoryRepository extends JpaRepository<Manufactory,Integer> {
    public List<Manufactory> findByManufactoryName(String manufactoryName);
}
