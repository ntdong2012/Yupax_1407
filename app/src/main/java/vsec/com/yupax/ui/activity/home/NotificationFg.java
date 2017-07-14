package vsec.com.yupax.ui.activity.home;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

import butterknife.BindView;
import vsec.com.yupax.R;
import vsec.com.yupax.base.BaseFragment;
import vsec.com.yupax.base.contract.NotificationContract;
import vsec.com.yupax.model.http.response.NotificationItem;
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
    ArrayList<NotificationItem> notificationItems;


    void initAdapter() {
        notificationItems = new ArrayList<>();
        notificationAdapter = new NotificationAdapter(getActivity(), notificationItems, new NotificationAdapter.INotificationClicked() {
            @Override
            public void onNotificationClicked(NotificationItem notificationItem) {
                NotificationDialog notificationDialog = new NotificationDialog(getActivity(), "Chào thai hoang anh - Bạn đã được thăng hạng vàng. Hãy duy trì hạng của bạn để nhận nhiều ưu đãi");
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

        Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                onStopLoading();
                updateNotification();
                return false;
            }
        });
        handler.sendEmptyMessageDelayed(1, 3000);
    }

    void updateNotification() {
        NotificationItem item = new NotificationItem();
        item.setMessage("Chào thai hoang anh - Bạn đã được thăng hạng vàng. Hãy duy trì hạng của bạn để nhận nhiều ưu đãi");
        item.setDate("17:29 23/05/2017");
        item.setRead(true);
        item.setTitle("Thông báo");
        notificationItems.add(item);

        NotificationItem item2 = new NotificationItem();
        item2.setMessage("Chào thai hoang anh - Bạn đã được thăng hạng vàng. Hãy duy trì hạng của bạn để nhận nhiều ưu đãi");
        item2.setDate("17:29 23/05/2017");
        item2.setTitle("Thông báo");
        item2.setRead(false);

        notificationItems.add(item2);

        NotificationItem item3 = new NotificationItem();
        item3.setMessage("Chào thai hoang anh - Bạn đã được thăng hạng vàng. Hãy duy trì hạng của bạn để nhận nhiều ưu đãi");
        item3.setRead(false);
        item3.setTitle("Quà liền tay, trúng ngay Yupax plus");
        item3.setDate("17:29 23/05/2017");
        notificationItems.add(item3);
        notificationAdapter.notifyDataSetChanged();
    }

    @Override
    public void useLanguage(String language) {

    }

    @Override
    public void onGetNotificationSuccess() {

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
