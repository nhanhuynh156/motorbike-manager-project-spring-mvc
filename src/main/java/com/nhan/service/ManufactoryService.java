package com.nhan.service;

import com.nhan.entity.Manufactory;

import java.util.List;

public interface ManufactoryService {

    public List<Manufactory> getAllManufactory();

    public List<Manufactory> getManufactoryByManufactoryName(String manufactoryName);

    public Manufactory getManufactoryById(int manufactoryId);

    public void deleteManufactory(Manufactory manufactory);
}
