package vsec.com.yupax.ui.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import vsec.com.yupax.R;
import vsec.com.yupax.model.http.response.TransactionItem;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/27/17.
 */

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {


    private Context context;
    private ArrayList<TransactionItem> transactionItems;

    public TransactionAdapter(Context context, ArrayList<TransactionItem> transactionItems) {
        this.context = context;
        this.transactionItems = transactionItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.notification_item, parent, false);
        ViewHolder pvh = new ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TransactionItem item = transactionItems.get(position);
        holder.notificationMessageTv.setText("" + item.getMessage());
        holder.notificationDateTv.setText("" + item.getDate());
    }

    @Override
    public int getItemCount() {
        return transactionItems.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.unread_iv)
        ImageView notificationIcon;
        @BindView(R.id.notification_message_tv)
        TextView notificationMessageTv;
        @BindView(R.id.notification_date)
        TextView notificationDateTv;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
