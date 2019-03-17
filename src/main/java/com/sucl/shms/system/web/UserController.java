package com.sucl.shms.system.web;

import com.sucl.shms.system.entity.User;
import com.sucl.shms.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author sucl
 * @since 2019/3/16
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/getUsers")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @ResponseBody
    @RequestMapping("/getUser")
    public User getUser(@RequestParam String id){
        return userService.getUser(id);
    }

    @RequestMapping("/saveUser")
    public User saveUser(User user){
        return userService.saveUser(user);
    }

    @RequestMapping("removeUser")
    public void removeUser(@RequestParam String id){
        userService.removeUser(id);
    }
}
