package vsec.com.yupax.presenter;

import javax.inject.Inject;

import vsec.com.yupax.base.RxPresenter;
import vsec.com.yupax.base.contract.HomeFgContract;
import vsec.com.yupax.model.DataManager;

/**
 * Created by nguyenthanhdong0109@gmail.com on 7/9/17.
 */

public class HomeFgPresenter extends RxPresenter<HomeFgContract.View> implements HomeFgContract.Presenter {

    private DataManager dataManager;

    @Inject
    public HomeFgPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }


    @Override
    public void getProductItems() {

    }
}
