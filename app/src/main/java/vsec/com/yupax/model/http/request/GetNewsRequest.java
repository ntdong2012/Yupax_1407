package vsec.com.yupax.model.http.request;

/**
 * Created by ntdong2012 on 7/24/2017.
 */

public class GetNewsRequest extends BaseRequest{

    private int pageIndex;
    private int pageSize;
    private String token;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
