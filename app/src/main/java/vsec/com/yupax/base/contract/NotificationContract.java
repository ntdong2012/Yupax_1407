package vsec.com.yupax.base.contract;

import vsec.com.yupax.base.BasePresenter;
import vsec.com.yupax.base.BaseView;
import vsec.com.yupax.model.http.response.ListNewsResponse;

/**
 * Created by nguyenthanhdong0109@gmail.com on 6/1/2017.
 */

public interface NotificationContract {

    interface View extends BaseView {

        void onGetNotificationSuccess(ListNewsResponse listNewsResponse);

        void onLoading();

        void onStopLoading();

    }

    interface Presenter extends BasePresenter<View> {

        void onGetNotification();
    }
}
