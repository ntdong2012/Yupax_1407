package vsec.com.yupax.ui.activity.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.text.Html;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import vsec.com.yupax.R;
import vsec.com.yupax.base.BaseActivity;
import vsec.com.yupax.base.contract.SignInContract;
import vsec.com.yupax.model.http.response.LoginResponse;
import vsec.com.yupax.presenter.SignInPresenter;
import vsec.com.yupax.ui.activity.home.HomeActivity;

public class SignInActivity extends BaseActivity<SignInPresenter> implements SignInContract.View {


    @BindView(R.id.register_tv)
    TextView registerLabelTv;
    @BindView(R.id.user_name_edt)
    AppCompatEditText userNameEdt;
    @BindView(R.id.password_edt)
    AppCompatEditText passwordEdt;


    public static void callSignInActivity(Context context, Bundle bundle) {
        Intent i = new Intent(context, SignInActivity.class);
        i.putExtras(bundle);
        context.startActivity(i);
        ((Activity) context).overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    @OnClick(R.id.register_tv)
    void onCallRegisterAction() {
        RegisterActivity.callRegisterActivity(this, new Bundle());
        this.finish();
    }

    @OnClick(R.id.login_btn)
    void onCallLoginAction() {
        HomeActivity.callHomeActivity(this, new Bundle());
        this.finish();
    }

    @OnClick(R.id.forgot_pass_tv)
    void onCallForgotPasswordAction() {
        ForgotPasswordActivity.callForgotPassword(this, new Bundle());
        this.finish();
    }

    @Override
    public void useLanguage(String language) {

    }

    @Override
    public void onSignInSuccess(LoginResponse loginResponse) {

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
