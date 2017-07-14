package vsec.com.yupax.ui.screen.login.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatRadioButton;
import android.widget.CompoundButton;

import butterknife.BindViews;
import butterknife.OnCheckedChanged;
import vsec.com.yupax.R;
import vsec.com.yupax.base.BaseActivity;
import vsec.com.yupax.base.contract.MerchantContract;
import vsec.com.yupax.presenter.MerchantPresenter;
import vsec.com.yupax.ui.screen.home.activity.HomeActivity;
import vsec.com.yupax.utils.Common;

public class MerchantActivity extends BaseActivity<MerchantPresenter> implements MerchantContract.View {

    public static void callMerchantActivity(Context context, Bundle bundle) {
        Intent i = new Intent(context, MerchantActivity.class);
        i.putExtras(bundle);
        context.startActivity(i);
        ((Activity) context).overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }


    @BindViews({R.id.resun_rb, R.id.vj_rb, R.id.yupax_rb})
    AppCompatRadioButton[] merchants;

    @Override
    protected int getLayout() {
        return R.layout.activity_merchant;
    }

    @Override
    protected void initEventAndData() {
        String currentMerchant = mPresenter.getCurrentMerchant();
        if (currentMerchant.contains(Common.SPF.RESUN_MERCHANT)) {
            merchants[0].setChecked(true);
        } else if (currentMerchant.contains(Common.SPF.VIETJET_MERCHANT)) {
            merchants[1].setChecked(true);
        } else {
            merchants[2].setChecked(true);
        }
    }

    @OnCheckedChanged({R.id.resun_rb, R.id.vj_rb, R.id.yupax_rb})
    public void onRadioButtonCheckChanged(CompoundButton button, boolean checked) {
        for (int i = 0; i < merchants.length; i++) {
            merchants[i].setChecked(false);
        }
        if (checked) {
            switch (button.getId()) {
                case R.id.resun_rb:
                    mPresenter.setCurrentMerchant(Common.SPF.RESUN_MERCHANT);
                    merchants[0].setChecked(true);
                    break;
                case R.id.vj_rb:
                    mPresenter.setCurrentMerchant(Common.SPF.VIETJET_MERCHANT);
                    merchants[1].setChecked(true);
                    break;
                case R.id.yupax_rb:
                    mPresenter.setCurrentMerchant(Common.SPF.YUPAX_MERCHANT);
                    merchants[2].setChecked(true);
                    break;
            }
        }
        HomeActivity.callHomeActivity(this, new Bundle());
        this.finish();
    }

    @Override
    public void useLanguage(String language) {

    }

    @Override
    public void onGetNotificationSuccess() {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onStopLoading() {

    }

    @Override
    protected void initInject() {
        getActivityComponent(false).inject(this);
    }
}
