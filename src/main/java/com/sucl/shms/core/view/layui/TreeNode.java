package com.sucl.shms.core.view.layui;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author sucl
 * @since 2019/3/20
 */
@Data
@NoArgsConstructor
public class TreeNode {

    private String name;
    private boolean spread = false;
    private String href;
    private List<TreeNode> children;
//    private Map<String,String> extension;
    private Object extension;

    public TreeNode(String name,Object extension ){
        this(name,false,extension);
    }

    public TreeNode(String name,boolean spread,Object extension ){
        this.name = name;
        this.spread = spread;
        this.extension = extension;
    }

}
