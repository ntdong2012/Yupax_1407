package vsec.com.yupax.base.contract;

import vsec.com.yupax.base.BasePresenter;
import vsec.com.yupax.base.BaseView;

/**
 * Created by nguyenthanhdong0109@gmail.com on 6/2/2017.
 */

public interface EventDetailContract {

    interface View extends BaseView {

        void onGetEventDetailSuccess();

        void onLoading();

        void onStopLoading();

    }

    interface Presenter extends BasePresenter<View> {

        void onGetEventDetail();
    }
}
