package vsec.com.yupax.ui.screen.login.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import vsec.com.yupax.R;
import vsec.com.yupax.app.YupaxApps;
import vsec.com.yupax.base.SimpleActivity;
import vsec.com.yupax.ui.screen.login.fragment.SignInFg;
import vsec.com.yupax.ui.screen.login.fragment.SignUpFg;
import vsec.com.yupax.utils.Common;
import vsec.com.yupax.utils.FontUtils;
import vsec.com.yupax.utils.PerUtils;

public class LoginActivity extends SimpleActivity {

    @BindView(R.id.back_tv)
    TextView backTv;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpaper)
    ViewPager viewPager;
    @BindView(R.id.actionbar_title_tv)
    TextView actionbarTitleTv;
    @BindView(R.id.language_tv)
    TextView languageTv;
    ViewPagerAdapter fgViewPaperAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (verifyPermission()) {
            renderView();
        }
    }

    public static void callLoginActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra("home_data", bundle);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.right_in, R.anim.left_out);

    }

    void setupViewPaper() {

        fgViewPaperAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        SignInFg signInFragment = new SignInFg();
        SignUpFg signUpFragment = new SignUpFg();

        fgViewPaperAdapter.addFragment(signInFragment, getResources().getString(R.string.sign_in));
        fgViewPaperAdapter.addFragment(signUpFragment, getResources().getString(R.string.sign_up));

        viewPager.setAdapter(fgViewPaperAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment currentFg = mFragmentList.get(position);

            return currentFg;
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    @OnClick(R.id.language_tv)
    void onLanguageSelected() {
        LanguageActivity.callLanguageActivity(this, new Bundle());

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initEventAndData() {
        renderView();
        setupViewPaper();
    }

    void renderView() {
        backTv.setTypeface(YupaxApps.getFontAwesomeTf());
        actionbarTitleTv.setTypeface(FontUtils.getInstance(this).boldFont);
        languageTv.setTypeface(FontUtils.getInstance(this).lightFont);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean isGranted = true;
        if (requestCode == Common.REQUEST_CODE_ASK_PERMISSIONS) {
            for (int i = 0; i < grantResults.length; i++) {
                if (!(grantResults[i] == PackageManager.PERMISSION_GRANTED)) {
                    isGranted = false;
                }
            }
            if (isGranted) {
                renderView();
            }
        }
    }


    @TargetApi(Build.VERSION_CODES.M)
    private boolean verifyPermission() {
        List<String> permissionNeeded = new ArrayList<String>();
        if (!PerUtils.hasAccessCoarseLocationPermission(this) && !PerUtils.isNeverAskAgainWithAccessCoarseLocationPermission(this)) {
            permissionNeeded.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        if (!PerUtils.hasAccessFineLocationPermission(this) && !PerUtils.isNeverAskAgainWithAccessFineLocationPermission(this)) {
            permissionNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }

        if (permissionNeeded.size() > 0) {
            this.requestPermissions(permissionNeeded.toArray(new String[permissionNeeded.size()]), Common.REQUEST_CODE_ASK_PERMISSIONS);
            return false;
        }
        return true;
    }
}
