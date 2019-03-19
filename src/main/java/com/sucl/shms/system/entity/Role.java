package com.sucl.shms.system.entity;

import com.sucl.shms.core.orm.Domain;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * @author sucl
 * @since 2019/3/16
 */
@Data
@Entity
@Table(name = "ROLE")
public class Role implements Domain {

    @Id
    @Column(name = "ROLE_ID",length = 24)
    private String roleId;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable (//被维护方可以不要此注解
            name = "ROLE_MENU" , //关联表名
            joinColumns = @JoinColumn (name = "ROLE_ID" ),//维护方
            inverseJoinColumns = @JoinColumn (name = "MENU_ID" ))//被维护方
    private Set<Menu> menus;

    @Column(name = "ROLE_CAPTION",length = 56)
    private String roleCaption;

    @Column(name = "DESCRIPTION",columnDefinition="varchar(256)")
    private String description;
}
