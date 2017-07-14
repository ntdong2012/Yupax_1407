package vsec.com.yupax.utils;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/14/17.
 */

public class PerUtils {

    private static final int ANDROID_M_VERSION = 23;

    public static boolean isAndroidM() {
        return Build.VERSION.SDK_INT >= ANDROID_M_VERSION;
    }


    @TargetApi(Build.VERSION_CODES.M)
    public static boolean hasReadExternalPermission(Context context) {
        if (!isAndroidM()) return true;
        if (context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        return true;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static boolean hasWriteExternalPermission(Context context) {
        if (!isAndroidM()) return true;
        if (context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        return true;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static boolean isNeverAskAgainWithWriteSdcardPermission(Activity mActivity) {
        if (!isAndroidM()) return true;
        if (!mActivity.shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            return false;
        }
        return true;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static boolean hasAccessFineLocationPermission(Context context) {
        if (!isAndroidM()) return true;
        if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        return true;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static boolean isNeverAskAgainWithAccessFineLocationPermission(Activity mActivity) {
        if (!isAndroidM()) return true;
        if (!mActivity.shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
            return false;
        }
        return true;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static boolean hasAccessCoarseLocationPermission(Context context) {
        if (!isAndroidM()) return true;
        if (context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        return true;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static boolean isNeverAskAgainWithAccessCoarseLocationPermission(Activity mActivity) {
        if (!isAndroidM()) return true;
        if (!mActivity.shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            return false;
        }
        return true;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static boolean isNeverAskAgainWithAccessNetworkPermission(Activity mActivity) {
        if (!isAndroidM()) return true;
        if (!mActivity.shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_NETWORK_STATE)) {
            return false;
        }
        return true;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static boolean isNeverAskAgainWithReadExPermission(Activity mActivity) {
        if (!isAndroidM()) return true;
        if (!mActivity.shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            return false;
        }
        return true;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static boolean isNeverAskAgainWithInternetPermission(Activity mActivity) {
        if (!isAndroidM()) return true;
        if (!mActivity.shouldShowRequestPermissionRationale(Manifest.permission.INTERNET)) {
            return false;
        }
        return true;
    }
}
