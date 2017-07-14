package vsec.com.yupax.base.contract;

import vsec.com.yupax.base.BasePresenter;
import vsec.com.yupax.base.BaseView;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/25/17.
 */

public interface SignUpContract {

    interface View extends BaseView {

        void onSignUpSuccess();

        void onSignUpError();

        void onLoading();

        void onStopLoading();

    }

    interface Presenter extends BasePresenter<View> {

        void onSignUp();
    }
}
