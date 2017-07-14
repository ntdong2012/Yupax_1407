package vsec.com.yupax.di.component;

import android.app.Activity;

import dagger.Component;
import vsec.com.yupax.di.module.FragmentModule;
import vsec.com.yupax.di.scope.FragmentScope;
import vsec.com.yupax.ui.activity.home.HomeFg;
import vsec.com.yupax.ui.activity.home.NotificationFg;
import vsec.com.yupax.ui.activity.home.PersonalFg;
import vsec.com.yupax.ui.activity.home.TransactionFg;
import vsec.com.yupax.ui.activity.login.ForgotPasswordFg;
import vsec.com.yupax.ui.activity.login.SignInFg;
import vsec.com.yupax.ui.activity.login.SignUpFg;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/21/17.
 */


@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(SignInFg signInFg);

    void inject(SignUpFg signUpFg);

    void inject(ForgotPasswordFg forgotPasswordFg);

    void inject(TransactionFg transactionFg);

    void inject(NotificationFg notificationFg);

    void inject(HomeFg homeFg);

    void inject(PersonalFg personalFg);

}
