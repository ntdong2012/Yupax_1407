package vsec.com.yupax.ui.screen.home.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import vsec.com.yupax.R;
import vsec.com.yupax.base.BaseFragment;
import vsec.com.yupax.base.contract.PromotionContract;
import vsec.com.yupax.model.http.response.GetPromotionsResponse;
import vsec.com.yupax.model.http.response.Promotion;
import vsec.com.yupax.presenter.PromotionPresenter;
import vsec.com.yupax.ui.screen.home.activity.EventDetailActivity;
import vsec.com.yupax.ui.screen.login.activity.SignInActivity;
import vsec.com.yupax.ui.view.adapter.PromotionAdapter;
import vsec.com.yupax.utils.ToastUtils;
import vsec.com.yupax.utils.log.DLog;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/13/17.
 */

public class PromotionFg extends BaseFragment<PromotionPresenter> implements PromotionContract.View {

    public PromotionFg() {
    }

    View rootView;
    @BindView(R.id.process)
    ProgressBar progressBar;
    @BindView(R.id.gift_recycleview)
    RecyclerView giftRecycleView;
    RecyclerView.LayoutManager mLayoutManager;
    @BindView(R.id.sort_spinner)
    Spinner sortSpinner;
    ArrayList<String> sortConditions;
    @BindView(R.id.sort_tv)
    TextView sortTv;
    PromotionAdapter promotionAdapter;
    ArrayList<Promotion> promotions;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.promotion_fg;
    }

    @Override
    protected void initEventAndData() {
        initSortView();

        initGiftView();

        mPresenter.getPromotions();
    }

    void initGiftView() {
        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        giftRecycleView.setLayoutManager(mLayoutManager);
        promotions = new ArrayList<>();
        promotionAdapter = new PromotionAdapter(getActivity(), promotions, new PromotionAdapter.IEventClick() {
            @Override
            public void onEventClick(Promotion promotion) {
                Bundle b = new Bundle();
                b.putString("logo", promotion.getImages());
                b.putString("description", promotion.getDescription());
                EventDetailActivity.callEventDetail(getActivity(), b);
            }
        });
        giftRecycleView.setAdapter(promotionAdapter);
    }

    void initSortView() {
        sortConditions = new ArrayList<>();
        sortConditions.add("Tất cả");
        sortConditions.add("Nổi bật");
        ArrayAdapter<String> sortAdapter = new ArrayAdapter<String>(getActivity(), R.layout.city_item_actionbar, sortConditions);
        sortSpinner.setAdapter(sortAdapter);
        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sortTv.setText("" + sortConditions.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @OnClick(R.id.sort_tv)
    void onSortClick() {
        sortSpinner.performClick();
    }


    @Override
    public void useLanguage(String language) {

    }

    @Override
    public void onGetPromotionsSuccess(GetPromotionsResponse getPromotionsResponse) {
        DLog.d("onGetPromotionsSuccess" + getPromotionsResponse.getErrorResponse().getMessage());

        if (getPromotionsResponse.getErrorResponse().getCode().contains("200")) {
            promotions.clear();
            for (int i = 0; i < getPromotionsResponse.getPromotions().size(); i++) {
                promotions.add(getPromotionsResponse.getPromotions().get(i));
            }
            promotionAdapter.notifyDataSetChanged();
        } else {
            ToastUtils.shortShow(getPromotionsResponse.getErrorResponse().getMessage());
            DLog.d(getPromotionsResponse.getErrorResponse().getMessage());
            if (getPromotionsResponse.getErrorResponse().getCode().contains("203")) {
                SignInActivity.callSignInActivity(getActivity(), new Bundle());
                mPresenter.saveToken("");
                getActivity().finish();
                return;
            }
        }
        onStopLoading();
    }

    @Override
    public void onLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStopLoading() {
        if (progressBar != null)
            progressBar.setVisibility(View.GONE);
    }
}
