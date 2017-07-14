package vsec.com.yupax.component;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import vsec.com.yupax.app.YupaxApps;
import vsec.com.yupax.utils.ToastUtils;
import vsec.com.yupax.utils.log.DLog;


/**
 * Created by nguyenthanhdong0109@gmail.com on 5/20/17.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static Thread.UncaughtExceptionHandler defaultHandler = null;

    private Context context = null;
    private boolean isUTMode = true;
    private boolean isSendLog = false;

    private final String TAG = CrashHandler.class.getSimpleName();

    public CrashHandler(Context context) {
        this.context = context;
    }

    public static void init(CrashHandler crashHandler) {
        defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(crashHandler);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        String stackString = getStackTrace(ex);
        stackString = getDeviceSuperInfo() + stackString;
        if (isUTMode) {
            String[] mails = {"nguyenthanhdong0109@gmail.com"};
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("plain/text");
            intent.putExtra(Intent.EXTRA_EMAIL, mails);
            intent.putExtra(Intent.EXTRA_SUBJECT,
                    "[VOC for Yupax] FC_Log");
            intent.putExtra(Intent.EXTRA_TEXT, stackString);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            YupaxApps.getInstance().startActivity(intent);
            int pid = android.os.Process.myPid();
            try {
                android.os.Process.killProcess(pid);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            uncaughtException(thread, ex);
        }
        ToastUtils.shortShow("Sorry, the program is about to exit");
        YupaxApps.getInstance().exitApp();
    }


    private String getDeviceSuperInfo() {
        try {
            PackageInfo pInfo = YupaxApps.getInstance().getPackageManager().getPackageInfo(YupaxApps.getInstance().getPackageName(), 0);

            String s = "Debug-infos:";
            s += "\n OS Version: " + System.getProperty("os.version") + "("
                    + android.os.Build.VERSION.INCREMENTAL + ")";
            s += "\n OS API Level: " + android.os.Build.VERSION.SDK_INT;
            s += "\n Device: " + android.os.Build.DEVICE;
            s += "\n Model (and Product): " + android.os.Build.MODEL + " ("
                    + android.os.Build.PRODUCT + ")";
            s += "\n DISPLAY: " + android.os.Build.DISPLAY;
            s += "\n UNKNOWN: " + android.os.Build.UNKNOWN;
            s += "\n MANUFACTURER: " + android.os.Build.MANUFACTURER;
            s += "\n SERIAL: " + android.os.Build.SERIAL;
            s += "\n USER: " + android.os.Build.USER;
            s += "\n HOST: " + android.os.Build.HOST;
            s += "\n APPVER: " + pInfo.versionCode;
            s += "\n++++++++++++++++++++++++++++++++++++++\n";
            return s;
        } catch (Exception e) {
            return "";
        }

    }

    public String getCrashInfo(Throwable ex) {
        Writer result = new StringWriter();
        PrintWriter printWriter = new PrintWriter(result);
        ex.setStackTrace(ex.getStackTrace());
        ex.printStackTrace(printWriter);
        return result.toString();
    }

    private String getStackTrace(Throwable t) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        Throwable cause = t;

        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();

        }

        final String stackTrace = result.toString();
        printWriter.close();
        return stackTrace;
    }



    public String collectCrashDeviceInfo() {
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
            String versionName = pi.versionName;
            String model = android.os.Build.MODEL;
            String androidVersion = android.os.Build.VERSION.RELEASE;
            String manufacturer = android.os.Build.MANUFACTURER;
            return versionName + "  " + model + "  " + androidVersion + "  " + manufacturer;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
