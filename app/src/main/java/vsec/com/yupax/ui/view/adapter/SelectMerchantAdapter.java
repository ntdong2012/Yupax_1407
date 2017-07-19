package vsec.com.yupax.ui.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import vsec.com.yupax.R;
import vsec.com.yupax.model.http.response.Merchant;

/**
 * Created by nguyenthanhdong0109@gmail.com on 7/18/17.
 */

public class SelectMerchantAdapter extends RecyclerView.Adapter<SelectMerchantAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Merchant> merchants;
    private IMerchantSelected iMerchantSelected;

    public interface IMerchantSelected {
        void onMerchantSelected(Merchant merchant);
    }


    public SelectMerchantAdapter(Context context, ArrayList<Merchant> merchants,
                                 IMerchantSelected iMerchantSelected) {
        this.context = context;
        this.merchants = merchants;
        this.iMerchantSelected = iMerchantSelected;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.select_merchant_item, parent, false);
        ViewHolder pvh = new ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Merchant item = merchants.get(position);
        holder.rb.setText(item.getName());
        holder.merchantWrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iMerchantSelected.onMerchantSelected(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return merchants.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.merchant_rb)
        RadioButton rb;
        @BindView(R.id.merchant_wrapper)
        RelativeLayout merchantWrapper;


        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
