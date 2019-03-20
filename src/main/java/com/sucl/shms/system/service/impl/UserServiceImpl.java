package com.sucl.shms.system.service.impl;

import com.sucl.shms.core.exception.BusException;
import com.sucl.shms.core.orm.Condition;
import com.sucl.shms.core.orm.Order;
import com.sucl.shms.core.orm.Pager;
import com.sucl.shms.core.service.impl.BaseServiceImpl;
import com.sucl.shms.system.dao.UserDao;
import com.sucl.shms.system.entity.Agency;
import com.sucl.shms.system.entity.User;
import com.sucl.shms.system.service.UserService;
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
public class UserServiceImpl extends BaseServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getUsers() {
        return userDao.getAll();
    }

    @Override
    public User getUser(String id) {
        return userDao.get(id);
    }

    @Override
    public User getInitializeUser(String id, String[] prps) {
        return userDao.getInitializeObject(id,prps);
    }

    @Override
    public Pager<User> getPageUser(Pager pager, Collection<Condition> conditions, Collection<Order> orders) {
        return userDao.getPager(pager,conditions,orders);
    }

    @Override
    public User saveUser(User user) {
        boolean update = StringUtils.isNotEmpty(user.getUserId());
        Agency agency = user.getAgency();
        if(agency!=null && StringUtils.isNotEmpty(agency.getAgencyId())){
            //进一步校验是否存在的机构
        }else{
            user.setAgency(null);
        }
        return userDao.save(user);
    }

    @Override
    public void removeUser(String id) {
        userDao.remove(id);
    }
}
