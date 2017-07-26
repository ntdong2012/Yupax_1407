package vsec.com.yupax.presenter;

import javax.inject.Inject;

import vsec.com.yupax.base.RxPresenter;
import vsec.com.yupax.base.contract.PersonalContract;
import vsec.com.yupax.model.DataManager;
import vsec.com.yupax.model.http.HttpHelper;
import vsec.com.yupax.model.http.request.GetUserInfoRequest;
import vsec.com.yupax.model.http.response.GetUserInfoResponse;
import vsec.com.yupax.utils.CommonSubscriber;
import vsec.com.yupax.utils.RxUtil;
import vsec.com.yupax.utils.Utils;

/**
 * Created by nguyenthanhdong0109@gmail.com on 7/10/17.
 */

public class PersonalPresenter extends RxPresenter<PersonalContract.View> implements PersonalContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public PersonalPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }


    @Override
    public void onGetNotification() {

    }

    @Override
    public void getUserInfo() {
        GetUserInfoRequest guir = new GetUserInfoRequest();
        guir.setToken(mDataManager.getToken());
        guir.setMerchantCode(mDataManager.getCurrentMerchantCode());
        guir = Utils.setupRequestFormat(guir);
        guir.setServiceName(HttpHelper.ServiceName.GET_USER_INFO);
        mView.onLoading();
        addSubscribe(mDataManager.getUserInfo(guir)
                .compose(RxUtil.<GetUserInfoResponse>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<GetUserInfoResponse>(mView) {
                    @Override
                    public void onNext(GetUserInfoResponse baseResponse) {
                        mView.onStopLoading();
                        mView.getUserInfoSuccess(baseResponse);
                    }
                })
        );
    }
}
