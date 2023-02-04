package com.jun.xiaomi.utils;

/**
 * @author : Bojack
 * @date : Created in 17:34 2022.10.12
 */
public class PageTools {
    private int currentPage;//��ǰҳ
    private int pageSize;//ÿҳ��ʾ������
    private int totalSize;//������
    private int index;//����
    private int prePage;//��һҳ
    private int totalPage;//��ҳ��
    private int nextPage;//��һҳ


    //�ṩ���������Ĺ��췽��
    public PageTools(int currentPage, int pageSize, int totalSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalSize = totalSize;
        index = (currentPage - 1) * pageSize;
        prePage = currentPage == 1 ? 1 : currentPage - 1;
        totalPage = totalSize / pageSize + (totalSize % pageSize == 0 ? 0 : 1);
        nextPage = currentPage == totalPage ? currentPage : currentPage + 1;
    }

    @Override
    public String toString() {
        return "PageTools{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", totalSize=" + totalSize +
                ", index=" + index +
                ", prePage=" + prePage +
                ", totalPage=" + totalPage +
                ", nextPage=" + nextPage +
                '}';
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }
}
