package vsec.com.yupax.model.http.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by nguyenthanhdong0109@gmail.com on 7/18/17.
 */

public class MerchantListResponse {

    @SerializedName("serviceName")
    private String serviceName;
    @SerializedName("error")
    private ErrorResponse errorResponse;
    @SerializedName("pageSize")
    private int pageSize;
    @SerializedName("pageIndex")
    private int pageIndex;
    @SerializedName("listMerchant")
    private List<Merchant> merchants;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

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

    public List<Merchant> getMerchants() {
        return merchants;
    }

    public void setMerchants(List<Merchant> merchants) {
        this.merchants = merchants;
    }
}
