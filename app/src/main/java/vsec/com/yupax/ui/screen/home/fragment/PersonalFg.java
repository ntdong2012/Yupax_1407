package vsec.com.yupax.ui.screen.home.fragment;

import android.text.TextUtils;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import vsec.com.yupax.R;
import vsec.com.yupax.base.BaseFragment;
import vsec.com.yupax.base.contract.PersonalContract;
import vsec.com.yupax.model.http.request.UserInfoChange;
import vsec.com.yupax.model.http.response.GetUserInfoResponse;
import vsec.com.yupax.presenter.PersonalPresenter;
import vsec.com.yupax.ui.view.dialog.QRCodeDialog;

/**
 * Created by nguyenthanhdong0109@gmail.com on 7/10/17.
 */

public class PersonalFg extends BaseFragment<PersonalPresenter> implements PersonalContract.View {


    @Override
    public void useLanguage(String language) {

    }

    @OnClick(R.id.qrcode_image_iv)
    void onClickQRCode() {
        QRCodeDialog dialog = new QRCodeDialog();
        dialog.show(getFragmentManager(), "Aaaa");
    }

    @Override
    public void getRateQuestionSuccess() {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void getUserInfoSuccess(GetUserInfoResponse userInfoRes) {
        if (userInfoRes != null && userInfoRes.getErrorResponse() != null
                && userInfoRes.getErrorResponse().getCode().contains("200")) {
            UserInfoChange user = userInfoRes.getUserInfoChange();
            String name = user.getFirstName() + " " + user.getLastName();
            String gender = user.getGender();
            if (!TextUtils.isEmpty(gender)) {
                if (gender.contains("FEMALE")) {
                    name = getString(R.string.mr_label) + " " + name;
                } else {
                    name = getString(R.string.ms_label) + " " + name;
                }
            }
            nameTv.setText(name);
        }
    }


    @Override
    public void onStopLoading() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.personal_fg;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.getUserInfo();
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @BindView(R.id.name_tv)
    TextView nameTv;
}
