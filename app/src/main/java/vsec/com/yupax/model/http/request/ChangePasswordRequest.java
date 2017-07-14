package vsec.com.yupax.model.http.request;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/31/17.
 */

public class ChangePasswordRequest extends BaseRequest {

    private String token;
    private UserInfoChangePassword userInfo;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserInfoChangePassword getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoChangePassword userInfo) {
        this.userInfo = userInfo;
    }
}
