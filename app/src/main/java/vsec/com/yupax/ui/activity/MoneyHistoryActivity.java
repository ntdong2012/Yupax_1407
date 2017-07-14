package vsec.com.yupax.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vsec.com.yupax.R;
import vsec.com.yupax.base.BaseActivity;
import vsec.com.yupax.base.contract.MoneyContract;
import vsec.com.yupax.fragment.IncomeFg;
import vsec.com.yupax.fragment.OutcomeFg;
import vsec.com.yupax.app.YupaxApps;
import vsec.com.yupax.presenter.MoneyPresenter;

public class MoneyHistoryActivity extends BaseActivity<MoneyPresenter> implements MoneyContract.View {

    @BindView(R.id.back_tv)
    TextView backTv;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    ViewPagerAdapter fgViewPaperAdapter;


    public static void callMoneyHistoryActivity(Context context) {
        Intent i = new Intent(context, MoneyHistoryActivity.class);
        context.startActivity(i);

    }


    @Override
    protected int getLayout() {
        return R.layout.activity_money_history;
    }

    @Override
    protected void initEventAndData() {
        renderView();
    }

    void renderView() {
        backTv.setTypeface(YupaxApps.getFontAwesomeTf());
        setupViewPager(viewpager);
        viewpager.addOnPageChangeListener(mOnPageChangeListener);
        tabLayout.setupWithViewPager(viewpager);

    }


    private void setupViewPager(ViewPager viewPager) {
        fgViewPaperAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        IncomeFg signInFragment = new IncomeFg();
        OutcomeFg signUpFragment = new OutcomeFg();
        //signInFragment.
        fgViewPaperAdapter.addFragment(signInFragment, getResources().getString(R.string.income_label));
        fgViewPaperAdapter.addFragment(signUpFragment, getResources().getString(R.string.outcome_label));
        viewPager.setAdapter(fgViewPaperAdapter);

    }

    @Override
    public void useLanguage(String language) {

    }

    @Override
    public void onGetCatalogSuccess() {

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


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            if (position == 0) {
                IncomeFg fragment = (IncomeFg) super.instantiateItem(container,
                        position);
                container.setTag("myView" + position);
                return fragment;
            } else if (position == 1) {
                OutcomeFg fragment = (OutcomeFg) super.instantiateItem(container,
                        position);
                container.setTag("myView" + position);
                return fragment;

            }
            return null;
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


    @OnClick(R.id.back_tv)
    void onBackBtnClick() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }
}

