package vsec.com.yupax.ui.screen.home.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import vsec.com.yupax.R;
import vsec.com.yupax.base.BaseFg;
import vsec.com.yupax.model.http.response.Charity;
import vsec.com.yupax.ui.screen.home.activity.EventDetailActivity;
import vsec.com.yupax.ui.view.adapter.CharityAdapter;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/13/17.
 */

public class NewsFg extends BaseFg  {


    View rootView;
    @BindView(R.id.process)
    ProgressBar progressBar;
    @BindView(R.id.charity_rv)
    RecyclerView charityRv;
    @BindView(R.id.empty_layout)
    RelativeLayout emptyView;
    CharityAdapter charityAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Charity> charities;

    public NewsFg() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.charity_fg, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        charities = new ArrayList<>();
        layoutManager = new GridLayoutManager(getActivity(), 2);
        charityRv.setLayoutManager(layoutManager);
        charityAdapter = new CharityAdapter(getActivity(), charities, new CharityAdapter.IEventClicked() {
            @Override
            public void onEventClicked(Charity charity) {
                EventDetailActivity.callEventDetail(getActivity(), new Bundle());
            }
        });

        charityRv.setAdapter(charityAdapter);


        Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                onLoadCharitySuccess();
                return false;
            }
        });

        handler.sendEmptyMessageDelayed(1, 4000);
    }


    public void onLoadCharitySuccess() {
        charities.add(new Charity("Hoi chu thap do"));
        charities.add(new Charity("Hoi chu thap do"));
        charities.add(new Charity("Hoi chu thap do"));
        charityAdapter.notifyDataSetChanged();

    }


}
