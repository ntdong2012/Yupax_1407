package vsec.com.yupax.base.contract;

import vsec.com.yupax.base.BasePresenter;
import vsec.com.yupax.base.BaseView;
import vsec.com.yupax.model.http.request.UserInfoChange;
import vsec.com.yupax.model.http.response.BaseResponse;
import vsec.com.yupax.model.http.response.GetDistrictResponse;
import vsec.com.yupax.model.http.response.GetProvincesResponse;
import vsec.com.yupax.model.http.response.GetUserInfoResponse;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/28/17.
 */

public interface ChangeProfileContract {

    interface View extends BaseView {

        void onChangeProfileSuccess(BaseResponse baseResponse);

        void onLoading();

        void onGetProvincesSuccess(GetProvincesResponse getProvincesResponse);

        void onStopLoading();

        void onGetUserInfoSuccess(GetUserInfoResponse getUserInfoResponse);

        void onGetDistrictSuccess(GetDistrictResponse getDistrictResponse);


    }

    interface Presenter extends BasePresenter<View> {

        void requestChangeProfile(String firstName, String lastName, String gender, String provinceId, String districtId, String address, String email, long dateOfBirth, String phone);

        void getProvinces();

        void saveUserName(String userName);

        void getUserInfo();

        void getDistricts(String provinceId);

        UserInfoChange getUserInfoFromDb();
    }
}
