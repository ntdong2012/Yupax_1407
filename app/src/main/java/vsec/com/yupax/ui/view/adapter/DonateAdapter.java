package vsec.com.yupax.ui.view.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
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
import vsec.com.yupax.model.http.response.Donate;
import vsec.com.yupax.app.YupaxApps;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/14/17.
 */

public class DonateAdapter extends RecyclerView.Adapter<DonateAdapter.ViewHolder> {


    private Context context;
    private ArrayList<Donate> gifts;

    public DonateAdapter(Context context, ArrayList<Donate> gifts) {
        this.context = context;
        this.gifts = gifts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.donate_item, parent, false);
        ViewHolder pvh = new ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Donate gift = gifts.get(position);

//        holder.name.setText("" + gift.getName());
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

        @BindView(R.id.friend_icon)
        ImageView friendIcon;
        @BindView(R.id.donate_btn)
        AppCompatButton donateBtn;
        @BindView(R.id.friend_name_tv)
        TextView name;
        @BindView(R.id.friend_phone_ico_tv)
        TextView phoneIco;
        @BindView(R.id.friend_phone_label_tv)
        TextView phoneLabel;
        @BindView(R.id.friend_email_ico_tv)
        TextView emailIco;
        @BindView(R.id.friend_email_label_tv)
        TextView emailLabel;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            phoneIco.setTypeface(YupaxApps.getFontAwesomeTf());
            emailIco.setTypeface(YupaxApps.getFontAwesomeTf());
        }
    }
}
