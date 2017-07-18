package vsec.com.yupax.ui.screen.login.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import vsec.com.yupax.R;
import vsec.com.yupax.base.BaseActivity;
import vsec.com.yupax.base.contract.SignInContract;
import vsec.com.yupax.model.http.response.LoginResponse;
import vsec.com.yupax.presenter.SignInPresenter;
import vsec.com.yupax.utils.AnimationUtils;
import vsec.com.yupax.utils.Utils;
import vsec.com.yupax.utils.log.DLog;

public class SignInActivity extends BaseActivity<SignInPresenter> implements SignInContract.View {


    @BindView(R.id.register_tv)
    TextView registerLabelTv;
    @BindView(R.id.user_name_edt)
    AppCompatEditText userNameEdt;
    @BindView(R.id.password_edt)
    AppCompatEditText passwordEdt;
    @BindView(R.id.process)
    ProgressBar progressBar;


    public static void callSignInActivity(Context context, Bundle bundle) {
        Intent i = new Intent(context, SignInActivity.class);
        i.putExtras(bundle);
        context.startActivity(i);
        ((Activity) context).overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    @OnClick(R.id.register_tv)
    void onCallRegisterAction() {
        RegisterActivity.callRegisterActivity(this, new Bundle());
    }

    @OnClick(R.id.login_btn)
    void onCallLoginAction() {
        String userName = userNameEdt.getText().toString();
        String passWord = passwordEdt.getText().toString();


        if (TextUtils.isEmpty(userName)) {
            AnimationUtils.shake(this, userNameEdt);
            return;
        }

        if (TextUtils.isEmpty(passWord)) {
            AnimationUtils.shake(this, passwordEdt);
            return;
        }

        onLoading();
        mPresenter.onSignIn(userName, passWord);
    }

    @OnEditorAction(R.id.password_edt)
    boolean onPasswordEditorAction() {
        Utils.hiddenSoftKeyboard(this, passwordEdt);
        onCallLoginAction();
        return true;
    }

    @OnClick(R.id.forgot_pass_tv)
    void onCallForgotPasswordAction() {
        ForgotPasswordActivity.callForgotPassword(this, new Bundle());
    }

    @Override
    public void useLanguage(String language) {

    }

    @Override
    public void onSignInSuccess(LoginResponse loginResponse) {
        onStopLoading();
        DLog.d("onSignInSuccess" + loginResponse.getUserInfo().getEmail());
        mPresenter.onSaveUserInfo(loginResponse.getUserInfo());
        this.finish();
        MerchantActivity.callMerchantActivity(this, new Bundle());
    }



    @Override
    public void onSignInError() {

    }

    @Override
    public void onLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStopLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_sign_in;
    }

    @Override
    protected void initEventAndData() {
        String one = getString(R.string.dont_have_account);
        String two = getString(R.string.register_label);
        registerLabelTv.setText(Html.fromHtml(one + " &nbsp;&nbsp;&nbsp;  " + "<font color='#ffffff'><b>" + two + "</b></font>"));
        userNameEdt.requestFocus();
        userNameEdt.requestFocusFromTouch();


    }

    @Override
    protected void initInject() {
        getActivityComponent(false).inject(this);
    }
}
