package vsec.com.yupax.base.contract;

import vsec.com.yupax.base.BasePresenter;
import vsec.com.yupax.base.BaseView;

/**
 * Created by nguyenthanhdong0109@gmail.com on 7/14/17.
 */

public interface MerchantContract {

    interface View extends BaseView {

        void onGetNotificationSuccess();

        void onLoading();

        void onStopLoading();

    }

    interface Presenter extends BasePresenter<View> {

        void onGetNotification();

        String getCurrentMerchant();

        void setCurrentMerchant(String str);
    }
}
