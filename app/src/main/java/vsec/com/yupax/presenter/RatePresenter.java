package vsec.com.yupax.presenter;

import javax.inject.Inject;

import vsec.com.yupax.base.RxPresenter;
import vsec.com.yupax.base.contract.RateContract;
import vsec.com.yupax.model.DataManager;

/**
 * Created by nguyenthanhdong0109@gmail.com on 6/7/2017.
 */

public class RatePresenter extends RxPresenter<RateContract.View> implements RateContract.Presenter {


    private DataManager mDataManager;

    @Inject
    public RatePresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }


    @Override
    public void onGetNotification() {

    }
}
