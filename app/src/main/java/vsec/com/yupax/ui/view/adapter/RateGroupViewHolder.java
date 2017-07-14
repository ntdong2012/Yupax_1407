package vsec.com.yupax.ui.view.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import vsec.com.yupax.R;

/**
 * Created by nguyenthanhdong0109@gmail.com on 6/7/2017.
 */

public class RateGroupViewHolder {

    public TextView num;
    public TextView question;

    public RateGroupViewHolder(View v) {
        this.num = (TextView) v.findViewById(R.id.num_tv);
        this.question = (TextView) v.findViewById(R.id.question_tv);
    }
}
