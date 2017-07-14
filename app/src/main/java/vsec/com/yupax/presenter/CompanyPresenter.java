package vsec.com.yupax.presenter;

import javax.inject.Inject;

import vsec.com.yupax.base.RxPresenter;
import vsec.com.yupax.base.contract.CompanyContract;
import vsec.com.yupax.model.DataManager;

/**
 * Created by nguyenthanhdong0109@gmail.com on 6/6/2017.
 */

public class CompanyPresenter extends RxPresenter<CompanyContract.View> implements CompanyContract.Presenter {

    private DataManager dataManager;

    @Inject
    public CompanyPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }



    @Override
    public void onResendPassword(String userName) {

    }
}
