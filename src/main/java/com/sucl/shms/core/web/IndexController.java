package com.sucl.shms.core.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author sucl
 * @since 2019/3/16
 */
@Controller
@RequestMapping
public class IndexController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/logout")
    public void logout(){

    }

    @RequestMapping(value = "/page/{page}")
    public String pageRoute(@PathVariable("page") String page){
        return page;
    }
}
