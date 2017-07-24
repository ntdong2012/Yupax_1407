package vsec.com.yupax.ui.screen.home.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import butterknife.BindView;
import vsec.com.yupax.R;
import vsec.com.yupax.base.BaseFragment;
import vsec.com.yupax.base.contract.NewContract;
import vsec.com.yupax.model.http.response.ListNewsResponse;
import vsec.com.yupax.model.http.response.News;
import vsec.com.yupax.presenter.NewsPresenter;
import vsec.com.yupax.ui.screen.home.activity.EventDetailActivity;
import vsec.com.yupax.ui.view.adapter.NewsAdapter;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/13/17.
 */

public class NewsFg extends BaseFragment<NewsPresenter> implements NewContract.View {


    View rootView;
    @BindView(R.id.process)
    ProgressBar progressBar;
    @BindView(R.id.charity_rv)
    RecyclerView newsRv;
    @BindView(R.id.empty_layout)
    RelativeLayout emptyView;
    NewsAdapter newsAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<News> news;

    public NewsFg() {
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.charity_fg;
    }

    @Override
    protected void initEventAndData() {
        news = new ArrayList<>();
        layoutManager = new GridLayoutManager(getActivity(), 2);
        newsRv.setLayoutManager(layoutManager);
        newsAdapter = new NewsAdapter(getActivity(), news, new NewsAdapter.IEventClicked() {
            @Override
            public void onEventClicked(News charity) {
                EventDetailActivity.callEventDetail(getActivity(), new Bundle());
            }
        });

        newsRv.setAdapter(newsAdapter);
        mPresenter.getNews();
    }


    @Override
    public void useLanguage(String language) {

    }

    @Override
    public void onGetNewsSuccess(ListNewsResponse listNewsResponse) {
        onStopLoading();
        if (listNewsResponse != null && listNewsResponse.getError().getCode().contains("200")) {
            news.clear();
            for (int i = 0; i < listNewsResponse.getNewses().size(); i++) {
                news.add(listNewsResponse.getNewses().get(i));
            }
            newsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStopLoading() {
        if (progressBar != null) progressBar.setVisibility(View.GONE);
    }
}
