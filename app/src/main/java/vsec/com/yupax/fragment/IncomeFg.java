package vsec.com.yupax.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import vsec.com.yupax.R;
import vsec.com.yupax.base.BaseFg;
import vsec.com.yupax.di.view.IncomeView;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/13/17.
 */

public class IncomeFg extends BaseFg implements IncomeView {


    public IncomeFg() {
    }

    View rootView;
    @BindView(R.id.process)
    ProgressBar progressBar;
    @BindView(R.id.empty_layout)
    View emptyLayout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.history_money_in_out_layout, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                onLoadingIncomeError();
                return false;
            }
        });
        handler.sendEmptyMessageDelayed(1, 4000);
    }

    @Override
    public void onLoadingIncomeSuccess() {

    }

    @Override
    public void onLoadingIncomeError() {
        hiddenProcess(progressBar);
        ((TextView) emptyLayout.findViewById(R.id.empty_item_mess_tv)).setText(getString(R.string.history_mooney_empty_notice));
        emptyLayout.setVisibility(View.VISIBLE);


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
