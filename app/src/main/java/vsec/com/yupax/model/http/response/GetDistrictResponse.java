package vsec.com.yupax.model.http.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ntdong2012 on 7/25/2017.
 */

public class GetDistrictResponse extends BaseResponse {

    @SerializedName("listDistrict")
    private ArrayList<District> districts;

    public ArrayList<District> getDistricts() {
        return districts;
    }

    public void setDistricts(ArrayList<District> districts) {
        this.districts = districts;
    }
}
