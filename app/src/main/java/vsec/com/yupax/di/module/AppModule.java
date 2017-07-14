package vsec.com.yupax.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import vsec.com.yupax.app.YupaxApps;
import vsec.com.yupax.model.DataManager;
import vsec.com.yupax.model.http.HttpHelper;
import vsec.com.yupax.model.http.RetrofitHelper;
import vsec.com.yupax.model.prefs.PreferencesHelper;
import vsec.com.yupax.model.prefs.PreferencesHelperImpl;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/20/17.
 */

@Module
public class AppModule {

    private final YupaxApps application;

    public AppModule(YupaxApps application) {
        this.application = application;
    }

    @Provides
    @Singleton
    YupaxApps provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }

    //    @Provides
//    @Singleton
//    DBHelper provideDBHelper(RealmHelper realmHelper) {
//        return realmHelper;
//    }
//
    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(PreferencesHelperImpl implPreferencesHelper) {
        return implPreferencesHelper;
    }
//
    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper/*, DBHelper DBHelper,*/ , PreferencesHelper preferencesHelper) {
        return new DataManager(httpHelper, /*DBHelper,*/ preferencesHelper);
    }
}

