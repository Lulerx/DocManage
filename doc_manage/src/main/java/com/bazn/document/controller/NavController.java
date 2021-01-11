package com.bazn.document.controller;

import com.bazn.document.bean.Navigation;
import com.bazn.document.dto.Result;
import com.bazn.document.service.NavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author itle
 * @version 1.0
 * @date 2020/11/26
 */
@RestController
@RequestMapping("/nav")
public class NavController {

    @Autowired
    private NavService navService;


    @GetMapping("/getAllNavs")
    public Result getNavs(){
        Result result = new Result();
        List<Navigation> allNavList = navService.getAllNav();
        result.setData(allNavList);
        return result;
    }

}
