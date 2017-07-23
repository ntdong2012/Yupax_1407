package vsec.com.yupax.model.http.request;

/**
 * Created by nguyenthanhdong0109@gmail.com on 7/23/17.
 */

public class RegisterUserToMerchantRequest extends BaseRequest {


    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
