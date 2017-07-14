package vsec.com.yupax.component;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/20/17.
 */

public class ImageLoader {

    public static void load(Context context, String url, ImageView iv) {
//        if (!BtsApplication.getAppComponent().preferencesHelper().getNoImageState()) {
        Glide.with(context).load(url).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
//        }
    }

//    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
//    public static void load(Activity activity, String url, ImageView iv) {
//        if (!activity.isDestroyed()) {
//            Glide.with(activity).load(url).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
//        }
//    }
//
//    public static void loadFromDrawable(Activity activity, int id, ImageView iv) {
//        Glide.with(activity).load(id).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
//    }
//
//
//    public static void loadAll(Context context, String url, ImageView iv) {
//        Glide.with(context).load(url).crossFade().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(iv);
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
//    public static void loadAll(Activity activity, String url, ImageView iv) {
//        if (!activity.isDestroyed()) {
//            Glide.with(activity).load(url).crossFade().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(iv);
//        }
//    }
}
