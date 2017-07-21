package vsec.com.yupax.model.http.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by nguyenthanhdong0109@gmail.com on 7/21/17.
 */

public class GetCategoriesResponse {


    @SerializedName("listCategory")
    private ArrayList<Category> categories;
    @SerializedName("error")
    private ErrorResponse errorResponse;
    @SerializedName("serviceName")
    private String serviceName;

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
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
