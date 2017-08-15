package vsec.com.yupax.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.text.DecimalFormat;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import vsec.com.yupax.R;
import vsec.com.yupax.model.http.request.BaseRequest;
import vsec.com.yupax.utils.log.DLog;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/14/17.
 */

public class Utils {

    public static int getHeightScreen(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        return display.getHeight();
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static String calculateDistance(double latOne, double logOne, double latTwo, double logTwo) {
        int Radius = 6371;// radius of earth in Km

        double dLat = Math.toRadians(latTwo - latOne);
        double dLon = Math.toRadians(logTwo - logOne);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(latOne))
                * Math.cos(Math.toRadians(latTwo)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        DLog.d("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);

        double orgin = Radius * c;
        DLog.d("ORIGIN : " + orgin);
        DecimalFormat result = new DecimalFormat("#.##");

        return result.format(orgin);
    }

    public static int getMaximumMapHeight(Context context) {
        int max = Utils.getHeightScreen(context) - Utils.getStatusBarHeight(context)
                - Utils.dpToPx(context.getResources(), (int) context.getResources().getDimension(R.dimen.actionbar_height))
                - Utils.dpToPx(context.getResources(), (int) context.getResources().getDimension(R.dimen.home_control_layout_height));
        return max;
    }

    public static int dpToPx(Resources res, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, res.getDisplayMetrics());
    }

    public static <T extends BaseRequest> T setupRequestFormat(T t) {
        t.setDeviceId("abc1-gdd2-dhc3-akd3");
        t.setDeviceType("Android");
        t.setLanguage("vi");
        t.setSecretKey("VsecYupax@2017");
        return t;
    }

    public static void hiddenSoftKeyboard(Context context, View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static <T> Flowable<T> createData(final T t) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }, BackpressureStrategy.BUFFER);
    }

}
