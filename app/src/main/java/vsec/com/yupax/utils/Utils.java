package vsec.com.yupax.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import vsec.com.yupax.R;
import vsec.com.yupax.model.http.request.BaseRequest;

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

    public static int getMaximumMapHeight(Context context) {
        int max = Utils.getHeightScreen(context) - Utils.getStatusBarHeight(context)
                - Utils.dpToPx(context.getResources(), (int) context.getResources().getDimension(R.dimen.actionbar_height))
                - Utils.dpToPx(context.getResources(), (int) context.getResources().getDimension(R.dimen.home_control_layout_height));
        return max;
    }

    public static int dpToPx(Resources res, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, res.getDisplayMetrics());
    }

    public static <T extends BaseRequest> T setupRequestFormat(T t){
        t.setDeviceId("abc1-gdd2-dhc3-akd3");
        t.setDeviceType("Android");
        t.setLanguage("vi");
//        t.setMerchantCode("B03149B1EB2027152795");
        t.setSecretKey("VsecYupax@2017");
        return t;
    }

    public static void hiddenSoftKeyboard(Context context, View view){
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
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
