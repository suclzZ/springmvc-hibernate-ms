package com.sucl.shms.system.web;

import com.sucl.shms.core.exception.BusException;
import com.sucl.shms.core.orm.Condition;
import com.sucl.shms.core.orm.Order;
import com.sucl.shms.core.orm.Pager;
import com.sucl.shms.core.orm.annotation.Page;
import com.sucl.shms.core.orm.annotation.QueryCondition;
import com.sucl.shms.core.orm.annotation.QueryOrder;
import com.sucl.shms.core.view.layui.TreeNode;
import com.sucl.shms.system.entity.Role;
import com.sucl.shms.system.service.RoleService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sucl
 * @since 2019/3/16
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @ResponseBody
    @RequestMapping("/getRoles")
    public List<Role> getRoles(){
        return roleService.getRoles();
    }

    @ResponseBody
    @RequestMapping("/getRole")
    public Role getRole(@RequestParam String id) throws BusException {
        return roleService.getRole(id);
    }

    @ResponseBody
    @RequestMapping("/getRoleTree")
    public List<TreeNode> getRoleTree() throws BusException {
        List<Role> roles = roleService.getRoles();
        if(CollectionUtils.isNotEmpty(roles)){
            return roles.stream().map(r->{
                return new TreeNode(r.getRoleCaption(),true,r);
            }).collect(Collectors.toList());
        }
        return Collections.EMPTY_LIST;
    }

    @ResponseBody
    @RequestMapping("/getPageRole")
    public Pager<Role> getPageRole(@Page Pager pager ,@QueryCondition(domain = Role.class) Collection<Condition> conditions,@QueryOrder Collection<Order> orders){
        return roleService.getPageRole(pager,conditions,orders);
    }

    @ResponseBody
    @RequestMapping("/saveRole")
    public Role saveRole(Role role){
        return roleService.saveRole(role);
    }

    @ResponseBody
    @RequestMapping("removeRole")
    public void removeRole(@RequestParam String id){
        roleService.removeRole(id);
    }
}
