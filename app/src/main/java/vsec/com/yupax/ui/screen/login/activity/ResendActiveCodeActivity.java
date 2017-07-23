package vsec.com.yupax.ui.screen.login.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import butterknife.OnClick;
import vsec.com.yupax.R;
import vsec.com.yupax.base.BaseActivity;
import vsec.com.yupax.base.contract.ResendActiveCodeContract;
import vsec.com.yupax.presenter.ResendActiveCodePresenter;
import vsec.com.yupax.ui.view.dialog.NotificationDialog;

public class ResendActiveCodeActivity extends BaseActivity<ResendActiveCodePresenter> implements ResendActiveCodeContract.View {

    public static void callResendActiveCodeActivity(Context context, Bundle bundle) {
        Intent i = new Intent(context, ResendActiveCodeActivity.class);
        i.putExtras(bundle);
        context.startActivity(i);
        ((Activity) context).overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_resend_active_code;
    }


    @OnClick(R.id.resend_active_code_btn)
    void onResendActiveCodeClick() {
        NotificationDialog notificationDialog = new NotificationDialog(this, getString(R.string.notification_resend_message_success), new NotificationDialog.INotificationCloseEvent() {
            @Override
            public void onNotificationClose() {

            }
        });
        notificationDialog.show();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    @OnClick(R.id.back_tv)
    void onClick() {
        onBackPressed();
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void useLanguage(String language) {

    }

    @Override
    public void onResendOtpSuccess() {

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
