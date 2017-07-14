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
import vsec.com.yupax.model.http.response.Charity;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/14/17.
 */

public class CharityAdapter extends RecyclerView.Adapter<CharityAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Charity> charities;

    public interface IEventClicked {
        void onEventClicked(Charity charity);
    }

    public IEventClicked iEventClicked;

    public CharityAdapter(Context context, ArrayList<Charity> charities, IEventClicked iEventClicked) {
        this.context = context;
        this.charities = charities;
        this.iEventClicked = iEventClicked;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.charity_item, parent, false);
        ViewHolder pvh = new ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Charity charity = charities.get(position);

        holder.name.setText("" + charity.getName());
        holder.eventView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iEventClicked.onEventClicked(charity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return charities.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.charity_icon)
        ImageView charityIcon;
        @BindView(R.id.charity_name_tv)
        TextView name;
        @BindView(R.id.charity_message_tv)
        TextView charityMessage;
        @BindView(R.id.event_view)
        RelativeLayout eventView;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
