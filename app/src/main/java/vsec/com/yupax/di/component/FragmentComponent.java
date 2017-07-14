package vsec.com.yupax.di.component;

import android.app.Activity;

import dagger.Component;
import vsec.com.yupax.di.module.FragmentModule;
import vsec.com.yupax.di.scope.FragmentScope;
import vsec.com.yupax.ui.screen.home.fragment.HomeFg;
import vsec.com.yupax.ui.screen.home.fragment.NotificationFg;
import vsec.com.yupax.ui.screen.home.fragment.PersonalFg;
import vsec.com.yupax.ui.screen.home.fragment.TransactionFg;
import vsec.com.yupax.ui.screen.login.fragment.ForgotPasswordFg;
import vsec.com.yupax.ui.screen.login.fragment.SignInFg;
import vsec.com.yupax.ui.screen.login.fragment.SignUpFg;

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
