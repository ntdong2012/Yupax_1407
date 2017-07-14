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
import vsec.com.yupax.model.http.response.RateAnswer;
import vsec.com.yupax.model.http.response.RateQuestion;
import vsec.com.yupax.ui.view.adapter.SampleExpandableListAdapter;
import vsec.com.yupax.utils.log.DLog;

/**
 * Created by ntdong2012 on 7/14/2017.
 */

public class RateDialog extends AppCompatDialog {

    private Context context;
    private static final String[][] data = {{"audia4", "audiq7", "audir8"}, {"bmwm6", "bmwx6"}, {"ferrarienzo", "ferrarif430", "ferrarif430italia"}};
    private ExpandableListView expandableListView;


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
        expandableListView.setAdapter(new SampleExpandableListAdapter(context, (Activity) context, rateQuestions));

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
//                if (groupPosition != previousGroup)
//                    expandableListView.collapseGroup(previousGroup);
//                previousGroup = groupPosition;
            }
        });
        DLog.d("Rate Question size: " + rateQuestions.size());
        for (int i = 0; i < rateQuestions.size(); i++) {
            expandableListView.expandGroup(i);
        }
    }
}
