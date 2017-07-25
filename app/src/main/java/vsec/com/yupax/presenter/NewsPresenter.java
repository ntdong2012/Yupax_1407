package vsec.com.yupax.presenter;

import javax.inject.Inject;

import vsec.com.yupax.base.RxPresenter;
import vsec.com.yupax.base.contract.NewContract;
import vsec.com.yupax.model.DataManager;
import vsec.com.yupax.model.http.HttpHelper;
import vsec.com.yupax.model.http.request.GetNewsRequest;
import vsec.com.yupax.model.http.response.ListNewsResponse;
import vsec.com.yupax.utils.CommonSubscriber;
import vsec.com.yupax.utils.RxUtil;
import vsec.com.yupax.utils.Utils;

/**
 * Created by ntdong2012 on 7/24/2017.
 */

public class NewsPresenter  extends RxPresenter<NewContract.View> implements NewContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public NewsPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getNews () {

        GetNewsRequest nr = new GetNewsRequest();
        nr = Utils.setupRequestFormat(nr);
        nr.setPageIndex(1);
        nr.setPageSize(10);
        nr.setToken(mDataManager.getToken());
        nr.setMerchantCode(mDataManager.getCurrentMerchantCode());
        nr.setServiceName(HttpHelper.ServiceName.LIST_NEWS);

        addSubscribe(mDataManager.getNews(nr)
                .compose(RxUtil.<ListNewsResponse>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<ListNewsResponse>(mView) {
                    @Override
                    public void onNext(ListNewsResponse listNewsResponse) {
                        mView.onGetNewsSuccess(listNewsResponse);
                    }
                })
        );
    }

    @Override
    public void saveToken(String token) {
        mDataManager.setToken(token);
    }
}
