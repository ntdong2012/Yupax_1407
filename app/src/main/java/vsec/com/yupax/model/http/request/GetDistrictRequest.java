package vsec.com.yupax.model.http.request;

/**
 * Created by ntdong2012 on 7/25/2017.
 */

public class GetDistrictRequest extends BaseRequest {

    private String provinceId;

    @Override
    public String getProvinceId() {
        return provinceId;
    }

    @Override
    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }
}
