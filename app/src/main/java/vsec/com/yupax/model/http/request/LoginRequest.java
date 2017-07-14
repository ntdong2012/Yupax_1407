package vsec.com.yupax.model.http.request;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/27/17.
 */

public class LoginRequest extends BaseRequest {

    private UserInfoForLogin userInfo;

    public UserInfoForLogin getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoForLogin userInfo) {
        this.userInfo = userInfo;
    }


}
