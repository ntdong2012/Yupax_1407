package vsec.com.yupax.ui.screen.login.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import vsec.com.yupax.R;
import vsec.com.yupax.base.BaseActivity;
import vsec.com.yupax.base.contract.ForgotPasswordContract;
import vsec.com.yupax.model.http.response.ActiveUserResponse;
import vsec.com.yupax.model.http.response.BaseResponse;
import vsec.com.yupax.presenter.ForgotPasswordPresenter;
import vsec.com.yupax.ui.view.dialog.NotificationDialog;
import vsec.com.yupax.utils.AnimationUtils;
import vsec.com.yupax.utils.ToastUtils;
import vsec.com.yupax.utils.log.DLog;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/26/2017.
 */

public class ForgotPasswordActivity extends BaseActivity<ForgotPasswordPresenter> implements ForgotPasswordContract.View {


    public static void callForgotPassword(Context context, Bundle bundle) {
        Intent i = new Intent(context, ForgotPasswordActivity.class);
        i.putExtras(bundle);
        context.startActivity(i);
        ((Activity) context).overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    @BindView(R.id.email_edt)
    AppCompatEditText emailEdt;
    @BindView(R.id.forgot_password_guide_tv)
    TextView activityGuideTv;
    @BindView(R.id.forgot_password_btn)
    AppCompatButton forgotPassBtn;

    boolean isActiveCode;

    @OnClick(R.id.back_icon_iv)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_forgot_password;
    }

    @OnClick(R.id.forgot_password_btn)
    void onHandleForgotPasswordAction() {

        String email = emailEdt.getText().toString();
        email = email.trim();
        if (TextUtils.isEmpty(email)) {
            AnimationUtils.shake(this, emailEdt);
            return;
        }

        if (!isActiveCode) {
            mPresenter.onResendPassword(email);
        } else {
            mPresenter.activeAccount(email);
        }
    }

    @Override
    protected void initEventAndData() {

        Intent i = getIntent();
        if (i != null) {
            Bundle b = i.getExtras();
            String isFrom = b.getString("data");
            DLog.d("From: " + isFrom);
            if (!TextUtils.isEmpty(isFrom) && isFrom.contains("is_active_code")) {
                isActiveCode = true;
            }
        }

        if (isActiveCode) {
            activityGuideTv.setText(getString(R.string.active_code_guide));
            emailEdt.setHint(getString(R.string.active_code_hint));
            forgotPassBtn.setText(getString(R.string.active_account_label));
        }

    }


    @Override
    public void useLanguage(String language) {

    }

    @Override
    public void onGetPasswordSuccess(BaseResponse baseResponse) {
        DLog.d("onGetPasswordSuccess" + baseResponse.getErrorResponse().getMessage());
        NotificationDialog dialog = new NotificationDialog(this, getString(R.string.notification_resend_message_success) + emailEdt.getText().toString()
                , new NotificationDialog.INotificationCloseEvent() {
            @Override
            public void onNotificationClose() {
                DLog.d("onNotificationClose");
                mPresenter.onSaveToken("");
                SignInActivity.callSignInActivity(ForgotPasswordActivity.this, new Bundle());
                ForgotPasswordActivity.this.finish();
            }
        });
        dialog.show();
    }

    @Override
    public void onActiveUserSuccess(ActiveUserResponse baseResponse) {
        DLog.d("onActiveUserSuccess");
        if (baseResponse != null && baseResponse.getErrorResponse() != null && baseResponse.getErrorResponse().getCode().contains("200")) {

            String email = baseResponse.getActiveUser().getEmail();
            String token = baseResponse.getActiveUser().getToken();

            mPresenter.onSaveEmail(email);
            mPresenter.onSaveToken(token);
            MerchantActivity.callMerchantActivity(this, new Bundle());
            finish();
        } else {
            ToastUtils.shortShow(baseResponse.getErrorResponse().getMessage());
            DLog.d(baseResponse.getErrorResponse().getMessage());
        }
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onStopLoading() {

    }

    @Override
    protected void initInject() {
        getActivityComponent(false).inject(this);
    }
}
