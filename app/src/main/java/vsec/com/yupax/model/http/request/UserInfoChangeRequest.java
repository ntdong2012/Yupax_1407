package vsec.com.yupax.model.http.request;

/**
 * Created by nguyenthanhdong0109@gmail.com on 7/24/17.
 */

public class UserInfoChangeRequest extends BaseRequest {


    private UserInfoChange userInfo;
    private String token;

    public UserInfoChange getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoChange userInfo) {
        this.userInfo = userInfo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
