package vsec.com.yupax.ui.screen.login.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import butterknife.OnClick;
import vsec.com.yupax.R;
import vsec.com.yupax.base.BaseActivity;
import vsec.com.yupax.base.contract.ForgotPasswordContract;
import vsec.com.yupax.presenter.ForgotPasswordPresenter;
import vsec.com.yupax.ui.view.dialog.NotificationDialog;

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
        NotificationDialog dialog = new NotificationDialog(this, getString(R.string.notification_resend_message_success));
        dialog.show();
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void useLanguage(String language) {

    }

    @Override
    public void onGetPasswordSuccess() {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onStopLoading() {

    }

    @Override
    protected void initInject() {

    }
}
