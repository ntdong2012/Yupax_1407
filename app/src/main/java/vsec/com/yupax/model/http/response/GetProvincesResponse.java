package vsec.com.yupax.model.http.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ntdong2012 on 7/21/2017.
 */

public class GetProvincesResponse {

    @SerializedName("listProvince")
    private ArrayList<Province> provinces;
    @SerializedName("error")
    private ErrorResponse errorResponse;
    @SerializedName("serviceName")
    private String serviceName;

    public ArrayList<Province> getProvinces() {
        return provinces;
    }

    public void setProvinces(ArrayList<Province> provinces) {
        this.provinces = provinces;
    }

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
