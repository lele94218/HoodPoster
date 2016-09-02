package com.hood.common;

import java.util.Collection;

/**
 * @author taoranxue on 9/1/16 9:24 PM.
 */
public class Page<T> {
    //page size
    private int pageSize;
    //page number
    private int pageNumber;
    //start index
    private int firstEntityIndex;
    //end index
    private int lastEntityIndex;

    private Collection<T> entities;
    //elements number
    private int entityCount;
    //pages number
    private int pageCount;

    public Page(int pageSize, int pageNumber, int entityCount) {
        if (pageNumber > 1 && pageSize <= 0) {
            throw new IllegalArgumentException("Illegal paging arguments. [pageSize =" + pageSize
                    + ", pageIndex=" + pageNumber + "]");
        }
        if (pageSize < 0)
            pageSize = 0;
        if (pageNumber < 1)
            pageNumber = 1;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.entityCount = entityCount;
        firstEntityIndex = (pageNumber - 1) * pageSize;
        lastEntityIndex = pageNumber * pageSize;

        if (entityCount % pageSize > 0) {
            pageCount = entityCount / pageSize + 1;
        } else {
            pageCount = entityCount / pageSize;
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getFirstEntityIndex() {
        return firstEntityIndex;
    }

    public void setFirstEntityIndex(int firstEntityIndex) {
        this.firstEntityIndex = firstEntityIndex;
    }

    public int getLastEntityIndex() {
        return lastEntityIndex;
    }

    public void setLastEntityIndex(int lastEntityIndex) {
        this.lastEntityIndex = lastEntityIndex;
    }

    public Collection<T> getEntities() {
        return entities;
    }

    public void setEntities(Collection<T> entities) {
        this.entities = entities;
    }

    public int getEntityCount() {
        return entityCount;
    }

    public void setEntityCount(int entityCount) {
        this.entityCount = entityCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
