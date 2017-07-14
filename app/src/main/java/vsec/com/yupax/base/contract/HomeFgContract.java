package vsec.com.yupax.base.contract;

import vsec.com.yupax.base.BasePresenter;
import vsec.com.yupax.base.BaseView;

/**
 * Created by nguyenthanhdong0109@gmail.com on 7/9/17.
 */

public interface HomeFgContract {


    interface View extends BaseView {

        void onGetProductItemsSuccess();

        void onLoading();

        void onStopLoading();

    }

    interface Presenter extends BasePresenter<View> {

        void getProductItems();
    }
}
