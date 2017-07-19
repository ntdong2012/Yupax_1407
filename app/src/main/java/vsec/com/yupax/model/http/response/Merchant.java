package vsec.com.yupax.model.http.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nguyenthanhdong0109@gmail.com on 7/18/17.
 */

public class Merchant {

    @SerializedName("hashcode")
    private String hashcode;
    @SerializedName("name")
    private String name;

    public String getHashcode() {
        return hashcode;
    }

    public void setHashcode(String hashcode) {
        this.hashcode = hashcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
