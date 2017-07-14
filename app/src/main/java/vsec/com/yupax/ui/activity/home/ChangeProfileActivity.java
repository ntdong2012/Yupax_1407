package vsec.com.yupax.ui.activity.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import butterknife.OnClick;
import vsec.com.yupax.R;
import vsec.com.yupax.base.BaseActivity;
import vsec.com.yupax.base.contract.ChangeProfileContract;
import vsec.com.yupax.presenter.ChangeProfilePresenter;

public class ChangeProfileActivity extends BaseActivity<ChangeProfilePresenter> implements ChangeProfileContract.View {


    public static void callChangeProfileActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, ChangeProfileActivity.class);
        intent.putExtra("home_data", bundle);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.right_in, R.anim.left_out);

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_change_profile;
    }

    @OnClick(R.id.back_tv)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }


    @Override
    protected void initEventAndData() {

    }

    @Override
    public void useLanguage(String language) {

    }

    @Override
    public void onChangeProfileSuccess() {

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
