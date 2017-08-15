package vsec.com.yupax.ui.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import vsec.com.yupax.R;
import vsec.com.yupax.model.http.response.Promotion;

/**
 * Created by nguyenthanhdong0109@gmail.com on 8/16/17.
 */

public class StorePromotionAdapter extends RecyclerView.Adapter<StorePromotionAdapter.ViewHolder> {


    private Context context;
    private ArrayList<Promotion> promotions;

    public interface IPromotionClick

    {
        void onPromotionClick(Promotion p);
    }

    private IPromotionClick iPromotionClick;

    public StorePromotionAdapter(Context context, ArrayList<Promotion> promotions, IPromotionClick iPromotionClick) {
        this.context = context;
        this.promotions = promotions;
        this.iPromotionClick = iPromotionClick;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.store_promotion_item_layout, parent, false);
        ViewHolder pvh = new ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Promotion promotion = promotions.get(position);

        holder.text.setText("" + promotion.getName());
        String duration = "";
        holder.promotionRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPromotionClick.onPromotionClick(promotion);
            }
        });


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

        @BindView(R.id.text)
        TextView text;
        @BindView(R.id.store_promotion_item_layout)
        RelativeLayout promotionRl;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
