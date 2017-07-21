package vsec.com.yupax.model.http.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nguyenthanhdong0109@gmail.com on 7/21/17.
 */

public class Category {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
