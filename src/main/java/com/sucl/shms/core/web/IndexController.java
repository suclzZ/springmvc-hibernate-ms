package com.sucl.shms.core.web;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.activation.MimeType;

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
    public String logout(){
        return "login";
    }

    /**
     * 前端页面路由
     * @param page
     * @return
     */
    @RequestMapping(value = "/page/{page}",produces = {MediaType.TEXT_HTML_VALUE})
    public String pageRoute(@PathVariable("page") String page, Model model){
        model.addAttribute("user","admin");
        return page;
    }
}
