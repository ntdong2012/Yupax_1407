package vsec.com.yupax.presenter;

import javax.inject.Inject;

import vsec.com.yupax.base.RxPresenter;
import vsec.com.yupax.base.contract.SettingContract;
import vsec.com.yupax.model.DataManager;

/**
 * Created by nguyenthanhdong0109@gmail.com on 7/24/17.
 */

public class SettingPresenter extends RxPresenter<SettingContract.View> implements SettingContract.Presenter {


    private DataManager mDataManager;

    @Inject
    public SettingPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }


    @Override
    public void logout() {
        mDataManager.setToken("");
    }

    @Override
    public String getEmail() {
        return mDataManager.getEmail();
    }

    @Override
    public String getNumberPhone() {
        return mDataManager.getPhone();
    }

    @Override
    public String getUserName() {
        return mDataManager.getUserName();
    }


}
