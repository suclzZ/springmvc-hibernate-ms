package com.sucl.shms.system.service;

import com.sucl.shms.core.service.BaseService;
import com.sucl.shms.system.entity.User;

import java.util.List;

/**
 * @author sucl
 * @since 2019/3/16
 */
public interface UserService extends BaseService {

    List<User> getUsers();

    User getUser(String id);

    User saveUser(User user);

    void removeUser(String id);
}
