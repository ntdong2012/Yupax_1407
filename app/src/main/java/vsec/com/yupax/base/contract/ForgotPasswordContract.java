package vsec.com.yupax.base.contract;

import vsec.com.yupax.base.BasePresenter;
import vsec.com.yupax.base.BaseView;
import vsec.com.yupax.model.http.response.ActiveUserResponse;
import vsec.com.yupax.model.http.response.BaseResponse;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/26/2017.
 */

public interface ForgotPasswordContract {

    interface View extends BaseView {

        void onGetPasswordSuccess(BaseResponse baseResponse);

        void onActiveUserSuccess(ActiveUserResponse baseResponse);

        void onLoading();

        void onStopLoading();

    }

    interface Presenter extends BasePresenter<View> {

        void onResendPassword(String userName);

        void activeAccount(String activeCode);

        void onSaveEmail(String email);

        void onSaveToken(String token);
    }
}
