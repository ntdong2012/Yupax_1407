package vsec.com.yupax.model.http.response;

/**
 * Created by nguyenthanhdong0109@gmail.com on 8/15/17.
 */

public class PromotionRp {

    private String name;
    private String description;
    private String images;
    private String promotionId;
    private String promotionHascode;

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

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId;
    }

    public String getPromotionHascode() {
        return promotionHascode;
    }

    public void setPromotionHascode(String promotionHascode) {
        this.promotionHascode = promotionHascode;
    }
}
