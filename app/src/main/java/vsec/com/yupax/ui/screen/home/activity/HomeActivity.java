package vsec.com.yupax.ui.screen.home.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import vsec.com.yupax.R;
import vsec.com.yupax.base.BaseActivity;
import vsec.com.yupax.base.contract.HomeContract;
import vsec.com.yupax.model.http.response.City;
import vsec.com.yupax.model.http.response.GetCategoriesResponse;
import vsec.com.yupax.presenter.HomePresenter;
import vsec.com.yupax.ui.screen.home.fragment.ExchangeFg;
import vsec.com.yupax.ui.screen.home.fragment.HomeFg;
import vsec.com.yupax.ui.screen.home.fragment.NotificationFg;
import vsec.com.yupax.ui.screen.home.fragment.PersonalFg;
import vsec.com.yupax.ui.screen.home.fragment.SettingsFg;
import vsec.com.yupax.ui.view.adapter.CityAdapter;
import vsec.com.yupax.ui.view.customview.CircularTextView;
import vsec.com.yupax.ui.view.dialog.RateDialog;
import vsec.com.yupax.utils.Common;

public class HomeActivity extends BaseActivity<HomePresenter> implements HomeContract.View {

    public static void callHomeActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, HomeActivity.class);
        intent.putExtra("home_data", bundle);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    @BindView(R.id.setting_view)
    View settingView;
    @BindView(R.id.notification_view)
    View notificationView;
    @BindView(R.id.address_view)
    View addressView;
    @BindView(R.id.personal_view)
    View personalView;

    @BindView(R.id.search_wrapper)
    RelativeLayout searchWrapperRl;

    @BindView(R.id.title_actionbar)
    TextView titleActionBar;
    @BindView(R.id.search_edt)
    AppCompatEditText searchEdt;
    @BindView(R.id.city_spinner)
    Spinner citySpinner;
    @BindView(R.id.city_layout)
    RelativeLayout cityLayout;
    @BindView(R.id.city_tv)
    TextView cityTv;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.back_icon)
    ImageView backIconIv;


    ArrayList<City> cities;
    CityAdapter cityAdapter;
    FragmentManager fm;

    @BindView(R.id.yupax_imageview_actionbar)
    ImageView yupaxIv;

    ImageView[] controlIcons;
    TextView[] controlLabels;
    int currentPosition;
    CircularTextView numOfNotificationTv;
    @BindView(R.id.floating_icon)
    FloatingActionButton floatingActionButton;


    @Override
    protected int getLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void initEventAndData() {
        renderView();
        initValue();
    }

    @OnClick(R.id.floating_icon)
    void onFloatingActionClick() {
        RateDialog dialog = new RateDialog(this);
        dialog.show();
    }


    @OnClick(R.id.city_tv)
    void onCityClick() {
        citySpinner.performClick();
    }

    void renderView() {
        updateHomeActionbar();
        initHomeControlView();
        initRegionSpinner();
    }


    void initRegionSpinner() {

        cities = new ArrayList<>();
        cityAdapter = new CityAdapter(this, cities);
        citySpinner.setAdapter(cityAdapter);

        cities.add(new City(0, "Hà nội"));
        cities.add(new City(1, "Đà nẵng"));
        cities.add(new City(2, "Sài gòn"));

        cityAdapter.notifyDataSetChanged();
        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("ntdong", "cityName: " + cities.get(i).getCityName());
                cityTv.setText("" + cities.get(i).getCityName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.d("ntdong", "cityName: ");
            }
        });
    }

    void initHomeControlView() {
        controlIcons = new ImageView[4];
        controlLabels = new TextView[4];

        ImageView addressIcon = (ImageView) addressView.findViewById(R.id.control_icon_tv);
        TextView addressLabel = (TextView) addressView.findViewById(R.id.control_label_tv);
        CircularTextView noti = (CircularTextView) addressView.findViewById(R.id.notification_count_tv);
        noti.setVisibility(View.GONE);
        addressLabel.setTextColor(getResources().getColor(R.color.vj_red_color));
        controlIcons[0] = addressIcon;
        controlLabels[0] = addressLabel;

        ImageView notificationIcon = (ImageView) notificationView.findViewById(R.id.control_icon_tv);
        TextView notificationLabel = (TextView) notificationView.findViewById(R.id.control_label_tv);
        controlIcons[1] = notificationIcon;
        controlLabels[1] = notificationLabel;

        ImageView exchangeIcon = (ImageView) personalView.findViewById(R.id.control_icon_tv);
        TextView exchangeLabel = (TextView) personalView.findViewById(R.id.control_label_tv);
        CircularTextView noti2 = (CircularTextView) personalView.findViewById(R.id.notification_count_tv);
        noti2.setVisibility(View.GONE);
        controlIcons[2] = exchangeIcon;
        controlLabels[2] = exchangeLabel;


        ImageView personalIcon = (ImageView) settingView.findViewById(R.id.control_icon_tv);
        TextView personalLabel = (TextView) settingView.findViewById(R.id.control_label_tv);
        CircularTextView noti3 = (CircularTextView) settingView.findViewById(R.id.notification_count_tv);
        noti3.setVisibility(View.GONE);

        controlIcons[3] = personalIcon;
        controlLabels[3] = personalLabel;

        ((TextView) addressView.findViewById(R.id.control_label_tv)).setText(getString(R.string.home_label));

        ((ImageView) settingView.findViewById(R.id.control_icon_tv)).setImageDrawable(getResources().getDrawable(R.drawable.setting_icon_bottombar));
        ((TextView) settingView.findViewById(R.id.control_label_tv)).setText(getString(R.string.setting_label));


        ((ImageView) notificationView.findViewById(R.id.control_icon_tv)).setImageDrawable(getResources().getDrawable(R.drawable.notification_icon_bottombar));
        ((TextView) notificationView.findViewById(R.id.control_label_tv)).setText(getString(R.string.notification_label));
        numOfNotificationTv = (CircularTextView) notificationView.findViewById(R.id.notification_count_tv);
        numOfNotificationTv.setStrokeWidth(1);
        numOfNotificationTv.setStrokeColor("#e51f20");
        numOfNotificationTv.setSolidColor("#e51f20");
        numOfNotificationTv.setVisibility(View.GONE);

        ((ImageView) personalView.findViewById(R.id.control_icon_tv)).setImageDrawable(getResources().getDrawable(R.drawable.user_icon_bottombar));
        ((TextView) personalView.findViewById(R.id.control_label_tv)).setText(getString(R.string.personal_label));
    }

    void initNotificationView(int num, boolean isVisible) {
        numOfNotificationTv.setText("" + num);
        numOfNotificationTv.setVisibility(isVisible ? View.GONE : View.GONE);

    }

    void initValue() {
        fm = getSupportFragmentManager();
        currentPosition = 0;
    }

    @OnClick(R.id.setting_view)
    void onSettingClicked() {
        floatingActionButton.setVisibility(View.GONE);
        updateActionbarForSettingView(getString(R.string.personal_label));
        updateColorForControlView(Common.CONTROL_ICON_POSITION.PERSONAL);
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.home_fg, new SettingsFg());
        ft.commit();
    }

    @OnClick(R.id.personal_view)
    void onPersonalClicked() {
        floatingActionButton.setVisibility(View.GONE);
        updateActionbarForPersonalView(getString(R.string.personal_label));
        updateColorForControlView(Common.CONTROL_ICON_POSITION.EXCHANGE);
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.home_fg, new PersonalFg());
        ft.commit();

    }

    @OnClick(R.id.logo_iv)
    void onHomeClicked() {
        updateFragmentTitleActionbarForLogo();
        floatingActionButton.setVisibility(View.GONE);
//        updateColorForControlView(Common.CONTROL_ICON_POSITION.EXCHANGE);

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.home_fg, new ExchangeFg());
        ft.commit();
    }

    @OnClick(R.id.address_view)
    void onAddressClicked() {
        updateHomeActionbar();
        floatingActionButton.setVisibility(View.VISIBLE);
        updateColorForControlView(Common.CONTROL_ICON_POSITION.ADDRESS);

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.home_fg, new HomeFg());
        ft.commit();

    }

    @OnClick(R.id.notification_view)
    void onNotificationClick() {
        floatingActionButton.setVisibility(View.GONE);
        updateColorForControlView(Common.CONTROL_ICON_POSITION.NOTIFICATION);
        updateFragmentTitleActionbar(getString(R.string.notification_label));
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.home_fg, new NotificationFg());
        ft.commit();
    }

    void updateColorForControlView(int newPosition) {
        if (newPosition == currentPosition) {
            return;
        }
        controlLabels[currentPosition].setTextColor(getResources().getColor(R.color.df_text_color));
        controlLabels[newPosition].setTextColor(getResources().getColor(R.color.vj_red_color));
        switch (newPosition) {
            case Common.CONTROL_ICON_POSITION.ADDRESS:
                controlIcons[newPosition].setImageDrawable(getResources().getDrawable(R.drawable.home_icon_bottombar));
                initNotificationView(3, false);
                break;
            case Common.CONTROL_ICON_POSITION.NOTIFICATION:
                controlIcons[newPosition].setImageDrawable(getResources().getDrawable(R.drawable.notification_icon_bottombar_selected));
                initNotificationView(3, true);
                break;
            case Common.CONTROL_ICON_POSITION.EXCHANGE:
                controlIcons[newPosition].setImageDrawable(getResources().getDrawable(R.drawable.user_icon_bottom_selected));
                initNotificationView(3, false);
                break;
            case Common.CONTROL_ICON_POSITION.PERSONAL:
                controlIcons[newPosition].setImageDrawable(getResources().getDrawable(R.drawable.setting_icon_bottombar_selected));
                initNotificationView(3, false);
                break;

        }
        switch (currentPosition) {
            case Common.CONTROL_ICON_POSITION.ADDRESS:
                controlIcons[currentPosition].setImageDrawable(getResources().getDrawable(R.drawable.home_icon_bottom));
                break;
            case Common.CONTROL_ICON_POSITION.NOTIFICATION:
                controlIcons[currentPosition].setImageDrawable(getResources().getDrawable(R.drawable.notification_icon_bottombar));
                initNotificationView(3, false);
                break;
            case Common.CONTROL_ICON_POSITION.EXCHANGE:
                controlIcons[currentPosition].setImageDrawable(getResources().getDrawable(R.drawable.user_icon_bottombar));
                break;
            case Common.CONTROL_ICON_POSITION.PERSONAL:
                controlIcons[currentPosition].setImageDrawable(getResources().getDrawable(R.drawable.setting_icon_bottombar));
                break;
        }

        currentPosition = newPosition;
    }

    void updateFragmentTitleActionbar(String title) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.bg_new_design));
        }
        toolbar.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        titleActionBar.setVisibility(View.VISIBLE);
        searchWrapperRl.setVisibility(View.GONE);
        searchEdt.setVisibility(View.GONE);
        yupaxIv.setVisibility(View.GONE);
        cityLayout.setVisibility(View.GONE);
        backIconIv.setVisibility(View.GONE);
        titleActionBar.setText(title);
    }

    void updateFragmentTitleActionbarForLogo() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.bg_new_design));
        }
        toolbar.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        titleActionBar.setVisibility(View.GONE);
        searchWrapperRl.setVisibility(View.GONE);
        searchEdt.setVisibility(View.GONE);
        yupaxIv.setVisibility(View.VISIBLE);
        cityLayout.setVisibility(View.GONE);
        backIconIv.setVisibility(View.GONE);
    }

    @OnClick(R.id.back_icon)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    void updateActionbarForPersonalView(String title) {
        titleActionBar.setVisibility(View.VISIBLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.bg_new_design));
        }
        toolbar.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        searchEdt.setVisibility(View.GONE);
        cityLayout.setVisibility(View.GONE);
        yupaxIv.setVisibility(View.GONE);
        backIconIv.setVisibility(View.GONE);
        searchWrapperRl.setVisibility(View.GONE);
        titleActionBar.setText(title);
    }


    void updateActionbarForSettingView(String title) {
        titleActionBar.setVisibility(View.GONE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.bg_new_design));
        }
        toolbar.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        searchEdt.setVisibility(View.GONE);
        cityLayout.setVisibility(View.GONE);
        backIconIv.setVisibility(View.GONE);
        searchWrapperRl.setVisibility(View.GONE);
        yupaxIv.setVisibility(View.GONE);
        titleActionBar.setText(title);
    }

    void updateHomeActionbar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.bg_new_design));
        }
        toolbar.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        titleActionBar.setVisibility(View.GONE);
        searchEdt.setVisibility(View.VISIBLE);
        backIconIv.setVisibility(View.GONE);
        yupaxIv.setVisibility(View.GONE);
        cityLayout.setVisibility(View.VISIBLE);
        searchWrapperRl.setVisibility(View.VISIBLE);
    }

    @Override
    public void useLanguage(String language) {

    }

    @Override
    protected void initInject() {
        getActivityComponent(false).inject(this);
    }



    @Override
    public void onGetCategoriesSuccess(GetCategoriesResponse getCategoriesResponse) {

    }

    @Override
    public void getProvincesSuccess() {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onStopLoading() {

    }
}
