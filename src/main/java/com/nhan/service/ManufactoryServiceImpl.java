package com.nhan.service;

import com.nhan.dao.ManufactoryRepository;
import com.nhan.entity.Manufactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufactoryServiceImpl implements ManufactoryService {

    @Autowired
    ManufactoryRepository manufactoryRepository;


    @Override
    public List<Manufactory> getAllManufactory() {
        return manufactoryRepository.findAll();
    }

    @Override
    public List<Manufactory> getManufactoryByManufactoryName(String manufactoryName) {
        return manufactoryRepository.findByManufactoryName(manufactoryName);
    }

    @Override
    public Manufactory getManufactoryById(int manufactoryId) {
        return manufactoryRepository.findOne(manufactoryId);
    }

    @Override
    public void deleteManufactory(Manufactory manufactory) {
        manufactoryRepository.delete(manufactory);
    }
}
