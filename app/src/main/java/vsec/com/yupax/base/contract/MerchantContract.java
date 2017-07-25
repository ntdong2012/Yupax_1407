package vsec.com.yupax.base.contract;

import vsec.com.yupax.base.BasePresenter;
import vsec.com.yupax.base.BaseView;
import vsec.com.yupax.model.http.response.MerchantListResponse;

/**
 * Created by nguyenthanhdong0109@gmail.com on 7/14/17.
 */

public interface MerchantContract {

    interface View extends BaseView {

        void onGetMerchantListSuccess(MerchantListResponse merchantListResponse);

        void onLoading();

        void onStopLoading();

    }

    interface Presenter extends BasePresenter<View> {

        void onGetNotification();

        String getCurrentMerchantCode();

        void setCurrentMerchantCode(String str);

        void setCurrentMerchantName(String str);

        void getListMerchants();

        void setToken(String token);


    }
}
