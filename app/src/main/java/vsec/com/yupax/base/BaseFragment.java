package vsec.com.yupax.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import vsec.com.yupax.app.YupaxApps;
import vsec.com.yupax.di.component.DaggerFragmentComponent;
import vsec.com.yupax.di.component.FragmentComponent;
import vsec.com.yupax.di.module.FragmentModule;
import vsec.com.yupax.utils.SnackbarUtil;
import vsec.com.yupax.utils.log.DLog;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/25/17.
 */

public abstract class BaseFragment<T extends BasePresenter> extends SimpleFragment implements BaseView {

    @Inject
    protected T mPresenter;

    protected Bundle bundle;

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(YupaxApps.getAppComponent(true))
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initInject();
        mPresenter.attachView(this);
        this.bundle = savedInstanceState;
        super.onViewCreated(view, savedInstanceState);
    }


    public Bundle getBundle() {
        return bundle;
    }

    @Override
    public void onDestroyView() {
        DLog.d("onDestroyView");
        if (mPresenter != null) mPresenter.detachView();
        super.onDestroyView();
    }

    @Override
    public void showErrorMsg(String msg) {
        SnackbarUtil.show(((ViewGroup) getActivity().findViewById(android.R.id.content)).getChildAt(0), msg);
    }

    @Override
    public void useNightMode(boolean isNight) {

    }

    @Override
    public void stateError() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateMain() {

    }

    protected abstract void initInject();
}
