package vsec.com.yupax.base.contract;

import vsec.com.yupax.base.BasePresenter;
import vsec.com.yupax.base.BaseView;
import vsec.com.yupax.model.http.request.LoginResponseNew;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/25/17.
 */

public interface SignInContract {

    interface View extends BaseView {

        void onSignInSuccess(LoginResponseNew loginResponse);

        void onSignInError();

        void onLoading();

        void onStopLoading();

    }

    interface Presenter extends BasePresenter<View> {

        void onSignIn(String userName, String password);

        void onSavedToken(String token);

    }
}
