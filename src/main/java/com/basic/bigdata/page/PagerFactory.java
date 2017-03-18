package com.basic.bigdata.page;

/**
 * Created by dell-pc on 2016/4/24.
 */

import java.util.ArrayList;
import java.util.List;

public class PagerFactory {
    public static <T> Pagination<T> createEmpty() {
        return create(1, 10);
    }

    public static <T> Pagination<T> create(Number pageIndex, Number pageSize) {
        return create(pageIndex, pageSize, 0, new ArrayList<T>(0));
    }

    public static <T> Pagination<T> create(Number pageIndex, Number pageSize, Number rowsCount) {
        return create(pageIndex, pageSize, rowsCount, new ArrayList<T>(0));
    }

    public static <T> Pagination<T> create(Number pageIndex, Number pageSize, Number rowsCount, List<T> data) {
        Pagination<T> p = new Pagination<T>(pageIndex.longValue(), pageSize.longValue(), rowsCount.longValue());
        if (data == null) {
            data = new ArrayList<T>(0);
        }
        p.setItems(data);
        return p;
    }

    public static <T> List<T> getPaginList(List<T> allList, int pageIndex, int pageSize) {
        List<T> result = new ArrayList<T>();
        int start = pageIndex < 2 ? 0 : ((pageIndex - 1) * pageSize);
        int end = start + pageSize > allList.size() ? allList.size() : start + pageSize;
        for (int i = start; i < end; i++) {
            result.add(allList.get(i));
        }
        return result;
    }
}
