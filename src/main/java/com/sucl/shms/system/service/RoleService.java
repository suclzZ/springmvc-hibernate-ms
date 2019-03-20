package com.sucl.shms.system.service;

import com.sucl.shms.core.orm.Condition;
import com.sucl.shms.core.orm.Order;
import com.sucl.shms.core.orm.Pager;
import com.sucl.shms.core.service.BaseService;
import com.sucl.shms.system.entity.Role;

import java.util.Collection;
import java.util.List;

/**
 * @author sucl
 * @since 2019/3/16
 */
public interface RoleService extends BaseService {

    List<Role> getRoles();

    Role getRole(String id);

    Role getInitializeRole(String id, String[] prps);

    Pager<Role> getPageRole(Pager pager, Collection<Condition> conditions, Collection<Order> orders);

    Role saveRole(Role role);

    void removeRole(String id);
}
