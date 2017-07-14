package vsec.com.yupax.presenter;

import javax.inject.Inject;

import vsec.com.yupax.base.RxPresenter;
import vsec.com.yupax.base.contract.MerchantContract;
import vsec.com.yupax.model.DataManager;

/**
 * Created by nguyenthanhdong0109@gmail.com on 7/14/17.
 */

public class MerchantPresenter extends RxPresenter<MerchantContract.View> implements MerchantContract.Presenter {

    private DataManager dataManager;

    @Inject
    public MerchantPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }


    @Override
    public void onGetNotification() {

    }

    @Override
    public String getCurrentMerchant() {
        return dataManager.getCurrentMerchant();
    }

    @Override
    public void setCurrentMerchant(String str) {
        dataManager.setCurrentMerchant(str);
    }


}
