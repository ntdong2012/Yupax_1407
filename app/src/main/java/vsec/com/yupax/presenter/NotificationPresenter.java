package vsec.com.yupax.presenter;

import javax.inject.Inject;

import vsec.com.yupax.base.RxPresenter;
import vsec.com.yupax.base.contract.NotificationContract;
import vsec.com.yupax.model.DataManager;
import vsec.com.yupax.model.http.HttpHelper;
import vsec.com.yupax.model.http.request.NewsRequest;
import vsec.com.yupax.model.http.response.ListNewsResponse;
import vsec.com.yupax.utils.CommonSubscriber;
import vsec.com.yupax.utils.RxUtil;
import vsec.com.yupax.utils.Utils;

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

        NewsRequest nr = new NewsRequest();
        nr = Utils.setupRequestFormat(nr);
        nr.setPageIndex(1);
        nr.setPageSize(10);
        nr.setServiceName(HttpHelper.ServiceName.LIST_NEWS);

//        addSubscribe(mDataManager.getNews(nr)
//                .compose(RxUtil.<ListNewsResponse>rxSchedulerHelper())
//                .subscribeWith(new CommonSubscriber<ListNewsResponse>(mView) {
//                    @Override
//                    public void onNext(ListNewsResponse listNewsResponse) {
//                        mView.onGetNotificationSuccess(listNewsResponse);
//                    }
//                })
//        );


    }
}
