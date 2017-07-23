package vsec.com.yupax.ui.screen.home.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

import butterknife.BindView;
import vsec.com.yupax.R;
import vsec.com.yupax.base.BaseFragment;
import vsec.com.yupax.base.contract.NotificationContract;
import vsec.com.yupax.model.http.response.ListNewsResponse;
import vsec.com.yupax.model.http.response.News;
import vsec.com.yupax.presenter.NotificationPresenter;
import vsec.com.yupax.ui.view.adapter.NotificationAdapter;
import vsec.com.yupax.ui.view.dialog.NotificationDialog;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/12/2017.
 */

public class NotificationFg extends BaseFragment<NotificationPresenter> implements NotificationContract.View {

    public NotificationFg() {
    }

    @BindView(R.id.process)
    ProgressBar progressBar;
    @BindView(R.id.notification_recycleview)
    RecyclerView notificationRecycleView;

    NotificationAdapter notificationAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<News> newses;


    void initAdapter() {
        newses = new ArrayList<>();
        notificationAdapter = new NotificationAdapter(getActivity(), newses, new NotificationAdapter.INotificationClicked() {
            @Override
            public void onNotificationClicked(News notificationItem) {
                NotificationDialog notificationDialog = new NotificationDialog(getActivity(),
                        "Chào thai hoang anh - Bạn đã được thăng hạng vàng. Hãy duy trì hạng của bạn để nhận nhiều ưu đãi",
                        new NotificationDialog.INotificationCloseEvent() {
                            @Override
                            public void onNotificationClose() {

                            }
                        });
                notificationDialog.show();
            }
        });
        layoutManager = new LinearLayoutManager(getActivity());
        notificationRecycleView.setLayoutManager(layoutManager);
        notificationRecycleView.setAdapter(notificationAdapter);
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.notification_fg;
    }

    @Override
    protected void initEventAndData() {
        initAdapter();
        onLoading();
        mPresenter.onGetNotification();
    }

    @Override
    public void useLanguage(String language) {

    }

    @Override
    public void onGetNotificationSuccess(ListNewsResponse listNewsResponse) {
        if (listNewsResponse != null && listNewsResponse.getError().getCode().contains("200")) {
            newses.clear();
            for (int i = 0; i < listNewsResponse.getNewses().size(); i++) {
                newses.add(listNewsResponse.getNewses().get(i));
            }
            notificationAdapter.notifyDataSetChanged();
            onStopLoading();
        }
    }

    @Override
    public void onLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStopLoading() {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }
}
