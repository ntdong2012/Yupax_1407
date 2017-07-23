package vsec.com.yupax.model.http.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nguyenthanhdong0109@gmail.com on 7/23/17.
 */

public class BaseResponse {


    @SerializedName("error")
    private ErrorResponse errorResponse;
    @SerializedName("serviceName")
    private String serviceName;

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
