package com.basic.bigdata.page;

/**
 * Created by dell-pc on 2016/4/24.
 */
import java.io.Serializable;
import java.util.List;

/**
 * 数据分页类
 *
 * @author GST
 * @version 1.0, 2006-12-30
 */

public class Pagination<T> implements Serializable {

    private static final long serialVersionUID = -5884976706259160221L;
    /**
     * 上一页
     */
    private long preIndex;
    /**
     * 当前页
     */
    private long curIndex;
    /**
     * 下一页
     */
    private long nextIndex;
    /**
     * 每页条数
     */
    private long pageSize;
    /**
     * 总条数
     */
    private long rowsCount;

    public void setPreIndex(long preIndex) {
        this.preIndex = preIndex;
    }

    public void setCurIndex(long curIndex) {
        this.curIndex = curIndex;
    }

    public void setNextIndex(long nextIndex) {
        this.nextIndex = nextIndex;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 总页数
     */
    private long pagesCount;
    /**
     * 对象列表
     */
    private List<T> items;

    /**
     *
     * 分页类构建函数
     *
     */
    public Pagination() {
        updateInfo(0, 0, 0);
    }

    /**
     *
     * 分页类构建函数
     *
     * @param pageIndex
     *            当前页码
     * @param pageSize
     *            每页记录数
     */
    public Pagination(long pageIndex, long pageSize) {
        updateInfo(pageIndex, pageSize, 0);
    }

    /**
     * 分页类构建函数
     *
     * @param pageIndex
     *            当前页码
     * @param pageSize
     *            每页记录数
     * @param rowsCount
     *            记录总数
     */
    public Pagination(long pageIndex, long pageSize, long rowsCount) {
        updateInfo(pageIndex, pageSize, rowsCount);
    }

    /**
     * 获取当前面记录
     *
     * @return
     */
    public List<T> getItems() {
        return items;
    }

    /**
     * 设置当前页记录
     *
     * @param items
     */
    public void setItems(List<T> items) {
        this.items = items;
    }

    /**
     * 获取当前页码
     *
     * @return
     */
    public long getCurIndex() {
        return curIndex;
    }

    /**
     * 获取下一页码
     *
     * @return
     */
    public long getNextIndex() {
        return nextIndex;
    }

    /**
     * 获取总页数
     *
     * @return
     */
    public long getPagesCount() {
        return pagesCount;
    }

    /**
     * 获取每页记录数
     *
     * @return
     */
    public long getPageSize() {
        return pageSize;
    }

    /**
     * 获取上一页码
     *
     * @return
     */
    public long getPreIndex() {
        return preIndex;
    }

    /**
     * 获取总记录数
     *
     * @return
     */
    public long getRowsCount() {
        return rowsCount;
    }

    /**
     * 获取首页码
     *
     * @return
     */
    public long getFirstIndex() {
        return 1;
    }

    /**
     * 获取末页码
     *
     * @return
     */
    public long getLastIndex() {
        return pagesCount;
    }

    private void updateInfo(long pageIndex, long pageSize, long rowsCount) {

        if (pageSize > 0) {

            this.curIndex = pageIndex;
            this.rowsCount = rowsCount;
            this.pageSize = pageSize;

            // 确定页数
            pagesCount = (rowsCount + pageSize - 1) / pageSize;
            // 确定当前页码
            if (curIndex <= 0)
                curIndex = 1;
            if (curIndex > pagesCount)
                curIndex = pagesCount;
            // 确定下一页码
            nextIndex = curIndex + 1;
            if (nextIndex > pagesCount)
                nextIndex = pagesCount;
            // 确定上一页码
            preIndex = curIndex - 1;
            if (preIndex <= 0)
                preIndex = 1;
        } else {
            this.preIndex = 1;
            this.curIndex = 1;
            this.nextIndex = 1;
            this.pageSize = 0;
            this.pagesCount = 1;
        }
    }

    /**
     * 设置总记录数
     *
     * @param rowsCount
     */
    public void setRowsCount(long rowsCount) {
        updateInfo(curIndex, pageSize, rowsCount);
    }

    /**
     * 设置总页数
     *
     * @param pagesCount
     */
    public void setPagesCount(long pagesCount) {
        this.pagesCount = pagesCount;
    }

}
