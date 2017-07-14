package vsec.com.yupax.presenter;

import javax.inject.Inject;

import vsec.com.yupax.base.RxPresenter;
import vsec.com.yupax.base.contract.HistoryTransactionContract;
import vsec.com.yupax.model.DataManager;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/27/17.
 */

public class HistoryTransactionPresenter extends RxPresenter<HistoryTransactionContract.View> implements HistoryTransactionContract.Presenter {


    private DataManager mDataManager;

    @Inject
    public HistoryTransactionPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void onGetTransaction() {

    }
}
