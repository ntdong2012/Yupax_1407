package vsec.com.yupax.presenter;

import javax.inject.Inject;

import vsec.com.yupax.base.RxPresenter;
import vsec.com.yupax.base.contract.RateContract;
import vsec.com.yupax.model.DataManager;
import vsec.com.yupax.model.http.HttpHelper;
import vsec.com.yupax.model.http.request.RateRequest;
import vsec.com.yupax.model.http.response.GetUserInfoResponse;
import vsec.com.yupax.model.http.response.RateQuestionResponse;
import vsec.com.yupax.utils.CommonSubscriber;
import vsec.com.yupax.utils.RxUtil;
import vsec.com.yupax.utils.Utils;

/**
 * Created by nguyenthanhdong0109@gmail.com on 6/7/2017.
 */

public class RatePresenter extends RxPresenter<RateContract.View> implements RateContract.Presenter {


    private DataManager mDataManager;

    @Inject
    public RatePresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }


    @Override
    public void getRateQuestions() {
        RateRequest rateRequest = new RateRequest();
        rateRequest.setToken(mDataManager.getToken());
        rateRequest.setPageSize(10);
        rateRequest.setPageIndex(1);
        rateRequest.setMerchantCode(mDataManager.getCurrentMerchantCode());
        rateRequest.setServiceName(HttpHelper.ServiceName.SURVEY_LIST);
        rateRequest = Utils.setupRequestFormat(rateRequest);
        mView.onLoading();
        addSubscribe(mDataManager.getRateQuestionList(rateRequest)
                .compose(RxUtil.<RateQuestionResponse>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<RateQuestionResponse>(mView) {
                    @Override
                    public void onNext(RateQuestionResponse baseResponse) {
                        mView.onStopLoading();
                        mView.getRateQuestionSuccess(baseResponse);
                    }
                })
        );
    }
}
