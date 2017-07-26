package vsec.com.yupax.base.contract;

import vsec.com.yupax.base.BasePresenter;
import vsec.com.yupax.base.BaseView;
import vsec.com.yupax.model.http.response.GetUserInfoResponse;

/**
 * Created by nguyenthanhdong0109@gmail.com on 7/10/17.
 */

public interface PersonalContract {


    interface View extends BaseView {

        void getRateQuestionSuccess();

        void onLoading();

        void getUserInfoSuccess(GetUserInfoResponse userInfoRes);

        void onStopLoading();

    }

    interface Presenter extends BasePresenter<View> {

        void onGetNotification();

        void getUserInfo();
    }

}
