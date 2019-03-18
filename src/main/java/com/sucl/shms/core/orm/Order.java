package com.sucl.shms.core.orm;

import com.sucl.shms.system.entity.Menu;
import lombok.Getter;
import lombok.Setter;

/**
 * @author sucl
 * @since 2019/3/18
 */
public interface Order {
    String getProperty();
    Direction getDirection();

    enum Direction{
        ASC,DESC;
    }
}
