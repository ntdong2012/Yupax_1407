package vsec.com.yupax.presenter;

import javax.inject.Inject;

import vsec.com.yupax.base.RxPresenter;
import vsec.com.yupax.base.contract.ForgotPasswordContract;
import vsec.com.yupax.model.DataManager;
import vsec.com.yupax.model.http.HttpHelper;
import vsec.com.yupax.model.http.request.ActiveUser;
import vsec.com.yupax.model.http.request.ActiveUserRequest;
import vsec.com.yupax.model.http.request.ResendPasswordRequest;
import vsec.com.yupax.model.http.request.UserInfoForResendPassword;
import vsec.com.yupax.model.http.response.ActiveUserResponse;
import vsec.com.yupax.model.http.response.BaseResponse;
import vsec.com.yupax.utils.CommonSubscriber;
import vsec.com.yupax.utils.RxUtil;
import vsec.com.yupax.utils.Utils;
import vsec.com.yupax.utils.log.DLog;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/26/2017.
 */

public class ForgotPasswordPresenter extends RxPresenter<ForgotPasswordContract.View> implements ForgotPasswordContract.Presenter {

    private DataManager dataManager;

    @Inject
    public ForgotPasswordPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }


    @Override
    public void onResendPassword(String userName) {
        ResendPasswordRequest resendPasswordRequest = new ResendPasswordRequest();

        resendPasswordRequest = Utils.setupRequestFormat(resendPasswordRequest);
        resendPasswordRequest.setServiceName(HttpHelper.ServiceName.FORGOT_PASSWORD);

        UserInfoForResendPassword userInfo = new UserInfoForResendPassword();
        userInfo.setUsername(userName);
        resendPasswordRequest.setUserInfo(userInfo);

        addSubscribe(dataManager.resendPassword(resendPasswordRequest)
                .compose(RxUtil.<BaseResponse>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<BaseResponse>(mView) {
                    @Override
                    public void onNext(BaseResponse gankItemBeen) {
                        DLog.d("onNext");
                        mView.onGetPasswordSuccess(gankItemBeen);
                    }
                })
        );

    }

    @Override
    public void activeAccount(String activeCode) {
        ActiveUserRequest activeUserRequest = new ActiveUserRequest();
        ActiveUser user = new ActiveUser();
        user.setUsername(dataManager.getUserName());
        user.setActiveCode(activeCode);
        activeUserRequest.setUserInfo(user);
        activeUserRequest.setServiceName(HttpHelper.ServiceName.ACTIVE_USER);
        activeUserRequest = Utils.setupRequestFormat(activeUserRequest);

        addSubscribe(dataManager.activeUser(activeUserRequest)
                .compose(RxUtil.<ActiveUserResponse>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<ActiveUserResponse>(mView) {
                    @Override
                    public void onNext(ActiveUserResponse gankItemBeen) {
                        DLog.d("onNext");
                        mView.onActiveUserSuccess(gankItemBeen);
                    }
                })
        );

    }

    @Override
    public void onSaveEmail(String email) {
        dataManager.saveEmail(email);
    }

    @Override
    public void onSaveToken(String token) {
        dataManager.setToken(token);
    }
}
