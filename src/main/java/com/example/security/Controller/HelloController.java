package com.example.security.Controller;

import com.example.security.Service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
    @Autowired
    private SecurityService securityService;

    @RequestMapping("/admin/hello")
    public String adminHello() {
        return "hello";
    }

    @RequestMapping("/user/hello")
    public String userHello() {
        //String string1 = securityService.admin();
        String string2 = securityService.admin();
        return string2;
    }


    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
}
