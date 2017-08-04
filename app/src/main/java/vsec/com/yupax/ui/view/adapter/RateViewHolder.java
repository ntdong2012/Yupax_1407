package vsec.com.yupax.ui.view.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import vsec.com.yupax.R;

/**
 * Created by nguyenthanhdong0109@gmail.com on 6/7/2017.
 */

public class RateViewHolder {

    public TextView text;
    public CheckBox checkbox;
    public ImageView imageview;

    public RateViewHolder(View v) {
        this.text = (TextView) v.findViewById(R.id.text1);
        this.imageview = (ImageView) v.findViewById(R.id.image_cb);
    }
}
