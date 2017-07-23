package vsec.com.yupax.model.http.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nguyenthanhdong0109@gmail.com on 7/23/17.
 */

public class News {

    @SerializedName("id")
    private int id;
    @SerializedName("hashcode")
    private String hashCode;
    @SerializedName("images")
    private String images;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
