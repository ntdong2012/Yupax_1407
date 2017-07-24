package vsec.com.yupax.ui.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
import vsec.com.yupax.model.http.response.News;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/14/17.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private Context context;
    private ArrayList<News> news;

    public interface IEventClicked {
        void onEventClicked(News charity);
    }

    public IEventClicked iEventClicked;

    public NewsAdapter(Context context, ArrayList<News> news, IEventClicked iEventClicked) {
        this.context = context;
        this.news = news;
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

        final News aNew = news.get(position);

        holder.name.setText("" + aNew.getName());
        holder.eventView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iEventClicked.onEventClicked(aNew);
            }
        });
        Glide.with(context).load(aNew.getImages()).into(holder.charityIcon);

    }

    @Override
    public int getItemCount() {
        return news.size();
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
