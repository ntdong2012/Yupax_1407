package vsec.com.yupax.base.contract;

import vsec.com.yupax.base.BasePresenter;
import vsec.com.yupax.base.BaseView;
import vsec.com.yupax.model.http.response.GetPromotionsResponse;
import vsec.com.yupax.model.http.response.ListNewsResponse;

/**
 * Created by ntdong2012 on 7/24/2017.
 */

public interface NewContract {

    interface View extends BaseView {

        void onGetNewsSuccess(ListNewsResponse listNewsResponse);

        void onLoading();

        void onStopLoading();

    }

    interface Presenter extends BasePresenter<View> {

        void getNews();

        void saveToken(String token);
    }
}
