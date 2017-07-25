package vsec.com.yupax.model.http.response;

import com.google.gson.annotations.SerializedName;

import vsec.com.yupax.model.http.request.UserInfoChange;

/**
 * Created by ntdong2012 on 7/25/2017.
 */

public class GetUserInfoResponse extends BaseResponse {

    @SerializedName("userInfo")
    private UserInfoChange userInfoChange;

    public UserInfoChange getUserInfoChange() {
        return userInfoChange;
    }

    public void setUserInfoChange(UserInfoChange userInfoChange) {
        this.userInfoChange = userInfoChange;
    }
}
