package vsec.com.yupax.ui.screen.home.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import vsec.com.yupax.R;
import vsec.com.yupax.base.BaseActivity;
import vsec.com.yupax.base.contract.RateContract;
import vsec.com.yupax.model.http.response.BaseResponse;
import vsec.com.yupax.model.http.response.Rate;
import vsec.com.yupax.model.http.response.RateAnswer;
import vsec.com.yupax.model.http.response.RateQuestionResponse;
import vsec.com.yupax.presenter.RatePresenter;
import vsec.com.yupax.ui.view.adapter.SampleExpandableListAdapter;
import vsec.com.yupax.utils.log.DLog;

public class RateActivity extends BaseActivity<RatePresenter> implements RateContract.View {

    public static void callRateActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, RateActivity.class);
        intent.putExtra("home_data", bundle);
        context.startActivity(intent);
//        ((Activity) context).overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    @BindView(R.id.expandableListView1)
    ExpandableListView expandableListView;
    SampleExpandableListAdapter rateAdapter;
    ArrayList<Rate> rates;


    @Override
    protected int getLayout() {
        return R.layout.rate_layout;
    }

    @Override
    protected void initEventAndData() {
        rates = new ArrayList<>();
        rateAdapter = new SampleExpandableListAdapter(this, rates);
        expandableListView.setAdapter(rateAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousGroup)
                    expandableListView.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
//                if (groupPosition != previousGroup)
//                    expandableListView.collapseGroup(previousGroup);
//                previousGroup = groupPosition;
            }
        });
        DLog.d("Rate Question size: " + rates.size());
        for (int i = 0; i < rates.size(); i++) {
            expandableListView.expandGroup(i);
        }

        mPresenter.getRateQuestions();
    }

    @Override
    public void useLanguage(String language) {

    }

    @OnClick(R.id.confirm_btn)
    void onAnswerRequest() {
        if(rates != null && rates.size()>0) {
            mPresenter.sendRateAnswer(rates);
        } else {
            this.finish();
        }
    }

    @Override
    public void sendRateAnswersSuccess(BaseResponse baseResponse) {
        DLog.d("sendRateAnswersSuccess");
        this.finish();
    }

    @Override
    public void getRateQuestionSuccess(RateQuestionResponse rQr) {
       if(rQr != null && rQr.getErrorResponse().getCode().equals("200")) {
            rates.clear();
            for (int i = 0; i< rQr.getRates().size(); i++) {
                rates.add(rQr.getRates().get(i));
            }
            rateAdapter.notifyDataSetChanged();
            for (int i = 0; i < rates.size(); i++) {
                expandableListView.expandGroup(i);
            }
        }

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onStopLoading() {

    }

    @Override
    protected void initInject() {
        getActivityComponent(false).inject(this);
    }
}
