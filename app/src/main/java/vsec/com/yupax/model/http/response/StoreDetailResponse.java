package vsec.com.yupax.model.http.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ntdong2012 on 7/20/2017.
 */

public class StoreDetailResponse {

    @SerializedName("storeBranchInfo")
    private Store store;
    private ErrorResponse error;
    @SerializedName("serviceName")
    private String serviceName;

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

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
}
