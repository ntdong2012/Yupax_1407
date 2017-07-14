package vsec.com.yupax.utils;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by nguyenthanhdong0109@gmail.com on 6/6/2017.
 */

public class FontUtils {

    public static Typeface normalFont;
    public static Typeface lightFont;
    public static Typeface boldFont;
    public static Typeface regularFont;
    public static FontUtils instance;

    private FontUtils(Context context){
        normalFont = Typeface.createFromAsset(context.getAssets(), "VIE-HelveticaNeue.otf");
        boldFont = Typeface.createFromAsset(context.getAssets(), "VIE-HelveticaNeue-Bold.otf");
        lightFont = Typeface.createFromAsset(context.getAssets(), "VIE-HelveticaNeue-Light.otf");
    }

    public static FontUtils getInstance(Context context) {
        if(instance == null) {
            instance = new FontUtils(context);
        }
        return instance;
    }

}
