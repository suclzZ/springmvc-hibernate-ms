package com.sucl.shms.system.dao.impl;

import com.sucl.shms.core.dao.impl.HibernateDao;
import com.sucl.shms.system.dao.AgencyDao;
import com.sucl.shms.system.entity.Agency;
import org.springframework.stereotype.Repository;

/**
 * @author sucl
 * @since 2019/3/16
 */
@Repository
public class AgencyDaoImpl extends HibernateDao<Agency,String> implements AgencyDao {
    @Override
    protected Class<Agency> getModelClazz() {
        return Agency.class;
    }
}
