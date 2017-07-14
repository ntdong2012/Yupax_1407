package vsec.com.yupax.presenter;

import javax.inject.Inject;

import vsec.com.yupax.base.RxPresenter;
import vsec.com.yupax.base.contract.HomeContract;
import vsec.com.yupax.base.contract.MoneyContract;
import vsec.com.yupax.model.DataManager;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/24/2017.
 */

public class MoneyPresenter extends RxPresenter<MoneyContract.View> implements MoneyContract.Presenter {

    private DataManager dataManager;

    @Inject
    public MoneyPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }


    @Override
    public void getCatalog() {

    }
}
