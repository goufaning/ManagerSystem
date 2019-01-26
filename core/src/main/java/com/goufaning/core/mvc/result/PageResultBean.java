package com.goufaning.core.mvc.result;

public class PageResultBean<T> extends ResultBean<T> {

    private long total;

    private T rows;

    public PageResultBean() {
        super();
    }

    public PageResultBean(T rows, long total) {
        this();
        this.total = total;
        this.rows = rows;
    }

    public PageResultBean(Throwable e) {
        super(e);
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public T getRows() {
        return rows;
    }

    public void setRows(T rows) {
        this.rows = rows;
    }
}
