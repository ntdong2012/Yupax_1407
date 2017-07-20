package vsec.com.yupax.di.component;

import android.app.Activity;

import dagger.Component;
import vsec.com.yupax.di.module.ActivityModule;
import vsec.com.yupax.di.scope.ActivityScope;
import vsec.com.yupax.ui.screen.home.activity.ChangePasswordActivity;
import vsec.com.yupax.ui.screen.home.activity.ChangeProfileActivity;
import vsec.com.yupax.ui.screen.home.activity.EventDetailActivity;
import vsec.com.yupax.ui.screen.home.activity.HomeActivity;
import vsec.com.yupax.ui.screen.home.activity.RateActivity;
import vsec.com.yupax.ui.screen.home.activity.StoreDetailActivity;
import vsec.com.yupax.ui.screen.login.activity.LanguageActivity;
import vsec.com.yupax.ui.screen.login.activity.MerchantActivity;
import vsec.com.yupax.ui.screen.login.activity.RegisterActivity;
import vsec.com.yupax.ui.screen.login.activity.ResendActiveCodeActivity;
import vsec.com.yupax.ui.screen.login.activity.SignInActivity;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/20/17.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(HomeActivity homeActivity);

    void inject(LanguageActivity languageActivity);

    void inject(ChangePasswordActivity changePasswordActivity);

    void inject(ResendActiveCodeActivity resendActiveCodeActivity);

    void inject(ChangeProfileActivity changeProfileActivity);

    void inject(EventDetailActivity eventDetailActivity);

    void inject(RateActivity rateActivity);

    void inject(SignInActivity signInActivity);

    void inject(RegisterActivity registerActivity);

    void inject(MerchantActivity merchantActivity);

    void inject(StoreDetailActivity storeDetailActivity);
}
