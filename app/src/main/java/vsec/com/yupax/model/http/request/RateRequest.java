package vsec.com.yupax.model.http.request;

/**
 * Created by ntdong2012 on 8/4/2017.
 */

public class RateRequest extends BaseRequest {


    private int pageSize;
    private int pageIndex;
    private String token;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}





