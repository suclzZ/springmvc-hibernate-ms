package com.sucl.shms.system.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author sucl
 * @since 2019/3/16
 */
@Data
@Entity
@Table(name = "MENU")
public class Menu {

    @Id
    @Column(name = "MENU_ID",length = 36)
    @GeneratedValue(generator = "system-id")
    @GenericGenerator(name = "system-id", strategy = "uuid")
    private String menuId;

    @Column(name = "MENU_CODE",length = 12)
    private String menuCode;

    @Column(name = "MENU_CAPTION",length = 12)
    private String menuCaption;

    @Column(name = "PARENT_MENU_CODE",length = 12)
    private String parentMenuCode;

    @Column(name = "PATH",length = 64)
    private String path;

    @Column(name = "STYLE",length = 56)
    private String style;

    @Column(name = "LEAF",length = 2)
    private boolean leaf;

}
