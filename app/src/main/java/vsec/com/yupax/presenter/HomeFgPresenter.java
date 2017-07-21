package vsec.com.yupax.presenter;

import javax.inject.Inject;

import vsec.com.yupax.base.RxPresenter;
import vsec.com.yupax.base.contract.HomeFgContract;
import vsec.com.yupax.model.DataManager;
import vsec.com.yupax.model.http.HttpHelper;
import vsec.com.yupax.model.http.request.BaseRequest;
import vsec.com.yupax.model.http.request.ListStoreRequest;
import vsec.com.yupax.model.http.response.GetCategoriesResponse;
import vsec.com.yupax.model.http.response.ListStoreResponse;
import vsec.com.yupax.utils.CommonSubscriber;
import vsec.com.yupax.utils.RxUtil;
import vsec.com.yupax.utils.Utils;

/**
 * Created by nguyenthanhdong0109@gmail.com on 7/9/17.
 */

public class HomeFgPresenter extends RxPresenter<HomeFgContract.View> implements HomeFgContract.Presenter {

    private DataManager dataManager;

    @Inject
    public HomeFgPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }


    @Override
    public void getListStores(String searchKey) {

        ListStoreRequest listStoreRequest = new ListStoreRequest();
        listStoreRequest = Utils.setupRequestFormat(listStoreRequest);

        listStoreRequest.setServiceName(HttpHelper.ServiceName.LIST_STORE_BRANCH);
        listStoreRequest.setKeySearch(searchKey);
        listStoreRequest.setPageIndex(1);
        listStoreRequest.setCategoryId(0);
        listStoreRequest.setPageSize(10);
        listStoreRequest.setMerchantCode(dataManager.getCurrentMerchant());
        listStoreRequest.setToken(dataManager.getToken());

        addSubscribe(dataManager.getListStoreBrand(listStoreRequest)
                .compose(RxUtil.<ListStoreResponse>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<ListStoreResponse>(mView) {
                    @Override
                    public void onNext(ListStoreResponse loginResponse) {
                        mView.onGetListStoreSuccess(loginResponse);
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
    public void getCategories() {
        BaseRequest baseRequest = new BaseRequest();
        baseRequest.setServiceName(HttpHelper.ServiceName.LIST_CATEGORY);
        baseRequest = Utils.setupRequestFormat(baseRequest);

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
}
