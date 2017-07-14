package vsec.com.yupax.ui.screen.login.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;

import butterknife.BindView;
import vsec.com.yupax.R;
import vsec.com.yupax.base.BaseActivity;
import vsec.com.yupax.base.contract.SignUpContract;
import vsec.com.yupax.presenter.SignUpPresenter;

public class RegisterActivity extends BaseActivity<SignUpPresenter> implements SignUpContract.View {


    public static void callRegisterActivity(Context context, Bundle bundle) {
        Intent i = new Intent(context, RegisterActivity.class);
        i.putExtras(bundle);
        context.startActivity(i);
        ((Activity) context).overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    @BindView(R.id.user_name_edt)
    AppCompatEditText userNameEdt;

    @Override
    protected int getLayout() {
        return R.layout.activity_register_new_design;
    }

    @Override
    protected void initEventAndData() {
        userNameEdt.requestFocus();
        userNameEdt.requestFocusFromTouch();
    }

    @Override
    public void useLanguage(String language) {

    }

    @Override
    public void onSignUpSuccess() {

    }

    @Override
    public void onSignUpError() {

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
