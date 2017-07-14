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
import vsec.com.yupax.model.http.response.EventItem;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/13/17.
 */

public class EventAdapter
        extends RecyclerView.Adapter<EventAdapter.ViewHolder> {


    private Context context;
    private ArrayList<EventItem> eventItems;

    public interface IEventClick {
        void onEventClick(EventItem eventItem);
    }

    private IEventClick iEventClick;

    public EventAdapter(Context context, ArrayList<EventItem> eventItems, IEventClick iEventClick) {
        this.context = context;
        this.eventItems = eventItems;
        this.iEventClick = iEventClick;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.event_item, parent, false);
        ViewHolder pvh = new ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final EventItem eventItem = eventItems.get(position);

        holder.eventNameTv.setText("" + eventItem.getName());
        holder.eventMessage.setText("" + eventItem.getMessage());
        holder.eventDuration.setText("" + eventItem.getDuration());
        holder.eventRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iEventClick.onEventClick(eventItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventItems.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.event_icon)
        ImageView eventIcon;
        @BindView(R.id.event_name_tv)
        TextView eventNameTv;
        @BindView(R.id.event_message_tv)
        TextView eventMessage;
        @BindView(R.id.event_duration_tv)
        TextView eventDuration;
        @BindView(R.id.event_layout)
        RelativeLayout eventRl;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
