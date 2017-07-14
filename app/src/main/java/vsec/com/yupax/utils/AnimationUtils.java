package vsec.com.yupax.utils;

import android.content.Context;
import android.view.View;

import vsec.com.yupax.R;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/31/17.
 */

public class AnimationUtils {

    public static void shake(Context context, View view) {
        android.view.animation.Animation shake = android.view.animation.AnimationUtils
                .loadAnimation(context, R.anim.shake);
        view.startAnimation(shake);
        view.requestFocus();
    }
}
