package vsec.com.yupax.model.http.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ntdong2012 on 7/20/2017.
 */

public class StoreDetailRequest extends BaseRequest {

    @SerializedName("storeBranchHashcode")
    private String storeBranchHashcode;
    @SerializedName("token")
    private String token;

    public String getStoreBranchHashcode() {
        return storeBranchHashcode;
    }

    public void setStoreBranchHashcode(String storeBranchHashcode) {
        this.storeBranchHashcode = storeBranchHashcode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
