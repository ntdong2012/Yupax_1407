package vsec.com.yupax.ui.screen.home.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import vsec.com.yupax.R;
import vsec.com.yupax.app.YupaxApps;
import vsec.com.yupax.base.BaseActivity;
import vsec.com.yupax.base.contract.ChangePasswordContract;
import vsec.com.yupax.model.http.response.ChangePasswordResponse;
import vsec.com.yupax.presenter.ChangePasswordPresenter;
import vsec.com.yupax.ui.screen.login.activity.SignInActivity;
import vsec.com.yupax.ui.view.dialog.NotificationDialog;
import vsec.com.yupax.utils.AnimationUtils;
import vsec.com.yupax.utils.ToastUtils;
import vsec.com.yupax.utils.log.DLog;

public class ChangePasswordActivity extends BaseActivity<ChangePasswordPresenter> implements ChangePasswordContract.View {

    public static void callChangePasswordActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, ChangePasswordActivity.class);
        intent.putExtra("home_data", bundle);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.right_in, R.anim.left_out);

    }

    @BindView(R.id.current_password_edt)
    EditText currentPassword;
    @BindView(R.id.new_password_edt)
    EditText newPassEdt;
    @BindView(R.id.re_new_password_edt)
    EditText reNewPassEdt;
    @BindView(R.id.process)
    ProgressBar progressBar;

    @OnClick(R.id.change_password_btn)
    void onChangePassword() {

        String pass = currentPassword.getText().toString().trim();
        String newPas = newPassEdt.getText().toString().trim();
        String reNewPas = reNewPassEdt.getText().toString().trim();

        if (TextUtils.isEmpty(pass)) {
            AnimationUtils.shake(this, currentPassword);
            return;
        }
        if (TextUtils.isEmpty(newPas)) {
            AnimationUtils.shake(this, newPassEdt);
            return;
        }
        if (TextUtils.isEmpty(reNewPas)) {
            AnimationUtils.shake(this, reNewPassEdt);
            return;
        }
        if (!newPas.equals(reNewPas)) {
            AnimationUtils.shake(this, reNewPassEdt);
            return;
        }

        onLoading();
        mPresenter.onChangePassword(pass, reNewPas);

    }


    @BindView(R.id.back_tv)
    TextView backTv;


    @Override
    protected int getLayout() {
        return R.layout.activity_change_password;
    }

    @Override
    protected void initEventAndData() {
        backTv.setTypeface(YupaxApps.getFontAwesomeTf());
    }

    @OnClick(R.id.back_tv)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    @Override
    public void useLanguage(String language) {

    }

    @Override
    public void onChangePasswordSuccess(ChangePasswordResponse changePasswordResponse) {
        if (changePasswordResponse != null && changePasswordResponse.getError() != null &&
                !TextUtils.isEmpty(changePasswordResponse.getError().getCode()) &&
                changePasswordResponse.getError().getCode().contains("200")) {
            NotificationDialog dialog = new NotificationDialog(this, getString(R.string.change_pass_success)
                    , new NotificationDialog.INotificationCloseEvent() {
                @Override
                public void onNotificationClose() {
                    DLog.d("onNotificationClose");
                    mPresenter.setToken("");
                    SignInActivity.callSignInActivity(ChangePasswordActivity.this, new Bundle());
                    ChangePasswordActivity.this.finish();
                }
            });
            dialog.show();
        } else {
            ToastUtils.shortShow(changePasswordResponse.getError().getMessage());
            DLog.d(changePasswordResponse.getError().getMessage());
        }
        onStopLoading();
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
