package vsec.com.yupax.model.http.request;

/**
 * Created by nguyenthanhdong0109@gmail.com on 7/24/17.
 */

public class GetPromotionRequest extends BaseRequest {

    private int pageIndex;
    private int pageSize;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
