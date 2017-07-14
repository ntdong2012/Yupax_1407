package vsec.com.yupax.presenter;

import javax.inject.Inject;

import vsec.com.yupax.base.RxPresenter;
import vsec.com.yupax.base.contract.EventDetailContract;
import vsec.com.yupax.model.DataManager;

/**
 * Created by nguyenthanhdong0109@gmail.com on 6/2/2017.
 */

public class EventDetailPresenter extends RxPresenter<EventDetailContract.View> implements EventDetailContract.Presenter {

    private DataManager dataManager;

    @Inject
    public EventDetailPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void onGetEventDetail() {

    }
}
