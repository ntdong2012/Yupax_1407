package vsec.com.yupax.base.contract;

import vsec.com.yupax.base.BasePresenter;
import vsec.com.yupax.base.BaseView;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/28/17.
 */

public interface ChangeProfileContract {

    interface View extends BaseView {

        void onChangeProfileSuccess();

        void onLoading();

        void onStopLoading();

    }

    interface Presenter extends BasePresenter<View> {

        void onChangeProfile(String password, String newPassword);
    }
}
