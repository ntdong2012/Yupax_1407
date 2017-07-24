package vsec.com.yupax.presenter;

import javax.inject.Inject;

import vsec.com.yupax.base.RxPresenter;
import vsec.com.yupax.base.contract.ChangePasswordContract;
import vsec.com.yupax.model.DataManager;
import vsec.com.yupax.model.http.HttpHelper;
import vsec.com.yupax.model.http.request.ChangePasswordRequest;
import vsec.com.yupax.model.http.request.UserInfoChangePassword;
import vsec.com.yupax.model.http.response.ChangePasswordResponse;
import vsec.com.yupax.utils.CommonSubscriber;
import vsec.com.yupax.utils.RxUtil;
import vsec.com.yupax.utils.Utils;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/27/17.
 */

public class ChangePasswordPresenter extends RxPresenter<ChangePasswordContract.View> implements ChangePasswordContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public ChangePasswordPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void onChangePassword(String password, String newPassword) {

        ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest();

        changePasswordRequest = Utils.setupRequestFormat(changePasswordRequest);

        changePasswordRequest.setToken(mDataManager.getToken());
        changePasswordRequest.setMerchantCode(mDataManager.getCurrentMerchant());
        changePasswordRequest.setServiceName(HttpHelper.ServiceName.CHANGE_PASSWORD);

        UserInfoChangePassword userInfo = new UserInfoChangePassword();
        userInfo.setPasswordOld(password);
        userInfo.setPasswordNew(newPassword);
        userInfo.setPasswordNewConfirm(newPassword);

        changePasswordRequest.setUserInfo(userInfo);

        addSubscribe(mDataManager.changePassword(changePasswordRequest)
                .compose(RxUtil.<ChangePasswordResponse>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<ChangePasswordResponse>(mView) {
                    @Override
                    public void onNext(ChangePasswordResponse changePasswordResponse) {
                        mView.onChangePasswordSuccess(changePasswordResponse);
                    }
                })
        );

    }

    @Override
    public void setToken(String token) {
        mDataManager.setToken(token);
    }
}
