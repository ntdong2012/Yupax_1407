package vsec.com.yupax.ui.screen.login.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

import butterknife.BindView;
import vsec.com.yupax.R;
import vsec.com.yupax.base.BaseActivity;
import vsec.com.yupax.base.contract.MerchantContract;
import vsec.com.yupax.model.http.response.Merchant;
import vsec.com.yupax.model.http.response.MerchantListResponse;
import vsec.com.yupax.presenter.MerchantPresenter;
import vsec.com.yupax.ui.screen.home.activity.HomeActivity;
import vsec.com.yupax.ui.view.adapter.SelectMerchantAdapter;
import vsec.com.yupax.utils.ToastUtils;
import vsec.com.yupax.utils.log.DLog;

public class MerchantActivity extends BaseActivity<MerchantPresenter> implements MerchantContract.View {

    public static void callMerchantActivity(Context context, Bundle bundle) {
        Intent i = new Intent(context, MerchantActivity.class);
        i.putExtras(bundle);
        context.startActivity(i);
        ((Activity) context).overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }


    private boolean isFirstTime = false;
    @BindView(R.id.process)
    ProgressBar progressBar;
    @BindView(R.id.merchant_rv)
    RecyclerView merchantRv;
    RecyclerView.LayoutManager layoutManager;
    SelectMerchantAdapter merchantAdapter;
    ArrayList<Merchant> merchants;

    @Override
    protected int getLayout() {
        return R.layout.activity_merchant;
    }

    @Override
    protected void initEventAndData() {
        onLoading();
        layoutManager = new LinearLayoutManager(this);
        merchantRv.setLayoutManager(layoutManager);

        merchants = new ArrayList<>();
        merchantAdapter = new SelectMerchantAdapter(this, merchants, new SelectMerchantAdapter.IMerchantSelected() {
            @Override
            public void onMerchantSelected(Merchant merchant) {
                DLog.d("onMerchantSelected");
                HomeActivity.callHomeActivity(MerchantActivity.this, new Bundle());
                mPresenter.setCurrentMerchantCode(merchant.getHashcode());
                mPresenter.setCurrentMerchantName(merchant.getName());
                finish();
            }
        });

        merchantRv.setAdapter(merchantAdapter);
        mPresenter.getListMerchants();
    }

    @Override
    public void onResume() {
        super.onResume();
        isFirstTime = true;
    }

    @Override
    public void useLanguage(String language) {

    }


    @Override
    public void onGetMerchantListSuccess(MerchantListResponse merchantListResponse) {
        if (merchantListResponse != null && merchantListResponse.getErrorResponse() != null
                && merchantListResponse.getErrorResponse().getCode().contains("200")) {
            merchants.clear();
            for (int i = 0; i < merchantListResponse.getMerchants().size(); i++) {
                DLog.d("Merchant : " + merchantListResponse.getMerchants().get(0).getHashcode());
                merchants.add(merchantListResponse.getMerchants().get(i));
            }
            merchantAdapter.notifyDataSetChanged();
        } else {
            ToastUtils.shortShow(merchantListResponse.getErrorResponse().getMessage());
            mPresenter.setToken("");
            SignInActivity.callSignInActivity(this, new Bundle());
            this.finish();
        }
        onStopLoading();
    }

    @Override
    public void onLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStopLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void initInject() {
        getActivityComponent(true).inject(this);
    }
}
