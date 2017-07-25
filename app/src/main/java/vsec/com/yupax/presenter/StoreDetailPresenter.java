package vsec.com.yupax.presenter;

import javax.inject.Inject;

import vsec.com.yupax.base.RxPresenter;
import vsec.com.yupax.base.contract.StoreDetailContract;
import vsec.com.yupax.model.DataManager;
import vsec.com.yupax.model.http.HttpHelper;
import vsec.com.yupax.model.http.request.StoreDetailRequest;
import vsec.com.yupax.model.http.response.StoreDetailResponse;
import vsec.com.yupax.utils.CommonSubscriber;
import vsec.com.yupax.utils.RxUtil;
import vsec.com.yupax.utils.Utils;

/**
 * Created by nguyenthanhdong0109@gmail.com on 6/6/2017.
 */

public class StoreDetailPresenter extends RxPresenter<StoreDetailContract.View> implements StoreDetailContract.Presenter {

    private DataManager dataManager;

    @Inject
    public StoreDetailPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void getStoreDetail(String storeID) {
        StoreDetailRequest storeDetailRequest = new StoreDetailRequest();
        storeDetailRequest = Utils.setupRequestFormat(storeDetailRequest);
        storeDetailRequest.setToken(dataManager.getToken());
        storeDetailRequest.setMerchantCode(dataManager.getCurrentMerchantCode());
        storeDetailRequest.setStoreBranchHashcode(storeID);
        storeDetailRequest.setServiceName(HttpHelper.ServiceName.DETAIL_STORE_BRANCH);
        addSubscribe(dataManager.getStoreDetail(storeDetailRequest)
                .compose(RxUtil.<StoreDetailResponse>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<StoreDetailResponse>(mView) {
                    @Override
                    public void onNext(StoreDetailResponse loginResponse) {
                        mView.onGetStoreDetailSuccess(loginResponse);
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
