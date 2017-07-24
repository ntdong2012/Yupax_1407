package vsec.com.yupax.ui.screen.home.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import butterknife.OnClick;
import vsec.com.yupax.R;
import vsec.com.yupax.base.BaseActivity;
import vsec.com.yupax.base.contract.EventDetailContract;
import vsec.com.yupax.presenter.EventDetailPresenter;

public class EventDetailActivity extends BaseActivity<EventDetailPresenter> implements EventDetailContract.View {

    public static void callEventDetail(Context context, Bundle bundle) {
        Intent intent = new Intent(context, EventDetailActivity.class);
        intent.putExtra("home_data", bundle);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    @Override
    public void useLanguage(String language) {

    }

    @Override
    public void onGetEventDetailSuccess() {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onStopLoading() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_event_detail;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected void initInject() {
        getActivityComponent(false).inject(this);
    }

    @OnClick(R.id.back_icon)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }
}
