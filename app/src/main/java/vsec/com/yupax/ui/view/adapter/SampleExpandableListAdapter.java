package vsec.com.yupax.ui.view.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ExpandableListAdapter;

import vsec.com.yupax.R;
import vsec.com.yupax.model.http.response.RateQuestion;


/**
 * Created by nguyenthanhdong0109@gmail.com on 6/7/2017.
 */

public class SampleExpandableListAdapter extends BaseExpandableListAdapter implements ExpandableListAdapter {
    public Context context;
    CheckBox checkBox;
    private LayoutInflater vi;
    //    private String[][] data;
    int _objInt;
    public static Boolean checked[] = new Boolean[1];

    HashMap<Long, Boolean> checkboxMap = new HashMap<Long, Boolean>();
    private static final int GROUP_ITEM_RESOURCE = R.layout.group_item;
    private static final int CHILD_ITEM_RESOURCE = R.layout.child_item;
    public String[] check_string_array;

    private ArrayList<RateQuestion> rateQuestions;

    public SampleExpandableListAdapter(Context context, Activity activity, ArrayList<RateQuestion> rateQuestions) {
        this.rateQuestions = rateQuestions;
        this.context = context;
        vi = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        _objInt = rateQuestions.size();
        check_string_array = new String[_objInt];
        popolaCheckMap();
    }

    public void popolaCheckMap() {
    }

    public class CheckListener implements OnCheckedChangeListener {

        long pos;

        public void setPosition(long p) {
            pos = p;
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView,
                                     boolean isChecked) {
            Log.i("checkListenerChanged", String.valueOf(pos) + ":" + String.valueOf(isChecked));
            checkboxMap.put(pos, isChecked);
            if (isChecked == true) check_string_array[(int) pos] = "true";
            else check_string_array[(int) pos] = "false";
            // save checkbox state of each group
            SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor preferencesEditor = settings.edit();
            preferencesEditor.putString(String.valueOf((int) pos), check_string_array[(int) pos]);
            preferencesEditor.commit();
        }
    }

    public String getChild(int groupPosition, int childPosition) {
        return rateQuestions.get(groupPosition).getAnswers().get(childPosition).getRateAnswer();
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    public int getChildrenCount(int groupPosition) {
        return rateQuestions.get(groupPosition).getAnswers().size();
    }

    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View v = convertView;
        String child = getChild(groupPosition, childPosition);
        int id_res = 0;
        if (child != null) {
            v = vi.inflate(CHILD_ITEM_RESOURCE, null);
            RateViewHolder holder = new RateViewHolder(v);
            holder.text.setText(Html.fromHtml(child));
        }
        return v;
    }

    public String getGroup(int groupPosition) {
        return "group-" + groupPosition;
    }

    public int getGroupCount() {
        return rateQuestions.size();
    }


    public long getGroupId(int groupPosition) {
        return groupPosition;
    }


    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View v = convertView;
        String group = null;
        int id_res = 0;
        long group_id = getGroupId(groupPosition);

        group = "" + (group_id + 1) + ".";

        if (group != null) {
            v = vi.inflate(GROUP_ITEM_RESOURCE, null);
            RateGroupViewHolder holder = new RateGroupViewHolder(v);
            holder.num.setText(Html.fromHtml(group));
            holder.question.setText(rateQuestions.get(groupPosition).getQuestion());
        }
        return v;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    public boolean hasStableIds() {
        return true;
    }
}

