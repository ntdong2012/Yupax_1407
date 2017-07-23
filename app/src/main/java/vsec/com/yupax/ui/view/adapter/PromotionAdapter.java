package vsec.com.yupax.ui.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import vsec.com.yupax.R;
import vsec.com.yupax.model.http.response.Promotion;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/13/17.
 */

public class PromotionAdapter
        extends RecyclerView.Adapter<PromotionAdapter.ViewHolder> {


    private Context context;
    private ArrayList<Promotion> promotions;

    public interface IEventClick {
        void onEventClick(Promotion promotion);
    }

    private IEventClick iEventClick;

    public PromotionAdapter(Context context, ArrayList<Promotion> promotions, IEventClick iEventClick) {
        this.context = context;
        this.promotions = promotions;
        this.iEventClick = iEventClick;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.promotion_item, parent, false);
        ViewHolder pvh = new ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Promotion promotion = promotions.get(position);

        holder.eventNameTv.setText("" + promotion.getName());
        holder.eventMessage.setText(Html.fromHtml(promotion.getDescription()));
        String duration = "";
        if (!TextUtils.isEmpty(promotion.getDateFrom())) {
            duration += promotion.getDateFrom();
        }
        if (!TextUtils.isEmpty(promotion.getDateTo())) {
            duration += " - " + promotion.getDateTo();
        }
        if (!TextUtils.isEmpty(duration)) {
            holder.eventDuration.setText(duration);
        } else {
            holder.eventDuration.setVisibility(View.GONE);
        }
        holder.eventRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iEventClick.onEventClick(promotion);
            }
        });

        Glide.with(context).load(promotion.getImages()).into(holder.eventIcon);

    }

    @Override
    public int getItemCount() {
        return promotions.size();
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
