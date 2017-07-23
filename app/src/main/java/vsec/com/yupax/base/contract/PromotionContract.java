package vsec.com.yupax.base.contract;

import vsec.com.yupax.base.BasePresenter;
import vsec.com.yupax.base.BaseView;
import vsec.com.yupax.model.http.response.GetPromotionsResponse;

/**
 * Created by nguyenthanhdong0109@gmail.com on 7/24/17.
 */

public interface PromotionContract {

    interface View extends BaseView {

        void onGetPromotionsSuccess(GetPromotionsResponse getPromotionsResponse);

        void onLoading();

        void onStopLoading();

    }

    interface Presenter extends BasePresenter<View> {

        void getPromotions();
    }
}
