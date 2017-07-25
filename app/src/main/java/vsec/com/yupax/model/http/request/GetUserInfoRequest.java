package vsec.com.yupax.model.http.request;

/**
 * Created by ntdong2012 on 7/25/2017.
 */

public class GetUserInfoRequest extends BaseRequest {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
