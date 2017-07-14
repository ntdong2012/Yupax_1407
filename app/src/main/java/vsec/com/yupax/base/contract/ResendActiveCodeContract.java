package vsec.com.yupax.base.contract;

import vsec.com.yupax.base.BasePresenter;
import vsec.com.yupax.base.BaseView;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/28/17.
 */

public interface ResendActiveCodeContract {

    interface View extends BaseView {

        void onResendOtpSuccess();

        void onLoading();

        void onStopLoading();

    }

    interface Presenter extends BasePresenter<View> {

        void onResendOtp(String userName);
    }
}
