package com.sucl.shms.core.view;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sucl
 * @since 2019/3/20
 */
@Data
@NoArgsConstructor
public class ZtreeNode {

    private String id ;
    private String pid ;
    private String name ;
    private ZtreeNode children;
    private boolean open;
    private boolean checked = false;

    public ZtreeNode(String id,String name,boolean open){
        this(id,null,name,open);
    }

    public ZtreeNode(String id, String pid ,String name,boolean open){
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.open = open;
    }

}
