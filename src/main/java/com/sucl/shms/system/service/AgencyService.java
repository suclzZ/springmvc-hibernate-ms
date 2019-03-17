package com.sucl.shms.system.service;

import com.sucl.shms.core.service.BaseService;
import com.sucl.shms.system.entity.Agency;

import java.util.List;

/**
 * @author sucl
 * @since 2019/3/16
 */
public interface AgencyService extends BaseService {

    List<Agency> getAgencys();

    Agency getAgency(String id);

    Agency saveAgency(Agency agency);

    void removeAgency(String id);
}
