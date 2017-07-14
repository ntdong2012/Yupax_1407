package vsec.com.yupax.ui.screen.home.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import vsec.com.yupax.R;
import vsec.com.yupax.base.SimpleFragment;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/12/2017.
 */

public class ExchangeFg extends SimpleFragment {

    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    private ViewPagerAdapter fgViewPaperAdapter;

    public ExchangeFg() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.exchange_fg;
    }

    @Override
    protected void initEventAndData() {
        setupViewPager(viewPager);
        viewPager.addOnPageChangeListener(mOnPageChangeListener);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        fgViewPaperAdapter = new ViewPagerAdapter(getChildFragmentManager());
        NowEventFg signInFragment = new NowEventFg();
        OnGoingEventFg signUpFragment = new OnGoingEventFg();
        fgViewPaperAdapter.addFragment(signInFragment, getResources().getString(R.string.voucher_label));
        fgViewPaperAdapter.addFragment(signUpFragment, getResources().getString(R.string.event_label));
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
                NowEventFg fragment = (NowEventFg) super.instantiateItem(container,
                        position);
                container.setTag("myView" + position);
                return fragment;
            } else if (position == 1) {
                OnGoingEventFg fragment = (OnGoingEventFg) super.instantiateItem(container,
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
