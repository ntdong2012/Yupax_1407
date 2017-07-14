package vsec.com.yupax.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import vsec.com.yupax.R;
import vsec.com.yupax.base.BaseFg;
import vsec.com.yupax.ui.activity.home.HomeActivity;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/9/2017.
 */

public class LoginFg extends BaseFg {

    View rootView;

    public LoginFg() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fg_login, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.login_btn)
    void onLogin() {
        HomeActivity.callHomeActivity(getActivity(), new Bundle());
        getActivity().finish();
    }
}
