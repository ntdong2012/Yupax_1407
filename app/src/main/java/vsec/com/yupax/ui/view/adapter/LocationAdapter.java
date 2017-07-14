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
import vsec.com.yupax.model.http.response.Location;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/15/2017.
 */

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {


    private Context context;
    private ArrayList<Location> gifts;

    public interface IItemClick {
        void onItemClick(Location location);
    }

    public IItemClick iItemClick;

    public LocationAdapter(Context context, ArrayList<Location> gifts, IItemClick iItemClick) {
        this.context = context;
        this.gifts = gifts;
        this.iItemClick = iItemClick;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.address_item, parent, false);
        ViewHolder pvh = new ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Location gift = gifts.get(position);

        holder.locationNameTv.setText(gift.getName());
        holder.phoneLabel.setText(gift.getPhone());
        holder.addressTv.setText(gift.getAddress());
        holder.locationLabelTv.setText(gift.getDistance());
        holder.messageTv.setText(gift.getMessage());
        if (gift.isRated()) {
            holder.rateIcon.setVisibility(View.VISIBLE);
        } else {
            holder.rateIcon.setVisibility(View.GONE);
        }
        holder.addressIcon.setImageDrawable(context.getResources().getDrawable(gift.getLogoId()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iItemClick.onItemClick(gift);
            }
        });
    }

    @Override
    public int getItemCount() {
        return gifts.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.address_icon)
        ImageView addressIcon;
        @BindView(R.id.location_name_tv)
        TextView locationNameTv;
        @BindView(R.id.address_tv)
        TextView addressTv;
        @BindView(R.id.location_ico_tv)
        TextView locationIco;
        @BindView(R.id.location_label_tv)
        TextView locationLabelTv;
        @BindView(R.id.phone_ico_tv)
        TextView phoneIco;
        @BindView(R.id.phone_label_tv)
        TextView phoneLabel;
        @BindView(R.id.message_tv)
        TextView messageTv;
        @BindView(R.id.rate_icon_iv)
        ImageView rateIcon;
        @BindView(R.id.item_view)
        RelativeLayout itemView;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
