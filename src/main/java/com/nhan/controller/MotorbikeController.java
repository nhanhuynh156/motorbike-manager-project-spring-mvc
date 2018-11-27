package com.nhan.controller;

import com.nhan.entity.Manufactory;
import com.nhan.entity.Motorbike;
import com.nhan.model.MotorbikeVO;
import com.nhan.service.ManufactoryService;
import com.nhan.service.MotorbikeService;
import com.nhan.service.helper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/motorbike")
public class MotorbikeController {
    @Autowired
    MotorbikeService motorbikeService;

    @Autowired
    ManufactoryService manufactoryService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String showMotorbike(Model model){
        List<Manufactory> manufactories = manufactoryService.getAllManufactory();

        Page<Motorbike> motorbikePage = motorbikeService.getMotorbikeLimitNumberRow(new PageRequest(0,3));

        model.addAttribute("motorbikePage", motorbikePage);
        model.addAttribute("manufactories", manufactories);

        return "motorbike";
    }


    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Page<Motorbike> goToPage(@RequestParam(value = "strRequestPageNumber", required = false) String strRequestPageNumber,
                                    @RequestParam(value = "strCurrentPageNumber", required = false) String strCurrentPageNumber){
        System.out.println(strRequestPageNumber);
        System.out.println(strCurrentPageNumber);
        Page<Motorbike> motorbikePage;
        strRequestPageNumber = strRequestPageNumber.replace("page-","").trim();
        strCurrentPageNumber = strCurrentPageNumber.replace("page-","").trim();
        System.out.println(strRequestPageNumber);
        System.out.println(strCurrentPageNumber);
        int currentPageNumber = Integer.valueOf(strCurrentPageNumber);
        if(strRequestPageNumber.equals("previous")){
            System.out.printf("previous1");
            motorbikePage = motorbikeService.getMotorbikeLimitNumberRow(new PageRequest(currentPageNumber-2,3));
        }else if (strRequestPageNumber.equals("next")){
            motorbikePage = motorbikeService.getMotorbikeLimitNumberRow(new PageRequest(currentPageNumber, 3));
        }else {
            int requestPageNumber = Integer.valueOf(strRequestPageNumber.replace("page-",""));
            motorbikePage = motorbikeService.getMotorbikeLimitNumberRow(new PageRequest(requestPageNumber-1,3));
        }
        return motorbikePage;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Motorbike addMotor(@RequestBody MotorbikeVO motorbikeVO){
        System.out.println("motorbikeVO" + motorbikeVO);
        Motorbike motorbike = Converter.convertMotorbikeVOToMotorbike(motorbikeVO);
        Manufactory manufactory = manufactoryService.getManufactoryById(motorbikeVO.getManuId());
        motorbike.setManufactory(manufactory);
        Motorbike motorbikeComeBack = motorbikeService.saveMotorbike(motorbike);
        return motorbikeComeBack;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteMotor(@RequestBody Map<String, String> id){
        motorbikeService.deleteMotorbike(Integer.parseInt(id.get("id")));
    }
}
