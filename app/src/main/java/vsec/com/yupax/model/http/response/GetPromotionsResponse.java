package vsec.com.yupax.model.http.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by nguyenthanhdong0109@gmail.com on 7/24/17.
 */

public class GetPromotionsResponse extends BaseResponse{

    @SerializedName("listPromotion")
    private ArrayList<Promotion> promotions;
    private int pageIndex;
    private int pageSize;

    public ArrayList<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(ArrayList<Promotion> promotions) {
        this.promotions = promotions;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
