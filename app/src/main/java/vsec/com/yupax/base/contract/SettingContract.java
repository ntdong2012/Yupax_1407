package vsec.com.yupax.base.contract;

import vsec.com.yupax.base.BasePresenter;
import vsec.com.yupax.base.BaseView;
import vsec.com.yupax.model.http.response.GetUserInfoResponse;

/**
 * Created by nguyenthanhdong0109@gmail.com on 7/24/17.
 */

public interface SettingContract {

    interface View extends BaseView {

        void onLogoutSuccess();

        void onLoading();

        void onGetUserInfoSuccess(GetUserInfoResponse getUserInfoResponse);

        void onStopLoading();

    }

    interface Presenter extends BasePresenter<View> {

        void logout();

        String getEmail();

        String getNumberPhone();

        String getUserName();

        String getMerchantName();

        void getUserInfo();


    }

}
