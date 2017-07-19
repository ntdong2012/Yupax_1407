package vsec.com.yupax.model.http.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nguyenthanhdong0109@gmail.com on 7/18/17.
 */

public class MerchantListRequest extends BaseRequest {


    @SerializedName("pageIndex")
    private int pageIndex;
    @SerializedName("pageSize")
    private int pageSize;
    @SerializedName("token")
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
