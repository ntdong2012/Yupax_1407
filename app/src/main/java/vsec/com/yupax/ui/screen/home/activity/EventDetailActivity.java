package vsec.com.yupax.ui.screen.home.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import vsec.com.yupax.R;
import vsec.com.yupax.base.BaseActivity;
import vsec.com.yupax.base.contract.EventDetailContract;
import vsec.com.yupax.component.ImageLoader;
import vsec.com.yupax.presenter.EventDetailPresenter;

public class EventDetailActivity extends BaseActivity<EventDetailPresenter> implements EventDetailContract.View {

    public static void callEventDetail(Context context, Bundle bundle) {
        Intent intent = new Intent(context, EventDetailActivity.class);
        intent.putExtra("home_data", bundle);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    private String logoURl;
    private String description;
    @BindView(R.id.event_image)
    ImageView eventImage;
    @BindView(R.id.description_tv)
    TextView descriptionTv;


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
        Intent i = getIntent();
        if (i != null) {
            Bundle b = i.getBundleExtra("home_data");
            logoURl = b.getString("logo");
            description = b.getString("description");
        }

        if (!TextUtils.isEmpty(logoURl)) {
            ImageLoader.load(this, logoURl, eventImage);
        }
        if (!TextUtils.isEmpty(description)) {
            descriptionTv.setText(Html.fromHtml(description));
        }

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
