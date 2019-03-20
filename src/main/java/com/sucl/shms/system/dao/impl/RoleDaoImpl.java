package com.sucl.shms.system.dao.impl;

import com.sucl.shms.core.dao.impl.HibernateDao;
import com.sucl.shms.system.dao.RoleDao;
import com.sucl.shms.system.dao.UserDao;
import com.sucl.shms.system.entity.Role;
import com.sucl.shms.system.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author sucl
 * @since 2019/3/16
 */
@Repository
public class RoleDaoImpl extends HibernateDao<Role,String> implements RoleDao {
    @Override
    protected Class<Role> getModelClazz() {
        return Role.class;
    }
}
