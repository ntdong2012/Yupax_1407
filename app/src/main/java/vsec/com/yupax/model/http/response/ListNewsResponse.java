package vsec.com.yupax.model.http.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by nguyenthanhdong0109@gmail.com on 7/23/17.
 */

public class ListNewsResponse {

    @SerializedName("error")
    private ErrorResponse error;
    @SerializedName("serviceName")
    private String serviceName;
    @SerializedName("listNews")
    private ArrayList<News> newses;
    @SerializedName("pageIndex")
    private int pageIndex;
    @SerializedName("pageSize")
    private int pageSize;

    public ErrorResponse getError() {
        return error;
    }

    public void setError(ErrorResponse error) {
        this.error = error;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public ArrayList<News> getNewses() {
        return newses;
    }

    public void setNewses(ArrayList<News> newses) {
        this.newses = newses;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
