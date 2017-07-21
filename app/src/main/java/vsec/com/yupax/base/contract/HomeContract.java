package vsec.com.yupax.base.contract;

import vsec.com.yupax.base.BasePresenter;
import vsec.com.yupax.base.BaseView;
import vsec.com.yupax.model.http.response.GetCategoriesResponse;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/24/2017.
 */

public interface HomeContract {

    interface View extends BaseView {

        void onGetCategoriesSuccess(GetCategoriesResponse getCategoriesResponse);

        void getProvincesSuccess();

        void onLoading();

        void onStopLoading();

    }

    interface Presenter extends BasePresenter<View> {

        void getCategories();

        void getProvinces();
    }
}
