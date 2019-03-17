package com.sucl.shms.system.web;

import com.sucl.shms.system.entity.Agency;
import com.sucl.shms.system.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.*;
import java.util.List;

/**
 * @author sucl
 * @since 2019/3/16
 */
@Controller
@RequestMapping(value = "/agency",produces = MediaType.APPLICATION_JSON_VALUE)
public class AgencyController {
    @Autowired
    private AgencyService agencyService;

    @RequestMapping("/getAgencys")
    public List<Agency> getAgencys(){
        return agencyService.getAgencys();
    }
    
    @RequestMapping("/getAgency")
    public Agency getAgency(@RequestParam String id){
        return agencyService.getAgency(id);
    }

    @RequestMapping("/saveAgency")
    public Agency saveAgency(Agency agency){
        return agencyService.saveAgency(agency);
    }
    
    @RequestMapping("removeAgency")
    public void removeAgency(@RequestParam String id){
        agencyService.removeAgency(id);
    }
}
