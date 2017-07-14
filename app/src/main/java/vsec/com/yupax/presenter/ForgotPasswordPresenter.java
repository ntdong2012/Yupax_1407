package vsec.com.yupax.presenter;

import javax.inject.Inject;

import vsec.com.yupax.base.RxPresenter;
import vsec.com.yupax.base.contract.ForgotPasswordContract;
import vsec.com.yupax.model.DataManager;
import vsec.com.yupax.model.http.HttpHelper;
import vsec.com.yupax.model.http.request.ResendPasswordRequest;
import vsec.com.yupax.model.http.request.UserInfoForResendPassword;
import vsec.com.yupax.model.http.response.Token;
import vsec.com.yupax.utils.CommonSubscriber;
import vsec.com.yupax.utils.RxUtil;
import vsec.com.yupax.utils.Utils;

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
        resendPasswordRequest.setServiceName(HttpHelper.ServiceName.RESEND_PASSWORD);

        UserInfoForResendPassword userInfo = new UserInfoForResendPassword();
        userInfo.setUsername(userName);
        resendPasswordRequest.setUserInfo(userInfo);

        addSubscribe(dataManager.resendPassword(resendPasswordRequest)
                .compose(RxUtil.<Token>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<Token>(mView) {
                    @Override
                    public void onNext(Token gankItemBeen) {
                        mView.onGetPasswordSuccess();
                    }
                })
        );

    }
}
