package vsec.com.yupax.ui.screen.login.fragment;

import android.os.Bundle;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import vsec.com.yupax.R;
import vsec.com.yupax.base.BaseFragment;
import vsec.com.yupax.base.contract.SignInContract;
import vsec.com.yupax.model.http.request.LoginResponseNew;
import vsec.com.yupax.presenter.SignInPresenter;
import vsec.com.yupax.ui.screen.home.activity.HomeActivity;
import vsec.com.yupax.ui.screen.login.activity.ForgotPasswordActivity;
import vsec.com.yupax.ui.screen.login.activity.ResendActiveCodeActivity;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/25/17.
 */

public class SignInFg extends BaseFragment<SignInPresenter> implements SignInContract.View {


    @BindView(R.id.user_name_edt)
    EditText userNameEdt;
    @BindView(R.id.password_edt)
    EditText passwordEdt;

    @OnClick(R.id.forgot_pass_tv)
    void onForgotPassword() {
        ForgotPasswordActivity.callForgotPassword(getActivity(), new Bundle());
    }

    @OnClick(R.id.login_btn)
    void onLogin() {
        String userName = userNameEdt.getText().toString();
        String password = passwordEdt.getText().toString();

//        if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password)) {
//            mPresenter.onSignIn(userName, password);
//        } else if (TextUtils.isEmpty(userName)) {
//            AnimationUtils.shake(getActivity(), userNameEdt);
//        } else if (TextUtils.isEmpty(password)) {
//            AnimationUtils.shake(getActivity(), passwordEdt);
//        }
//        userName = "fokbpecz";
//        password = "685502";
//        mPresenter.onSignIn(userName, password);

        HomeActivity.callHomeActivity(getActivity(), new Bundle());
        getActivity().finish();
    }

    public SignInFg() {
    }


    @OnClick(R.id.resend_active_code_tv)
    void onResendActiveCode() {
        ResendActiveCodeActivity.callResendActiveCodeActivity(getActivity(), new Bundle());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.sign_in;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void useLanguage(String language) {

    }

    @Override
    public void onSignInSuccess(LoginResponseNew loginResponse) {
//        DLog.d("onSignInSuccess");
//        try {
//            if (!TextUtils.isEmpty(loginResponse.getError().getCode()) && Integer.parseInt(loginResponse.getError().getCode())
//                    == Common.HTTP_CODE.CODE_OK) {
//                DLog.d("Token : " + loginResponse.getUserInfo().getToken());
//                mPresenter.onSavedToken(loginResponse.getUserInfo().getToken());
//                HomeActivity.callHomeActivity(getActivity(), new Bundle());
//                getActivity().finish();
//            }
//        } catch (Exception ex) {
//            AnimationUtils.shake(getActivity(), userNameEdt);
//            ex.printStackTrace();
//        }

    }

    @Override
    public void onSignInError() {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onStopLoading() {

    }
}
