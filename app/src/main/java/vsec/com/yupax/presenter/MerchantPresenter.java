package vsec.com.yupax.presenter;

import javax.inject.Inject;

import vsec.com.yupax.base.RxPresenter;
import vsec.com.yupax.base.contract.MerchantContract;
import vsec.com.yupax.model.DataManager;
import vsec.com.yupax.model.http.HttpHelper;
import vsec.com.yupax.model.http.request.MerchantListRequest;
import vsec.com.yupax.model.http.response.MerchantListResponse;
import vsec.com.yupax.utils.CommonSubscriber;
import vsec.com.yupax.utils.RxUtil;
import vsec.com.yupax.utils.Utils;

/**
 * Created by nguyenthanhdong0109@gmail.com on 7/14/17.
 */

public class MerchantPresenter extends RxPresenter<MerchantContract.View> implements MerchantContract.Presenter {

    private DataManager dataManager;

    @Inject
    public MerchantPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }


    @Override
    public void onGetNotification() {

    }

    @Override
    public String getCurrentMerchantCode() {
        return dataManager.getCurrentMerchantCode();
    }

    @Override
    public void setCurrentMerchantCode(String str) {
        dataManager.setCurrentMerchantCode(str);
    }

    @Override
    public void setCurrentMerchantName(String str) {
        dataManager.setCurrentMerchantName(str);
    }

    @Override
    public void getListMerchants() {

        MerchantListRequest merchantListRequest = new MerchantListRequest();

        merchantListRequest = Utils.setupRequestFormat(merchantListRequest);

        merchantListRequest.setServiceName(HttpHelper.ServiceName.LIST_MERCHANT);
        merchantListRequest.setToken(dataManager.getToken());
        merchantListRequest.setPageIndex(1);
        merchantListRequest.setPageSize(10);


        addSubscribe(dataManager.getListMerchant(merchantListRequest)
                .compose(RxUtil.<MerchantListResponse>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<MerchantListResponse>(mView) {
                    @Override
                    public void onNext(MerchantListResponse loginResponse) {
                        mView.onGetMerchantListSuccess(loginResponse);
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
    public void setToken(String token) {
        dataManager.setToken(token);
    }


}
