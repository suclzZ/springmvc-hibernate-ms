package com.sucl.shms.system.entity;

import com.sucl.shms.core.orm.Domain;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author sucl
 * @since 2019/3/16
 */
@Data
@Entity
@Table(name = "AGENCY")
public class Agency implements Domain {

    @Id
    @Column(name = "AGENCY_ID",length = 36)
    @GeneratedValue(generator = "system-id")
    @GenericGenerator(name = "system-id", strategy = "uuid")
    private String agencyId;

    @Column(name = "AGENCY_CODE",length = 24)
    private String agencyCode;

    @Column(name = "AGENCY_NAME",length = 56)
    private String agencyName;

    @Column(name = "DUTY",length = 36)
    private String duty;

    @Column(name = "DESCRIPTION",length = 256)
    private String description;

}
