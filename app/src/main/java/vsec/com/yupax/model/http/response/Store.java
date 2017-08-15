package vsec.com.yupax.model.http.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ntdong2012 on 7/20/2017.
 */

public class Store {

    @SerializedName("storeId")
    private int storeId;
    @SerializedName("storeBranchHashcode")
    private String storeBranchHashcode;
    @SerializedName("storeBranchId")
    private int storeBranchId;
    @SerializedName("storeName")
    private String storeName;
    @SerializedName("storeBranchName")
    private String storeBranchName;
    @SerializedName("mobile")
    private String mobile;
    @SerializedName("email")
    private String email;
    @SerializedName("lat")
    private String lat;
    @SerializedName("long")
    private String lg;
    @SerializedName("openTime")
    private String openTime;
    @SerializedName("address")
    private String address;
    @SerializedName("provinceId")
    private String provinceId;
    @SerializedName("districtId")
    private String districtId;
    @SerializedName("wardId")
    private String wardId;
    @SerializedName("logo")
    private String logo;

    @SerializedName("images")
    private String images;
    @SerializedName("name")
    private String name;

    @SerializedName("promotion")
    private String promotion;
    @SerializedName("description")
    private String description;

    @SerializedName("listPromotion")
    private ArrayList<Promotion> promotions;


    public ArrayList<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(ArrayList<Promotion> promotions) {
        this.promotions = promotions;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private double myLat;
    private double myLog;

    public double getMyLat() {
        return myLat;
    }

    public void setMyLat(double myLat) {
        this.myLat = myLat;
    }

    public double getMyLog() {
        return myLog;
    }

    public void setMyLog(double myLog) {
        this.myLog = myLog;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getStoreBranchHashcode() {
        return storeBranchHashcode;
    }

    public void setStoreBranchHashcode(String storeBranchHashcode) {
        this.storeBranchHashcode = storeBranchHashcode;
    }

    public int getStoreBranchId() {
        return storeBranchId;
    }

    public void setStoreBranchId(int storeBranchId) {
        this.storeBranchId = storeBranchId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreBranchName() {
        return storeBranchName;
    }

    public void setStoreBranchName(String storeBranchName) {
        this.storeBranchName = storeBranchName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLg() {
        return lg;
    }

    public void setLg(String lg) {
        this.lg = lg;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getWardId() {
        return wardId;
    }

    public void setWardId(String wardId) {
        this.wardId = wardId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
