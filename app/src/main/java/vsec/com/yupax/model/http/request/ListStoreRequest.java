package vsec.com.yupax.model.http.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ntdong2012 on 7/20/2017.
 */

public class ListStoreRequest extends BaseRequest {

    @SerializedName("pageIndex")
    private int pageIndex;
    @SerializedName("pageSize")
    private int pageSize;
    @SerializedName("keySearch")
    private String keySearch;
    @SerializedName("token")
    private String token;
    @SerializedName("categoryId")
    private int categoryId;
    @SerializedName("latitude")
    private String latitude;
    @SerializedName("longitude")
    private String longitude;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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

    public String getKeySearch() {
        return keySearch;
    }

    public void setKeySearch(String keySearch) {
        this.keySearch = keySearch;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
