package vsec.com.yupax.base.contract;

import vsec.com.yupax.base.BasePresenter;
import vsec.com.yupax.base.BaseView;
import vsec.com.yupax.model.http.response.ChangePasswordResponse;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/27/17.
 */

public interface ChangePasswordContract {

    interface View extends BaseView {

        void onChangePasswordSuccess(ChangePasswordResponse changePasswordResponse);

        void onLoading();

        void onStopLoading();

    }

    interface Presenter extends BasePresenter<View> {

        void onChangePassword(String password, String newPassword);

        void setToken(String token);
    }
}
