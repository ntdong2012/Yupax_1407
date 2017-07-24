package vsec.com.yupax.presenter;

import javax.inject.Inject;

import vsec.com.yupax.base.RxPresenter;
import vsec.com.yupax.base.contract.PromotionContract;
import vsec.com.yupax.model.DataManager;
import vsec.com.yupax.model.http.HttpHelper;
import vsec.com.yupax.model.http.request.GetPromotionRequest;
import vsec.com.yupax.model.http.response.GetPromotionsResponse;
import vsec.com.yupax.utils.CommonSubscriber;
import vsec.com.yupax.utils.RxUtil;
import vsec.com.yupax.utils.Utils;

/**
 * Created by nguyenthanhdong0109@gmail.com on 7/24/17.
 */

public class PromotionPresenter extends RxPresenter<PromotionContract.View> implements PromotionContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public PromotionPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }


    @Override
    public void getPromotions() {
        GetPromotionRequest getPromotionRequest = new GetPromotionRequest();
        getPromotionRequest.setServiceName(HttpHelper.ServiceName.LIST_PROMOTION);
        getPromotionRequest = Utils.setupRequestFormat(getPromotionRequest);
        getPromotionRequest.setPageIndex(1);
        getPromotionRequest.setMerchantCode(mDataManager.getCurrentMerchant());
        getPromotionRequest.setToken(mDataManager.getToken());
        getPromotionRequest.setPageSize(10);
        addSubscribe(mDataManager.getPromotions(getPromotionRequest)
                .compose(RxUtil.<GetPromotionsResponse>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<GetPromotionsResponse>(mView) {
                    @Override
                    public void onNext(GetPromotionsResponse listNewsResponse) {
                        mView.onGetPromotionsSuccess(listNewsResponse);
                    }
                })
        );
    }

    @Override
    public void saveToken(String token) {
        mDataManager.setToken(token);
    }
}
