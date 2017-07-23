package vsec.com.yupax.ui.screen.login.fragment;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import vsec.com.yupax.R;
import vsec.com.yupax.app.YupaxApps;
import vsec.com.yupax.base.BaseFragment;
import vsec.com.yupax.base.contract.ForgotPasswordContract;
import vsec.com.yupax.model.http.response.ActiveUserResponse;
import vsec.com.yupax.model.http.response.BaseResponse;
import vsec.com.yupax.presenter.ForgotPasswordPresenter;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/26/2017.
 */

public class ForgotPasswordFg extends BaseFragment<ForgotPasswordPresenter> implements ForgotPasswordContract.View {


    @BindView(R.id.back_tv)
    TextView backTv;

    @BindView(R.id.user_name_edt)
    EditText userNameEdt;


    @OnClick(R.id.back_tv)
    void onBackPress() {
        getActivity().onBackPressed();
    }

    @OnClick(R.id.resend_password_btn)
    void onResendPassword() {

        String userName = userNameEdt.getText().toString();

        if (!TextUtils.isEmpty(userName)) {
            mPresenter.onResendPassword(userName);
        }

    }

    @Override
    public void useLanguage(String language) {

    }



    @Override
    public void onGetPasswordSuccess(BaseResponse baseResponse) {

    }

    @Override
    public void onActiveUserSuccess(ActiveUserResponse baseResponse) {

    }



    @Override
    public void onLoading() {

    }

    @Override
    public void onStopLoading() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_forgot_password;
    }

    @Override
    protected void initEventAndData() {
        backTv.setTypeface(YupaxApps.getFontAwesomeTf());
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }
}
