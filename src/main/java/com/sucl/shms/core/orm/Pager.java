package com.sucl.shms.core.orm;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *  i*s ~ (i+1)*s-1
 *
 * @author sucl
 * @since 2019/3/18
 */
@Data
@NoArgsConstructor
public class Pager<T> {
    private int code = 200;//前端必须 没办法

    private static int DEFALUT_PAGEINDEX = 1;
    public static int DEFALUT_PAGESIZE = 15;

    public static final int QUERY_TYPE_ALL = 0;
    public static final int QUERY_TYPE_LIST = 1;
    public static final int QUERY_TYPE_COUNT = 2;

    private int pageType;

    private int pageIndex;
    private int pageSize;
    private int pageStart;
    private int pageEnd;
    private int maxPage;

    private int totleCount;
    private List<T> records;

    public Pager(String pageSize, String pageIndex, String pageType) {
        try {
            this.pageSize = new Integer(pageSize).intValue();
        } catch (NumberFormatException e) {
            this.pageSize = DEFALUT_PAGESIZE;
        }
        try {
            this.pageIndex = new Integer(pageIndex).intValue();
        } catch (NumberFormatException e) {
            this.pageIndex = DEFALUT_PAGEINDEX;
        }
        try {
            this.pageType = new Integer(pageType).intValue();
        } catch (NumberFormatException e) {
            this.pageType = 0;
        }
        initPager();
    }

    public Pager(int pageSize, int pageIndex, int pageType) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.pageType = pageType;
        initPager();
    }

    private void initPager() {
        this.pageIndex = this.pageIndex < 1 ? 1 : this.pageIndex;
        this.pageStart = (this.pageIndex - 1) * this.pageSize;
        this.pageEnd = this.pageIndex*this.pageSize - 1;
    }

    public void setTotleCount(int totleCount) {
        this.totleCount = totleCount;
        int maxPage = getMaxPage();
        if ((maxPage > 0) && (this.pageIndex > maxPage)) {
            this.pageIndex = maxPage;
        }
        initPager();
    }

    public int getMaxPage() {
        double maxPage = Math.ceil(new Double(this.totleCount).doubleValue() / this.pageSize);
        return Double.valueOf(maxPage).intValue();
    }
}
