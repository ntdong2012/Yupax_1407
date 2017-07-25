package vsec.com.yupax.presenter;

import javax.inject.Inject;

import vsec.com.yupax.base.RxPresenter;
import vsec.com.yupax.base.contract.SettingContract;
import vsec.com.yupax.model.DataManager;
import vsec.com.yupax.model.http.HttpHelper;
import vsec.com.yupax.model.http.request.GetUserInfoRequest;
import vsec.com.yupax.model.http.response.GetUserInfoResponse;
import vsec.com.yupax.utils.CommonSubscriber;
import vsec.com.yupax.utils.RxUtil;
import vsec.com.yupax.utils.Utils;

/**
 * Created by nguyenthanhdong0109@gmail.com on 7/24/17.
 */

public class SettingPresenter extends RxPresenter<SettingContract.View> implements SettingContract.Presenter {


    private DataManager mDataManager;

    @Inject
    public SettingPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }


    @Override
    public void logout() {
        mDataManager.setToken("");
    }

    @Override
    public String getEmail() {
        return mDataManager.getEmail();
    }

    @Override
    public String getNumberPhone() {
        return mDataManager.getPhone();
    }

    @Override
    public String getUserName() {
        return mDataManager.getUserName();
    }

    @Override
    public String getMerchantName() {
        return mDataManager.getCurrentMerchantName();
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
                        mView.onGetUserInfoSuccess(baseResponse);
                    }
                })
        );
    }


}
