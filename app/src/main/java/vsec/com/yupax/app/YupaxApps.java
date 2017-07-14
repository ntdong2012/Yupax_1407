package vsec.com.yupax.app;

import android.app.Activity;
import android.app.Application;
import android.graphics.Typeface;

import java.util.HashSet;
import java.util.Set;

import vsec.com.yupax.component.CrashHandler;
import vsec.com.yupax.di.component.AppComponent;
import vsec.com.yupax.di.component.DaggerAppComponent;
import vsec.com.yupax.di.module.AppModule;
import vsec.com.yupax.di.module.HttpModule;
import vsec.com.yupax.utils.log.DLog;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/8/2017.
 */

public class YupaxApps extends Application {

    private static Typeface fontAwesomeTf;
    private static YupaxApps instance;
    private Set<Activity> allActivities;
    private static AppModule appModule;
    private static HttpModule httpModule;
    public static AppComponent appComponent;

    public CrashHandler crashHandler;

    @Override
    public void onCreate() {
        super.onCreate();

        initFont();
        instance = this;

//        crashHandler = new CrashHandler(this);
//        CrashHandler.init(crashHandler);

    }

    public static AppComponent getAppComponent(boolean isNeedRefresh) {
        DLog.d("getAppComponent " + isNeedRefresh);
        if (appComponent == null || isNeedRefresh) {
            DLog.d("getAppComponent " + isNeedRefresh);

            appModule = new AppModule(instance);
            httpModule = new HttpModule();

            appComponent = DaggerAppComponent.builder()
                    .appModule(appModule)
                    .httpModule(httpModule)
                    .build();
        }
        return appComponent;
    }

    public static YupaxApps getInstance() {
        return instance;
    }

    void initFont() {
        if (fontAwesomeTf == null) {
            fontAwesomeTf = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        }
    }

    public static Typeface getFontAwesomeTf() {
        return fontAwesomeTf;
    }

    public static void setFontAwesomeTf(Typeface fontAwesomeTf) {
        YupaxApps.fontAwesomeTf = fontAwesomeTf;
    }

    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
