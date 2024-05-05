package com.pxg.demo.client.dto.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PageReturnT<T> extends ReturnInfo {

    private static final long serialVersionUID = 1L;

    private long totalCount = 0;
    private long pageSize = 1;
    private long pageIndex = 1;
    private List<T> data;

    public PageReturnT() {
    }

    public long getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getPageSize() {
        return this.pageSize < 1 ? 1 : this.pageSize;
    }

    public void setPageSize(long pageSize) {
        if (pageSize < 1) {
            this.pageSize = 1;
        } else {
            this.pageSize = pageSize;
        }

    }

    public long getPageIndex() {
        return this.pageIndex < 1 ? 1 : this.pageIndex;
    }

    public void setPageIndex(long pageIndex) {
        if (pageIndex < 1) {
            this.pageIndex = 1;
        } else {
            this.pageIndex = pageIndex;
        }

    }

    public List<T> getData() {
        return data;
    }

    public void setData(Collection<T> data) {
        if (data == null) {
            return;
        }
        this.data = new ArrayList<>(data);

    }

    public long getTotalPages() {
        return this.totalCount % this.pageSize == 0 ? this.totalCount / this.pageSize : this.totalCount / this.pageSize + 1;
    }

    public boolean isEmpty() {
        return this.data == null || this.data.isEmpty();
    }

    public boolean isNotEmpty() {
        return !this.isEmpty();
    }

    public static PageReturnT buildSuccess() {
        PageReturnT response = new PageReturnT();
        response.setSuccess(true);
        return response;
    }

    public static PageReturnT buildFailure(String errCode, String errMessage) {
        PageReturnT response = new PageReturnT();
        response.setSuccess(false);
        response.setErrCode(errCode);
        response.setErrMessage(errMessage);
        return response;
    }

    public static <T> PageReturnT<T> of(int pageSize, int pageIndex) {
        PageReturnT<T> response = new PageReturnT<>();
        response.setSuccess(true);
        response.setData(Collections.emptyList());
        response.setTotalCount(0);
        response.setPageSize(pageSize);
        response.setPageIndex(pageIndex);
        return response;
    }

    public static <T> PageReturnT<T> of(Collection<T> data, long totalCount, long pageSize, long pageIndex) {
        PageReturnT<T> response = new PageReturnT<>();
        response.setSuccess(true);
        response.setData(data);
        response.setTotalCount(totalCount);
        response.setPageSize(pageSize);
        response.setPageIndex(pageIndex);
        return response;
    }

    public static <E, T> PageReturnT<T> of(Collection<E> data, Function<E, T> convert, long totalCount, long pageSize, long pageIndex) {
        PageReturnT<T> response = new PageReturnT<>();
        response.setSuccess(true);
        if (data != null) {
            response.setData(data.stream().map(convert).collect(Collectors.toList()));
        }
        response.setTotalCount(totalCount);
        response.setPageSize(pageSize);
        response.setPageIndex(pageIndex);
        return response;
    }
}
