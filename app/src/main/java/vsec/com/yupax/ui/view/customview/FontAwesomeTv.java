package vsec.com.yupax.ui.view.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import vsec.com.yupax.app.YupaxApps;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/28/17.
 */

public class FontAwesomeTv extends AppCompatTextView {

    private Context context;

    public FontAwesomeTv(Context context) {
        super(context);
        this.context = context;
        this.setTypeface(YupaxApps.getFontAwesomeTf());
    }

    public FontAwesomeTv(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.setTypeface(YupaxApps.getFontAwesomeTf());
    }

    public FontAwesomeTv(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        this.setTypeface(YupaxApps.getFontAwesomeTf());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

}
