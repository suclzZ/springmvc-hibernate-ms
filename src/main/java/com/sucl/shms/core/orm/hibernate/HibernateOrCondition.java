package com.sucl.shms.core.orm.hibernate;

import com.sucl.shms.core.orm.Condition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 * @author sucl
 * @since 2019/3/18
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HibernateOrCondition implements Condition {
    private HibernateCondition condition1;
    private HibernateCondition condition2;

    public Criterion generateExpression() {
        return Restrictions.or(convertToCriterion(this.condition1), convertToCriterion(this.condition1));
    }

    private Criterion convertToCriterion(Condition cond) {
        if ((cond instanceof HibernateCondition))
            return ((HibernateCondition)cond).generateExpression();
        if ((cond instanceof HibernateOrCondition)) {
            return ((HibernateOrCondition)cond).generateExpression();
        }
        return null;
    }

    @Override
    public String getProperty() {
        return null;
    }

    @Override
    public Operate getOperate() {
        return null;
    }

    @Override
    public Object getValue() {
        return null;
    }
}
