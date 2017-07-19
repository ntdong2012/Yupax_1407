package vsec.com.yupax.base.contract;

import vsec.com.yupax.base.BasePresenter;
import vsec.com.yupax.base.BaseView;
import vsec.com.yupax.model.http.response.LoginResponse;
import vsec.com.yupax.model.http.response.UserInfoResponse;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/25/17.
 */

public interface SignInContract {

    interface View extends BaseView {

        void onSignInSuccess(LoginResponse loginResponse);

        void onSignInError();

        void onLoading();

        void onStopLoading();

    }

    interface Presenter extends BasePresenter<View> {

        void onSignIn(String userName, String password);

        void onSavedToken(String token);

        void onSaveUserInfo(UserInfoResponse userInfoResponse);

        String getUserName();

        String getUserEmail();
    }
}
