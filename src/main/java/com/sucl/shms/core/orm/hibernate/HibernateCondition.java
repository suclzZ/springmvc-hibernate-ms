package com.sucl.shms.core.orm.hibernate;

import com.sucl.shms.core.orm.Condition;
import com.sucl.shms.core.orm.Order;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 * @author sucl
 * @since 2019/3/18
 */
public class HibernateCondition implements Condition {
    protected String property;
    protected Operate operate;
    protected Object value;

    public HibernateCondition(String property, Object value){
        this(property,Operate.EQ,value);
    }

    public HibernateCondition(String property, Operate operate, Object value){
        this.property = property;
        this.operate = operate;
        this.value = value;
    }

    public Criterion generateExpression(String alias){
        if (this.value != null) {
            if (Operate.EQ == operate)
                return Restrictions.eq(this.property, this.value);
            if (Operate.NE == operate)
                return Restrictions.ne(this.property, this.value);
            if (Operate.LIKE == operate)
                return Restrictions.like(this.property, this.value.toString(), MatchMode.ANYWHERE);
            if (Operate.LLIKE == operate)
                return Restrictions.like(this.property, this.value.toString(), MatchMode.END);
            if (Operate.RLIKE == operate)
                return Restrictions.like(this.property, this.value.toString(), MatchMode.START);
            if (Operate.BTW == operate) {
                String[] betweenArray = this.value.toString().split("-BTW-");
                if (betweenArray.length < 2) return null;
                return generateBetween(betweenArray[0], betweenArray[1]);
            }if (Operate.IN == operate) {
                if ((this.value instanceof Object[]))
                    return Restrictions.in(this.property, (Object[])this.value);
            }else if (Operate.NOT_IN == operate) {
                if ((this.value instanceof Object[]))
                    return Restrictions.not(Restrictions.in(this.property, (Object[])this.value));
            } else {
                if (Operate.LT == operate)
                    return Restrictions.lt(this.property, this.value);
                if (Operate.GT == operate)
                    return Restrictions.gt(this.property, this.value);
                if (Operate.LE == operate)
                    return Restrictions.le(this.property, this.value);
                if (Operate.GE == operate) {
                    return Restrictions.ge(this.property, this.value);
                }
            }
        }
        if (Operate.IS_NULL == operate)
            return Restrictions.isNull(this.property);
        if (Operate.NOT_NULL == operate) {
            return Restrictions.isNotNull(this.property);
        }
        return null;
    }

    public Criterion generateExpression() {
        return generateExpression(null);
    }

    private Criterion generateBetween(String begin, String end) {
        return Restrictions.between(this.property, begin, end);
    }

    @Override
    public String getProperty() {
        return this.property;
    }

    @Override
    public Operate getOperate() {
        return this.operate;
    }

    @Override
    public Object getValue() {
        return this.value;
    }
}
