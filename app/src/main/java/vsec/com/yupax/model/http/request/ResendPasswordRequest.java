package vsec.com.yupax.model.http.request;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/27/17.
 */

public class ResendPasswordRequest extends BaseRequest {

    private UserInfoForResendPassword userInfo;

    public UserInfoForResendPassword getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoForResendPassword userInfo) {
        this.userInfo = userInfo;
    }
}
