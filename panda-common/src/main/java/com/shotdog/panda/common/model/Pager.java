package com.shotdog.panda.common.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/***
 *
 * @author ziming  Create At 2018-12-01 10:09
 *
 */
@Data
public class Pager<E> implements Serializable {


    private int pageNo = 1;

    private int pageSize = 10;

    private int totalCount;

    private int totalPage;

    private List<E> list;


    public Pager(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }


    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        this.totalPage = (this.totalCount % this.pageSize == 0) ? this.totalCount / this.pageSize : this.totalCount / this.pageSize + 1;
    }


}
