package vsec.com.yupax.model.http.response;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/31/17.
 */

public class ChangePasswordResponse {

    private ErrorResponse error;
    private String serviceName;

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
