package vsec.com.yupax.ui.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import vsec.com.yupax.R;
import vsec.com.yupax.model.http.response.News;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/12/2017.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private Context context;
    private ArrayList<News> notificationItems;
    private INotificationClicked iNotificationClicked;

    public interface INotificationClicked {
        void onNotificationClicked(News notificationItem);
    }


    public NotificationAdapter(Context context, ArrayList<News> notificationItems,
                               INotificationClicked iNotificationClicked) {
        this.context = context;
        this.notificationItems = notificationItems;
        this.iNotificationClicked = iNotificationClicked;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.notification_item, parent, false);
        ViewHolder pvh = new ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final News item = notificationItems.get(position);
        holder.notificationMessageTv.setText("" + item.getDescription());
//        holder.notificationDateTv.setText(item.getDate());
        holder.notificationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iNotificationClicked.onNotificationClicked(item);
            }
        });

//        if (item.isRead()) {
//            holder.unreadIv.setVisibility(View.VISIBLE);
//        } else {
//            holder.unreadIv.setVisibility(View.INVISIBLE);
//        }

        holder.notificationTitleTv.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return notificationItems.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.unread_iv)
        ImageView unreadIv;
        @BindView(R.id.notification_message_tv)
        TextView notificationMessageTv;
        @BindView(R.id.notification_date)
        TextView notificationDateTv;
        @BindView(R.id.notification_item_layout)
        RelativeLayout notificationView;
        @BindView(R.id.notification_title_tv)
        TextView notificationTitleTv;


        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
