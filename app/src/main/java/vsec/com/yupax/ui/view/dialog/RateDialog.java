package vsec.com.yupax.ui.view.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ExpandableListView;

import java.util.ArrayList;

import vsec.com.yupax.R;
import vsec.com.yupax.base.contract.RateContract;
import vsec.com.yupax.model.http.response.Rate;
import vsec.com.yupax.model.http.response.RateAnswer;
import vsec.com.yupax.model.http.response.RateQuestionResponse;
import vsec.com.yupax.presenter.RatePresenter;
import vsec.com.yupax.ui.view.adapter.SampleExpandableListAdapter;
import vsec.com.yupax.utils.log.DLog;

/**
 * Created by ntdong2012 on 7/14/2017.
 */

public class RateDialog extends AppCompatDialog{

    private Context context;
    private static final String[][] data = {{"audia4", "audiq7", "audir8"}, {"bmwm6", "bmwx6"}, {"ferrarienzo", "ferrarif430", "ferrarif430italia"}};
    private ExpandableListView expandableListView;
    private RatePresenter mRatePresenter;

    public RateDialog(Context context) {
        super(context);
        this.context = context;
    }

    public RateDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }

    protected RateDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.rate_layout);
        this.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(0x11000000));
        initRateInfo();

        initUI();
    }

    void initUI() {
        AppCompatButton confirmBtn = (AppCompatButton) findViewById(R.id.confirm_btn);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RateDialog.this.dismiss();
            }
        });
    }

    void initRateInfo() {
        RateAnswer r = new RateAnswer("Rất tốt");
        RateAnswer r1 = new RateAnswer("Tốt");
        RateAnswer r2 = new RateAnswer("Rất rất tốt");

        ArrayList<RateAnswer> rateAnswers = new ArrayList<>();
        rateAnswers.add(r);
        rateAnswers.add(r1);
        rateAnswers.add(r2);


        Rate q = new Rate("Bạn có hài lòng với dịch vụ mặt đất của chúng tôi", rateAnswers);
        Rate q2 = new Rate("Bạn có hài lòng với dịch vụ hành lý của chúng tôi", rateAnswers);
        Rate q3 = new Rate("Bạn có hài lòng với dịch vụ sân bay của chúng tôi", rateAnswers);
        Rate q4 = new Rate("Bạn có hài lòng với dịch vụ máy bay của chúng tôi", rateAnswers);


        ArrayList<Rate> rateQuestionResponses = new ArrayList<>();
        rateQuestionResponses.add(q);
        rateQuestionResponses.add(q2);
        rateQuestionResponses.add(q3);
        rateQuestionResponses.add(q4);

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView1);
        expandableListView.setAdapter(new SampleExpandableListAdapter(context, rateQuestionResponses));

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
//                if (groupPosition != previousGroup)
//                    expandableListView.collapseGroup(previousGroup);
//                previousGroup = groupPosition;
            }
        });
        DLog.d("Rate Question size: " + rateQuestionResponses.size());
        for (int i = 0; i < rateQuestionResponses.size(); i++) {
            expandableListView.expandGroup(i);
        }
    }

}
