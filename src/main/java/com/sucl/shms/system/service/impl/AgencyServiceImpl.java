package com.sucl.shms.system.service.impl;

import com.sucl.shms.core.service.impl.BaseServiceImpl;
import com.sucl.shms.system.dao.AgencyDao;
import com.sucl.shms.system.entity.Agency;
import com.sucl.shms.system.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author sucl
 * @since 2019/3/16
 */
@Transactional
@Service
public class AgencyServiceImpl extends BaseServiceImpl implements AgencyService {

    @Autowired
    private AgencyDao agencyDao;

    @Override
    public List<Agency> getAgencys() {
        return agencyDao.getAll();
    }

    @Override
    public Agency getAgency(String id) {
        return agencyDao.get(id);
    }

    @Override
    public Agency saveAgency(Agency agency) {
        return agencyDao.save(agency);
    }

    @Override
    public void removeAgency(String id) {
        agencyDao.remove(id);
    }
}
