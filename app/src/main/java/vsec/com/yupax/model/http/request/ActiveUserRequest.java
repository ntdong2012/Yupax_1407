package vsec.com.yupax.model.http.request;

/**
 * Created by nguyenthanhdong0109@gmail.com on 7/23/17.
 */

public class ActiveUserRequest extends BaseRequest {


    private ActiveUser userInfo;

    public ActiveUser getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(ActiveUser userInfo) {
        this.userInfo = userInfo;
    }
}
