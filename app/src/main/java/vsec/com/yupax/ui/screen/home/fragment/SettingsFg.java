package vsec.com.yupax.ui.screen.home.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import vsec.com.yupax.R;
import vsec.com.yupax.base.BaseFragment;
import vsec.com.yupax.base.contract.SettingContract;
import vsec.com.yupax.model.http.response.GetUserInfoResponse;
import vsec.com.yupax.presenter.SettingPresenter;
import vsec.com.yupax.ui.screen.home.activity.ChangePasswordActivity;
import vsec.com.yupax.ui.screen.home.activity.ChangeProfileActivity;
import vsec.com.yupax.ui.screen.home.activity.HistoryTransactionActivity;
import vsec.com.yupax.ui.screen.login.activity.SignInActivity;
import vsec.com.yupax.utils.ToastUtils;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/12/2017.
 */

public class SettingsFg extends BaseFragment<SettingPresenter> implements SettingContract.View {


    @BindView(R.id.change_password_layout)
    View changePasswordLayout;
    @BindView(R.id.upgrade_account_level_layout)
    View upgradeAccountLevelLayout;
    @BindView(R.id.link_account_layout)
    View linkAccountLayout;
    @BindView(R.id.history_transaction_layout)
    View historyTransactionLayout;
    @BindView(R.id.share_app_layout)
    View shareAppLayout;
    @BindView(R.id.invite_friend_layout)
    View inviteFriendLayout;
    @BindView(R.id.change_personal_info_layout)
    View changePersonalLayout;
    @BindView(R.id.logout_app_layout)
    View logoutLayout;

    @BindView(R.id.group_tv)
    TextView merchantTv;

    @BindView(R.id.name_tv)
    TextView nameTv;
    @BindView(R.id.email_tv)
    TextView emailTv;
    @BindView(R.id.phone_tv)
    TextView phoneTv;

    @Override
    protected int getLayoutId() {
        return R.layout.setting_fg_layout;
    }

    @Override
    protected void initEventAndData() {
        renderUI();
    }

    void renderUI() {
        ((ImageView) changePersonalLayout.findViewById(R.id.personal_icon)).setImageDrawable(getResources().getDrawable(R.drawable.user_icon_personal));


        ((TextView) changePasswordLayout.findViewById(R.id.personal_label_tv)).setText(getString(R.string.change_password_info));
        ((ImageView) changePasswordLayout.
                findViewById(R.id.personal_icon)).setImageDrawable(getResources().getDrawable(R.drawable.change_password_personal_icon));

        ((TextView) upgradeAccountLevelLayout.findViewById(R.id.personal_label_tv)).setText(getString(R.string.upgrade_account_level_label));
        ((ImageView) upgradeAccountLevelLayout.
                findViewById(R.id.personal_icon)).setImageDrawable(getResources().getDrawable(R.drawable.guide_update_level_icon));

        ((TextView) linkAccountLayout.findViewById(R.id.personal_label_tv)).setText(getString(R.string.link_account_label));

        ((TextView) historyTransactionLayout.findViewById(R.id.personal_label_tv)).setText(getString(R.string.history_transaction_label));
        ((ImageView) historyTransactionLayout.
                findViewById(R.id.personal_icon)).setImageDrawable(getResources().getDrawable(R.drawable.history_transaction_icon));
        ((View) historyTransactionLayout.findViewById(R.id.view_sp)).setVisibility(View.GONE);

        ((TextView) shareAppLayout.findViewById(R.id.personal_label_tv)).setText(getString(R.string.share_app_label));
        ((ImageView) shareAppLayout.
                findViewById(R.id.personal_icon)).setImageDrawable(getResources().getDrawable(R.drawable.icon_facebook_circle));


        ((TextView) inviteFriendLayout.findViewById(R.id.personal_label_tv)).setText(getString(R.string.invite_friend_label));
        ((ImageView) inviteFriendLayout.
                findViewById(R.id.personal_icon)).setImageDrawable(getResources().getDrawable(R.drawable.background_icon_change));
//        ((View) inviteFriendLayout.findViewById(R.id.view_sp)).setVisibility(View.GONE);

        ((TextView) logoutLayout.findViewById(R.id.personal_label_tv)).setText(getString(R.string.logout_app));
        ((ImageView) logoutLayout.
                findViewById(R.id.personal_icon)).setImageDrawable(getResources().getDrawable(R.drawable.info_icon));
        ((View) logoutLayout.findViewById(R.id.view_sp)).setVisibility(View.GONE);


        nameTv.setText(mPresenter.getUserName());
        phoneTv.setText(mPresenter.getNumberPhone());
        emailTv.setText(mPresenter.getEmail());
        mPresenter.getUserInfo();
        merchantTv.setText(mPresenter.getMerchantName());
    }

    @OnClick(R.id.history_transaction_layout)
    void historyTransactionCall() {
        HistoryTransactionActivity.callHistoryTransactionActivity(getActivity(), new Bundle());
    }

    @OnClick(R.id.change_password_layout)
    void changePasswordCall() {
        ChangePasswordActivity.callChangePasswordActivity(getActivity(), new Bundle());
    }

    @OnClick(R.id.change_personal_info_layout)
    void changePersonalInfoCall() {
        ChangeProfileActivity.callChangeProfileActivity(getActivity(), new Bundle());
    }

    @OnClick(R.id.logout_app_layout)
    void onLogout() {
        mPresenter.logout();
        SignInActivity.callSignInActivity(getActivity(), new Bundle());
        getActivity().finish();
    }

    @Override
    public void useLanguage(String language) {

    }

    @Override
    public void onLogoutSuccess() {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onGetUserInfoSuccess(GetUserInfoResponse getUserInfoResponse) {
        if (getUserInfoResponse != null && getUserInfoResponse.getErrorResponse() != null && getUserInfoResponse.getErrorResponse().getCode().contains("200")) {
            nameTv.setText("" + getUserInfoResponse.getUserInfoChange().getFirstName() + " " +
                    getUserInfoResponse.getUserInfoChange().getLastName());
        } else {
            ToastUtils.shortShow(getUserInfoResponse.getErrorResponse().getMessage());
        }
    }

    @Override
    public void onStopLoading() {

    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }
}
