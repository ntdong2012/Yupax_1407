package vsec.com.yupax.ui.screen.home.fragment;

import butterknife.OnClick;
import vsec.com.yupax.R;
import vsec.com.yupax.base.BaseFragment;
import vsec.com.yupax.base.contract.PersonalContract;
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
    public void onStopLoading() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.personal_fg;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }
}
