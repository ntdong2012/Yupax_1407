package vsec.com.yupax.ui.screen.login.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatRadioButton;
import android.text.TextUtils;
import android.widget.CompoundButton;
import android.widget.RadioButton;

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
    RadioButton[] merchants;
    private boolean isFirstTime = false;

    @Override
    protected int getLayout() {
        return R.layout.activity_merchant;
    }

    @Override
    protected void initEventAndData() {
        String currentMerchant = mPresenter.getCurrentMerchant();
        if (currentMerchant.contains(getString(R.string.resun_label))) {
            merchants[0].setChecked(true);
        } else if (currentMerchant.contains(getString(R.string.vietjet_label))) {
            merchants[1].setChecked(true);
        } else {
            merchants[2].setChecked(true);
        }


        for (int i = 0; i < merchants.length; i++) {
            merchants[i].setOnCheckedChangeListener(mOnCheck);
        }

    }

    CompoundButton.OnCheckedChangeListener mOnCheck = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                buttonView.setChecked(true);
                if (isFirstTime) {
                    HomeActivity.callHomeActivity(MerchantActivity.this, new Bundle());
                }
                String label = buttonView.getText().toString();
                if (!TextUtils.isEmpty(label) && label.equals(getString(R.string.vietjet_label))) {
                    mPresenter.setCurrentMerchant(getString(R.string.vietjet_label));
                } else if (!TextUtils.isEmpty(label) && label.equals(getString(R.string.resun_label))) {
                    mPresenter.setCurrentMerchant(getString(R.string.resun_label));
                } else {
                    mPresenter.setCurrentMerchant(getString(R.string.yupax_label));
                }
                finish();
            }
        }
    };


//    @OnCheckedChanged({R.id.resun_rb, R.id.vj_rb, R.id.yupax_rb})
//    public void onRadioButtonCheckChanged(CompoundButton button, boolean checked) {
//        if (checked) {
//            switch (button.getId()) {
//                case R.id.resun_rb:
//                    merchants[0].setChecked(true);
//                    //mPresenter.setCurrentMerchant(Common.SPF.RESUN_MERCHANT);
////                    runOnUiThread(new Runnable() {
////                        @Override
////                        public void run() {
////
////                        }
////                    });
//                    break;
//                case R.id.vj_rb:
//                    merchants[1].setChecked(true);
//                    //mPresenter.setCurrentMerchant(Common.SPF.VIETJET_MERCHANT);
////                    runOnUiThread(new Runnable() {
////                        @Override
////                        public void run() {
////
////                        }
////                    });
//                    break;
//                case R.id.yupax_rb:
//                    merchants[2].setChecked(true);
//                    //mPresenter.setCurrentMerchant(Common.SPF.YUPAX_MERCHANT);
////                    runOnUiThread(new Runnable() {
////                        @Override
////                        public void run() {
////
////                        }
////                    });
//                    break;
//            }
//
//            if (isFirstTime) {
//                HomeActivity.callHomeActivity(MerchantActivity.this, new Bundle());
//                MerchantActivity.this.finish();
//            }
//
////            if (isFirstTime) {
////                runOnUiThread(new Runnable() {
////                    @Override
////                    public void run() {
////                        HomeActivity.callHomeActivity(MerchantActivity.this, new Bundle());
////                        MerchantActivity.this.finish();
////                    }
////                });
////            }
//        }
//    }

    @Override
    public void onResume() {
        super.onResume();
        isFirstTime = true;
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
