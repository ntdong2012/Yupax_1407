package vsec.com.yupax.ui.activity.login;

import vsec.com.yupax.R;
import vsec.com.yupax.base.BaseFragment;
import vsec.com.yupax.base.contract.SignUpContract;
import vsec.com.yupax.presenter.SignUpPresenter;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/25/17.
 */

public class SignUpFg extends BaseFragment<SignUpPresenter> implements SignUpContract.View {

    public SignUpFg() {
    }


    @Override
    public void useLanguage(String language) {

    }

    @Override
    public void onSignUpSuccess() {

    }

    @Override
    public void onSignUpError() {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onStopLoading() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.sign_up;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }
}
