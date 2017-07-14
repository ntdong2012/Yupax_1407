package vsec.com.yupax.base.contract;

import vsec.com.yupax.base.BasePresenter;
import vsec.com.yupax.base.BaseView;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/26/2017.
 */

public interface ForgotPasswordContract {

    interface View extends BaseView {

        void onGetPasswordSuccess();

        void onLoading();

        void onStopLoading();

    }

    interface Presenter extends BasePresenter<View> {

        void onResendPassword(String userName);
    }
}
