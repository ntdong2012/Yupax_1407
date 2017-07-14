package vsec.com.yupax.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/20/17.
 */

public class SnackbarUtil {

    public static void show(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }

    public static void showShort(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }

}
