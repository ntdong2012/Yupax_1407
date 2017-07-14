package vsec.com.yupax.presenter;

import javax.inject.Inject;

import vsec.com.yupax.base.RxPresenter;
import vsec.com.yupax.base.contract.ChangeProfileContract;
import vsec.com.yupax.model.DataManager;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/28/17.
 */

public class ChangeProfilePresenter extends RxPresenter<ChangeProfileContract.View> implements ChangeProfileContract.Presenter {

    private DataManager manager;

    @Inject
    public ChangeProfilePresenter(DataManager dataManager) {
        this.manager = dataManager;
    }


    @Override
    public void onChangeProfile(String password, String newPassword) {

    }
}
