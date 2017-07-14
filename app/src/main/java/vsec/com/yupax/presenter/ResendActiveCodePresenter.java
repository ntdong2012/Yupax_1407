package vsec.com.yupax.presenter;

import javax.inject.Inject;

import vsec.com.yupax.base.RxPresenter;
import vsec.com.yupax.base.contract.ResendActiveCodeContract;
import vsec.com.yupax.model.DataManager;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/28/17.
 */

public class ResendActiveCodePresenter extends RxPresenter<ResendActiveCodeContract.View> implements ResendActiveCodeContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public ResendActiveCodePresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }


    @Override
    public void onResendOtp(String userName) {

    }
}
