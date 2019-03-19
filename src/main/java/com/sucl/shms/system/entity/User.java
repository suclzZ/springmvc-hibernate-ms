package com.sucl.shms.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sucl.shms.core.orm.Domain;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * @author sucl
 * @since 2019/3/16
 */
@Data
@Entity
@Table(name = "USER")
//处理序列号异常
@JsonIgnoreProperties({"password","hibernateLazyInitializer", "handler"})
public class User implements Domain {

    @Id
    @Column(name = "USER_ID",length = 36)
    @GeneratedValue(generator = "system-id")
    @GenericGenerator(name = "system-id", strategy = "uuid")
    private String userId;

    @JoinColumn(name = "AGENCY_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Agency agency;

    @ManyToMany(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinTable (//被维护方可以不要此注解
            name = "USER_ROLE" , //关联表名
            joinColumns = @JoinColumn (name = "USER_ID" ),//维护方
            inverseJoinColumns = @JoinColumn (name = "ROLE_ID" ))//被维护方
    private Set<Role> roles;

    @Column(name = "LOGIN_NAME",length = 56)
    private String loginName;

    @Column(name = "USER_CAPTION",length = 16)
    private String userCaption;

    @Column(name = "PASSWORD",length = 24)
    private String password;

    @Column(name = "BIRTHDAY",length = 24)
    private String birthday;

    @Column(name = "TELEPHONE",length = 16)
    private String telephone;

    @Column(name = "EMAIL",length = 36)
    private String email;

    @Column(name = "QQ",length = 12)
    private String qq;

    @Column(name = "WEIXIN",length = 56)
    private String weixin;

    @Column(name = "DESCRIPTION",columnDefinition="varchar(256)")
    private String description;
}
