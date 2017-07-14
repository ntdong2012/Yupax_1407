package vsec.com.yupax.utils.log;

import android.util.Log;

/**
 * Created by nguyenthanhdong0109@gmail.com on 8/24/16.
 * I win.
 */
public class Debugger {

    private static final String DEBUG_TAG = "ntdong";
    private static final boolean DEBUG_MODE = true;

    public static void d(String msg) {
        if (DEBUG_MODE) {
            Log.d(DEBUG_TAG,/* MainActivity.TAG +*/ " > " + msg);
        }
    }

    public static void e(String msg) {
        if (DEBUG_MODE) {
            Log.e(DEBUG_TAG,/* MainActivity.TAG + */" > " + msg);
        }
    }

    public static void w(String msg) {
        if (DEBUG_MODE) {
            Log.w(DEBUG_TAG, /*MainActivity.TAG +*/ " > " + msg);
        }
    }

    public static void i(String msg) {
        if (DEBUG_MODE) {
            Log.i(DEBUG_TAG, /*MainActivity.TAG +*/ " > " + msg);
        }
    }
}
