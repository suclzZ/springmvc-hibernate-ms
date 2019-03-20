package com.sucl.shms.system.service.impl;

import com.sucl.shms.core.orm.Condition;
import com.sucl.shms.core.orm.Order;
import com.sucl.shms.core.orm.Pager;
import com.sucl.shms.core.service.impl.BaseServiceImpl;
import com.sucl.shms.system.dao.RoleDao;
import com.sucl.shms.system.entity.Agency;
import com.sucl.shms.system.entity.Role;
import com.sucl.shms.system.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * @author sucl
 * @since 2019/3/16
 */
@Transactional
@Service
public class RoleServiceImpl extends BaseServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> getRoles() {
        return roleDao.getAll();
    }

    @Override
    public Role getRole(String id) {
        return roleDao.get(id);
    }

    @Override
    public Role getInitializeRole(String id, String[] prps) {
        return roleDao.getInitializeObject(id,prps);
    }

    @Override
    public Pager<Role> getPageRole(Pager pager, Collection<Condition> conditions, Collection<Order> orders) {
        return roleDao.getPager(pager,conditions,orders);
    }

    @Override
    public Role saveRole(Role role) {
        boolean update = StringUtils.isNotEmpty(role.getRoleId());
        return roleDao.save(role);
    }

    @Override
    public void removeRole(String id) {
        roleDao.remove(id);
    }
}
