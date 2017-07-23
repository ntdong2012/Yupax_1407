package vsec.com.yupax.presenter;

import javax.inject.Inject;

import vsec.com.yupax.base.RxPresenter;
import vsec.com.yupax.base.contract.SignInContract;
import vsec.com.yupax.model.DataManager;
import vsec.com.yupax.model.http.HttpHelper;
import vsec.com.yupax.model.http.request.LoginRequest;
import vsec.com.yupax.model.http.request.UserInfoForLogin;
import vsec.com.yupax.model.http.response.LoginResponse;
import vsec.com.yupax.model.http.response.UserInfoResponse;
import vsec.com.yupax.utils.CommonSubscriber;
import vsec.com.yupax.utils.RxUtil;
import vsec.com.yupax.utils.Utils;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/25/17.
 */

public class SignInPresenter extends RxPresenter<SignInContract.View> implements SignInContract.Presenter {

    private DataManager dataManager;

    @Inject
    public SignInPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }


    @Override
    public void onSignIn(String userName, String password) {

        LoginRequest loginRequest = new LoginRequest();

        loginRequest = Utils.setupRequestFormat(loginRequest);

        loginRequest.setServiceName(HttpHelper.ServiceName.LOGIN);

        UserInfoForLogin userInfo = new UserInfoForLogin();
        userInfo.setUsername(userName);
        userInfo.setPassword(password);
        loginRequest.setUserInfo(userInfo);

        addSubscribe(dataManager.signIn(loginRequest)
                .compose(RxUtil.<LoginResponse>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<LoginResponse>(mView) {
                    @Override
                    public void onNext(LoginResponse loginResponse) {
                        mView.onSignInSuccess(loginResponse);
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
    public void onSavedToken(String token) {
        dataManager.setToken(token);
    }

    @Override
    public void onSaveUserInfo(UserInfoResponse userInfoResponse) {
        dataManager.onSaveUserInfo(userInfoResponse);
    }

    @Override
    public String getUserName() {
        return dataManager.getUserName();
    }

    @Override
    public void onSaveLoginState(boolean isSaved) {
        dataManager.saveLoginState(isSaved);
    }

    @Override
    public String getUserEmail() {
        return dataManager.getEmail();
    }

    @Override
    public boolean getSaveLoginState() {
        return dataManager.getSaveLoginState();
    }


}
