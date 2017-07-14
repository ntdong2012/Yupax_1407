package vsec.com.yupax.presenter;

import javax.inject.Inject;

import vsec.com.yupax.base.RxPresenter;
import vsec.com.yupax.base.contract.NotificationContract;
import vsec.com.yupax.model.DataManager;

/**
 * Created by nguyenthanhdong0109@gmail.com on 6/1/2017.
 */

public class NotificationPresenter extends RxPresenter<NotificationContract.View> implements NotificationContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public NotificationPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void onGetNotification() {

    }
}
