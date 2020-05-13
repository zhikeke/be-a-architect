package com.ke.aboutspringboot.controller;

import com.ke.aboutspringboot.service.PriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @Autowired
    private PriorityService priorityService;

    @RequestMapping("/priority/{name}/get")
    @ResponseBody
    public String getPriorityByName(@PathVariable("name") String name) {
        return priorityService.getPriority(name);
    }
}
