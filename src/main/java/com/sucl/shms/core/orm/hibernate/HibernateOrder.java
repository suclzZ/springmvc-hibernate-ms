package com.sucl.shms.core.orm.hibernate;

import com.sucl.shms.core.orm.Order;
import lombok.Data;

/**
 * 排序相关
 * @author sucl
 * @since 2019/3/18
 */
@Data
public class HibernateOrder extends org.hibernate.criterion.Order implements Order {
    private String property;
    private Direction direction;

    public HibernateOrder(String propertyName, Direction direction) {
        super(propertyName, direction == Direction.ASC);
        this.property = property;
        this.direction = direction;
    }
}
