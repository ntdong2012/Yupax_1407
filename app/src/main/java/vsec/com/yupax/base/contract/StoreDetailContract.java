package vsec.com.yupax.base.contract;

import vsec.com.yupax.base.BasePresenter;
import vsec.com.yupax.base.BaseView;
import vsec.com.yupax.model.http.response.StoreDetailResponse;

/**
 * Created by nguyenthanhdong0109@gmail.com on 6/6/2017.
 */

public interface StoreDetailContract {

    interface View extends BaseView {

        void onGetStoreDetailSuccess(StoreDetailResponse storeDetailResponse);

        void onLoading();

        void onStopLoading();

    }

    interface Presenter extends BasePresenter<View> {

        void getStoreDetail(String storeID);
    }
}
