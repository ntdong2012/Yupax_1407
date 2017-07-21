package vsec.com.yupax.presenter;

import javax.inject.Inject;

import vsec.com.yupax.base.RxPresenter;
import vsec.com.yupax.base.contract.HomeContract;
import vsec.com.yupax.model.DataManager;
import vsec.com.yupax.model.http.HttpHelper;
import vsec.com.yupax.model.http.request.BaseRequest;
import vsec.com.yupax.model.http.response.GetCategoriesResponse;
import vsec.com.yupax.utils.CommonSubscriber;
import vsec.com.yupax.utils.RxUtil;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/24/2017.
 */

public class HomePresenter extends RxPresenter<HomeContract.View> implements HomeContract.Presenter {

    private DataManager dataManager;

    @Inject
    public HomePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }


    @Override
    public void getCategories() {
        BaseRequest baseRequest= new BaseRequest();
        baseRequest.setServiceName(HttpHelper.ServiceName.LIST_CATEGORY);

        addSubscribe(dataManager.getCategories(baseRequest)
                .compose(RxUtil.<GetCategoriesResponse>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<GetCategoriesResponse>(mView) {
                    @Override
                    public void onNext(GetCategoriesResponse loginResponse) {
                        mView.onGetCategoriesSuccess(loginResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        e.printStackTrace();
                    }
                })
        );
    }

    @Override
    public void getProvinces() {

    }
}
