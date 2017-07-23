package vsec.com.yupax.model.http.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nguyenthanhdong0109@gmail.com on 7/23/17.
 */

public class CreateUserRequest extends BaseRequest {

    @SerializedName("userInfo")
    private CreateUser userInfo;

    public CreateUser getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(CreateUser userInfo) {
        this.userInfo = userInfo;
    }
}
