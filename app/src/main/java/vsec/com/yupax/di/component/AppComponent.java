package vsec.com.yupax.di.component;

import javax.inject.Singleton;

import dagger.Component;
import vsec.com.yupax.app.YupaxApps;
import vsec.com.yupax.di.module.AppModule;
import vsec.com.yupax.di.module.HttpModule;
import vsec.com.yupax.model.DataManager;
import vsec.com.yupax.model.http.RetrofitHelper;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/20/17.
 */


@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    YupaxApps getContext();

    RetrofitHelper retrofitHelper();

    DataManager getDataManager();


}


