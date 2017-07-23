package vsec.com.yupax.ui.screen.login.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.OnClick;
import vsec.com.yupax.R;
import vsec.com.yupax.base.BaseActivity;
import vsec.com.yupax.base.contract.SignUpContract;
import vsec.com.yupax.model.http.response.BaseResponse;
import vsec.com.yupax.presenter.SignUpPresenter;
import vsec.com.yupax.ui.view.dialog.NotificationDialog;
import vsec.com.yupax.utils.AnimationUtils;
import vsec.com.yupax.utils.ToastUtils;
import vsec.com.yupax.utils.log.DLog;

public class RegisterActivity extends BaseActivity<SignUpPresenter> implements SignUpContract.View {


    public static void callRegisterActivity(Context context, Bundle bundle) {
        Intent i = new Intent(context, RegisterActivity.class);
        i.putExtras(bundle);
        context.startActivity(i);
        ((Activity) context).overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    @BindView(R.id.user_name_edt)
    AppCompatEditText userNameEdt;
    @BindView(R.id.process)
    ProgressBar progressBar;
    @BindView(R.id.password_edt)
    AppCompatEditText passwordEdt;

    @Override
    protected int getLayout() {
        return R.layout.activity_register_new_design;
    }

    @Override
    protected void initEventAndData() {
        userNameEdt.requestFocus();
        userNameEdt.requestFocusFromTouch();
    }

    @OnClick(R.id.register_btn)
    void onRegisterClicked() {
        String userName = userNameEdt.getText().toString();
        String password = passwordEdt.getText().toString();

        userName = userName.trim();
        if (TextUtils.isEmpty(userName)) {
            AnimationUtils.shake(this, userNameEdt);
            return;
        }
        if (TextUtils.isEmpty(password)) {
            AnimationUtils.shake(this, passwordEdt);
            return;
        }
        onLoading();
        mPresenter.onSignUp(userName, password);
    }

    @OnClick(R.id.back_icon_iv)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void useLanguage(String language) {

    }

    @Override
    public void onSignUpSuccess(BaseResponse baseResponse) {
        DLog.d("onSignUpSuccess");
        onStopLoading();
        if (baseResponse != null && baseResponse.getErrorResponse() != null && baseResponse.getErrorResponse().getCode().contains("200")) {
            DLog.d("create user ok");
            NotificationDialog dialog = new NotificationDialog(this, getString(R.string.notification_resend_message_success) + " " +
                    userNameEdt.getText().toString()
                    , new NotificationDialog.INotificationCloseEvent() {
                @Override
                public void onNotificationClose() {
                    DLog.d("onNotificationClose");
                    Bundle b = new Bundle();
                    b.putString("data", "is_active_code");
                    ForgotPasswordActivity.callForgotPassword(RegisterActivity.this, b);
                    mPresenter.onSaveUserName((userNameEdt.getText().toString()).trim());
                    RegisterActivity.this.finish();
                }
            });
            dialog.show();
        } else {
            ToastUtils.shortShow(baseResponse.getErrorResponse().getMessage());
            DLog.d(baseResponse.getErrorResponse().getMessage());
        }
    }

    @Override
    public void onSignUpError() {

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
    protected void initInject() {
        getActivityComponent(false).inject(this);
    }
}
