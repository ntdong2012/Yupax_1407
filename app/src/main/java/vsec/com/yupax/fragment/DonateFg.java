package vsec.com.yupax.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
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
import vsec.com.yupax.di.view.DonateView;
import vsec.com.yupax.model.http.response.Donate;
import vsec.com.yupax.ui.view.adapter.DonateAdapter;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/13/17.
 */

public class DonateFg extends BaseFg implements DonateView {

    public DonateFg() {
    }

    View rootView;
    @BindView(R.id.process)
    ProgressBar progressBar;
    @BindView(R.id.empty_layout)
    RelativeLayout emptyLayout;
    @BindView(R.id.donate_rv)
    RecyclerView donateRv;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Donate> donates;
    DonateAdapter donateAdapter;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layoutManager = new LinearLayoutManager(getActivity());
        donateRv.setLayoutManager(layoutManager);
        donates = new ArrayList<>();
        donateAdapter = new DonateAdapter(getActivity(), donates);
        donateRv.setAdapter(donateAdapter);
        donateRv.setAdapter(donateAdapter);

        Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                onLoadDonateSuccess();
                return false;
            }
        });
        handler.sendEmptyMessageDelayed(1, 6000);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.donate_fg, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onLoadDonateSuccess() {
        donates.add(new Donate());
        donates.add(new Donate());
        donates.add(new Donate());
        donates.add(new Donate());
        donates.add(new Donate());
        donates.add(new Donate());
        donateAdapter.notifyDataSetChanged();
        hiddenProcess(progressBar);
    }

    @Override
    public void onLoadDonateError() {

    }

    @Override
    public void showProcess(ProgressBar progressBar) {

    }

    @Override
    public void hiddenProcess(ProgressBar progressBar) {

    }

    @Override
    public void onError() {

    }
}
