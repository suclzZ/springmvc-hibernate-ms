package com.sucl.shms.system.web;

import com.sucl.shms.core.exception.BusException;
import com.sucl.shms.core.orm.Condition;
import com.sucl.shms.core.orm.Order;
import com.sucl.shms.core.orm.Pager;
import com.sucl.shms.core.orm.annotation.Page;
import com.sucl.shms.core.orm.annotation.QueryCondition;
import com.sucl.shms.core.orm.annotation.QueryOrder;
import com.sucl.shms.core.view.ZtreeNode;
import com.sucl.shms.system.entity.Role;
import com.sucl.shms.system.entity.User;
import com.sucl.shms.system.service.RoleService;
import com.sucl.shms.system.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collection;
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
    @Autowired
    private RoleService roleService;

    @ResponseBody
    @RequestMapping("/getUsers")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @ResponseBody
    @RequestMapping("/getUser")
    public User getUser(@RequestParam String id) throws BusException {
        return userService.getUser(id);
    }

    @ResponseBody
    @RequestMapping("/getUserAndRoles")
    public User getUserAndRoles(@RequestParam String id) throws BusException {
        return userService.getInitializeUser(id,new String[]{"roles"});
    }

    /**
     * 获取所有角色，和与当前用户关联的角色
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/getUserAndAllroles")
    public List<ZtreeNode> getUserAndAllroles(@RequestParam String id){
        User user = userService.getInitializeUser(id, new String[]{"roles"});
        List<Role> roles = roleService.getRoles();
        List<ZtreeNode> ztreeNodes = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(roles)){
            roles.stream().forEach(r->{
                ZtreeNode ztreeNode = new ZtreeNode(r.getRoleId(),r.getRoleCaption(),true);
                ztreeNode.setChecked(user.getRoleIds()!=null && user.getRoleIds().contains(r.getRoleId()));
                ztreeNodes.add(ztreeNode);
            });
        }
        return ztreeNodes;
    }

    @ResponseBody
    @RequestMapping("/getPageUser")
    public Pager<User> getPageUser(@Page Pager pager ,@QueryCondition(domain = User.class) Collection<Condition> conditions,@QueryOrder Collection<Order> orders){
        return userService.getPageUser(pager,conditions,orders);
    }

    @ResponseBody
    @RequestMapping("/saveUser")
    public User saveUser(User user){
        return userService.saveUser(user);
    }

    @ResponseBody
    @RequestMapping("removeUser")
    public void removeUser(@RequestParam String id){
        userService.removeUser(id);
    }
}
