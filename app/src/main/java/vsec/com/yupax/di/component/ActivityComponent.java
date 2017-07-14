package vsec.com.yupax.di.component;

import android.app.Activity;

import dagger.Component;
import vsec.com.yupax.di.module.ActivityModule;
import vsec.com.yupax.di.scope.ActivityScope;
import vsec.com.yupax.ui.activity.MoneyHistoryActivity;
import vsec.com.yupax.ui.activity.home.ChangePasswordActivity;
import vsec.com.yupax.ui.activity.home.ChangeProfileActivity;
import vsec.com.yupax.ui.activity.home.EventDetailActivity;
import vsec.com.yupax.ui.activity.home.HomeActivity;
import vsec.com.yupax.ui.activity.home.RateActivity;
import vsec.com.yupax.ui.activity.login.LanguageActivity;
import vsec.com.yupax.ui.activity.login.RegisterActivity;
import vsec.com.yupax.ui.activity.login.ResendActiveCodeActivity;
import vsec.com.yupax.ui.activity.login.SignInActivity;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/20/17.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(HomeActivity homeActivity);

    void inject(MoneyHistoryActivity moneyHistoryActivity);

    void inject(LanguageActivity languageActivity);

    void inject(ChangePasswordActivity changePasswordActivity);

    void inject(ResendActiveCodeActivity resendActiveCodeActivity);

    void inject(ChangeProfileActivity changeProfileActivity);

    void inject(EventDetailActivity eventDetailActivity);

    void inject(RateActivity rateActivity);

    void inject(SignInActivity signInActivity);

    void inject(RegisterActivity registerActivity);
}
