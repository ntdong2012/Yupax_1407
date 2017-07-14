package vsec.com.yupax.ui.activity.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vsec.com.yupax.R;
import vsec.com.yupax.base.BaseFg;
import vsec.com.yupax.di.view.GiftView;
import vsec.com.yupax.model.http.response.EventItem;
import vsec.com.yupax.ui.view.adapter.EventAdapter;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/13/17.
 */

public class NowEventFg extends BaseFg implements GiftView {

    public NowEventFg() {
    }

    View rootView;
    @BindView(R.id.process)
    ProgressBar progressBar;
    @BindView(R.id.gift_recycleview)
    RecyclerView giftRecycleView;
    RecyclerView.LayoutManager mLayoutManager;
    @BindView(R.id.sort_spinner)
    Spinner sortSpinner;
    ArrayList<String> sortConditions;
    @BindView(R.id.sort_tv)
    TextView sortTv;
    EventAdapter eventAdapter;
    ArrayList<EventItem> eventItems;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.event_fg, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initSortView();

        initGiftView();

        Handler mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {

                eventItems.add(new EventItem("VTB Công ty cổ phần công nghệ Tiến Đạt", "Giảm giá 5%", "18/07/2017 - 30/07/2017"));
                eventItems.add(new EventItem("VTB Công ty cổ phần công nghệ Tiến Đạt", "Giảm giá 5%", "18/07/2017 - 30/07/2017"));
                eventItems.add(new EventItem("VTB Công ty cổ phần công nghệ Tiến Đạt", "Giảm giá 5%", "18/07/2017 - 30/07/2017"));
                eventItems.add(new EventItem("VTB Công ty cổ phần công nghệ Tiến Đạt", "Giảm giá 5%", "18/07/2017 - 30/07/2017"));
                eventItems.add(new EventItem("VTB Công ty cổ phần công nghệ Tiến Đạt", "Giảm giá 5%", "18/07/2017 - 30/07/2017"));
                eventItems.add(new EventItem("VTB Công ty cổ phần công nghệ Tiến Đạt", "Giảm giá 5%", "18/07/2017 - 30/07/2017"));

                eventAdapter.notifyDataSetChanged();
                hiddenProcess(progressBar);


                return false;
            }
        });

        mHandler.sendEmptyMessageAtTime(1, 2000);
    }

    void initGiftView() {
        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        giftRecycleView.setLayoutManager(mLayoutManager);
        eventItems = new ArrayList<>();
        eventAdapter = new EventAdapter(getActivity(), eventItems, new EventAdapter.IEventClick() {
            @Override
            public void onEventClick(EventItem eventItem) {
                EventDetailActivity.callEventDetail(getActivity(), new Bundle());
            }
        });
        giftRecycleView.setAdapter(eventAdapter);
    }

    void initSortView() {
        sortConditions = new ArrayList<>();
        sortConditions.add("Tất cả");
        sortConditions.add("Nổi bật");
        ArrayAdapter<String> sortAdapter = new ArrayAdapter<String>(getActivity(), R.layout.city_item_actionbar, sortConditions);
        sortSpinner.setAdapter(sortAdapter);
        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sortTv.setText("" + sortConditions.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @OnClick(R.id.sort_tv)
    void onSortClick() {
        sortSpinner.performClick();
    }

    @Override
    public void onGetGiftSuccess() {

    }

    @Override
    public void showProcess(ProgressBar progressBar) {

    }

    @Override
    public void hiddenProcess(ProgressBar progressBar) {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onError() {

    }
}
