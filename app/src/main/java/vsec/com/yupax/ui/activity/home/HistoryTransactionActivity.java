package vsec.com.yupax.ui.activity.home;

import android.app.Activity;
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
import butterknife.OnClick;
import vsec.com.yupax.R;
import vsec.com.yupax.app.YupaxApps;
import vsec.com.yupax.base.SimpleActivity;

public class HistoryTransactionActivity extends SimpleActivity {

    public static void callHistoryTransactionActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, HistoryTransactionActivity.class);
        intent.putExtra("home_data", bundle);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.right_in, R.anim.left_out);

    }

    @BindView(R.id.back_tv)
    TextView backTv;
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    private ViewPagerAdapter fgViewPaperAdapter;


    @Override
    protected int getLayout() {
        return R.layout.activity_history_transaction;
    }

    @Override
    protected void initEventAndData() {
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        backTv.setTypeface(YupaxApps.getFontAwesomeTf());
    }

    @OnClick(R.id.back_tv)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    private void setupViewPager(ViewPager viewPager) {
        fgViewPaperAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        TransactionFg signInFragment = new TransactionFg();
        TransactionFg signUpFragment = new TransactionFg();
        fgViewPaperAdapter.addFragment(signInFragment, getResources().getString(R.string.transaction_tab_one_label));
        fgViewPaperAdapter.addFragment(signUpFragment, getResources().getString(R.string.transaction_tab_two_label));
        viewPager.setAdapter(fgViewPaperAdapter);

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
                TransactionFg fragment = (TransactionFg) super.instantiateItem(container,
                        position);
                container.setTag("myView" + position);
                return fragment;
            } else if (position == 1) {
                TransactionFg fragment = (TransactionFg) super.instantiateItem(container,
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
}
