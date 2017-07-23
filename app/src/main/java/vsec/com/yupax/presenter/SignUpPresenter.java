package vsec.com.yupax.presenter;

import javax.inject.Inject;

import vsec.com.yupax.base.RxPresenter;
import vsec.com.yupax.base.contract.SignUpContract;
import vsec.com.yupax.model.DataManager;
import vsec.com.yupax.model.http.HttpHelper;
import vsec.com.yupax.model.http.request.CreateUser;
import vsec.com.yupax.model.http.request.CreateUserRequest;
import vsec.com.yupax.model.http.response.BaseResponse;
import vsec.com.yupax.utils.CommonSubscriber;
import vsec.com.yupax.utils.RxUtil;
import vsec.com.yupax.utils.Utils;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/25/17.
 */

public class SignUpPresenter extends RxPresenter<SignUpContract.View> implements SignUpContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public SignUpPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void onSignUp(String userName, String password) {


        CreateUser cr = new CreateUser();
        cr.setUsername(userName);
        cr.setPassword(password);
        cr.setPasswordConfirm(password);
        CreateUserRequest cur = new CreateUserRequest();
        cur.setUserInfo(cr);
        cur = Utils.setupRequestFormat(cur);
        cur.setServiceName(HttpHelper.ServiceName.CREATE_USER);

        addSubscribe(mDataManager.registerUser(cur)
                .compose(RxUtil.<BaseResponse>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<BaseResponse>(mView) {
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        mView.onSignUpSuccess(baseResponse);
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
    public void onSaveUserName(String userName) {
        mDataManager.setUserName(userName);
    }
}
