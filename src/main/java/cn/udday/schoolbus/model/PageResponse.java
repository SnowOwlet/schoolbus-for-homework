package cn.udday.schoolbus.model;

public class PageResponse<T> extends Response<T> {

    private int pageTotal;
    public PageResponse(T data, Integer pageTotal) {
        super(data,"操作成功！");
        this.pageTotal = pageTotal;
    }

    public PageResponse(int i, String s, String text) {
        super(i, s, text);
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }
}
