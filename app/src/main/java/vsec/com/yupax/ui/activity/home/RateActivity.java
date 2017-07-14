package vsec.com.yupax.ui.activity.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;

import butterknife.OnClick;
import vsec.com.yupax.R;
import vsec.com.yupax.base.BaseActivity;
import vsec.com.yupax.base.contract.RateContract;
import vsec.com.yupax.model.http.response.RateAnswer;
import vsec.com.yupax.model.http.response.RateQuestion;
import vsec.com.yupax.presenter.RatePresenter;
import vsec.com.yupax.ui.view.adapter.SampleExpandableListAdapter;

public class RateActivity extends BaseActivity<RatePresenter> implements RateContract.View {

    public static void callRateActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, RateActivity.class);
        intent.putExtra("home_data", bundle);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }


    @OnClick(R.id.back_tv)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    private Context context;
    private static final String[][] data = {{"audia4", "audiq7", "audir8"}, {"bmwm6", "bmwx6"}, {"ferrarienzo", "ferrarif430", "ferrarif430italia"}};
    private ExpandableListView expandableListView;


    @Override
    protected int getLayout() {
        return R.layout.activity_rate;
    }

    @Override
    protected void initEventAndData() {

        RateAnswer r = new RateAnswer("Rất tốt");
        RateAnswer r1 = new RateAnswer("Tốt");
        RateAnswer r2 = new RateAnswer("Rất rất tốt");

        ArrayList<RateAnswer> rateAnswers = new ArrayList<>();
        rateAnswers.add(r);
        rateAnswers.add(r1);
        rateAnswers.add(r2);


        RateQuestion q = new RateQuestion("Bạn có hài lòng với dịch vụ mặt đất của chúng tôi", rateAnswers);
        RateQuestion q2 = new RateQuestion("Bạn có hài lòng với dịch vụ hành lý của chúng tôi", rateAnswers);
        RateQuestion q3 = new RateQuestion("Bạn có hài lòng với dịch vụ sân bay của chúng tôi", rateAnswers);
        RateQuestion q4 = new RateQuestion("Bạn có hài lòng với dịch vụ máy bay của chúng tôi", rateAnswers);


        ArrayList<RateQuestion> rateQuestions = new ArrayList<>();
        rateQuestions.add(q);
        rateQuestions.add(q2);
        rateQuestions.add(q3);
        rateQuestions.add(q4);

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView1);
        expandableListView.setAdapter(new SampleExpandableListAdapter(context, this, rateQuestions));

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousGroup)
                    expandableListView.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });

    }

    @Override
    public void useLanguage(String language) {

    }

    @Override
    public void getRateQuestionSuccess() {

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
