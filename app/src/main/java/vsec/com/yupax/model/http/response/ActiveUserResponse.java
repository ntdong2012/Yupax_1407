package vsec.com.yupax.model.http.response;


import com.google.gson.annotations.SerializedName;

/**
 * Created by nguyenthanhdong0109@gmail.com on 7/23/17.
 */

public class ActiveUserResponse extends BaseResponse {

    @SerializedName("userInfo")
    private ActiveUser activeUser;

    public ActiveUser getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(ActiveUser activeUser) {
        this.activeUser = activeUser;
    }
}
