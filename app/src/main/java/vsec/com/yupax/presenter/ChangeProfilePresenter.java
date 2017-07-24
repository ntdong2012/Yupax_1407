package vsec.com.yupax.presenter;

import javax.inject.Inject;

import vsec.com.yupax.base.RxPresenter;
import vsec.com.yupax.base.contract.ChangeProfileContract;
import vsec.com.yupax.model.DataManager;
import vsec.com.yupax.model.http.HttpHelper;
import vsec.com.yupax.model.http.request.BaseRequest;
import vsec.com.yupax.model.http.response.GetProvincesResponse;
import vsec.com.yupax.utils.CommonSubscriber;
import vsec.com.yupax.utils.RxUtil;
import vsec.com.yupax.utils.Utils;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/28/17.
 */

public class ChangeProfilePresenter extends RxPresenter<ChangeProfileContract.View> implements ChangeProfileContract.Presenter {

    private DataManager manager;

    @Inject
    public ChangeProfilePresenter(DataManager dataManager) {
        this.manager = dataManager;
    }


    @Override
    public void onChangeProfile(String password, String newPassword) {

    }

    @Override
    public void getProvinces() {

        BaseRequest baseRequest= new BaseRequest();
        baseRequest.setServiceName(HttpHelper.ServiceName.LIST_PROVINCE);
        baseRequest = Utils.setupRequestFormat(baseRequest);

        addSubscribe(manager.getProvinces(baseRequest)
                .compose(RxUtil.<GetProvincesResponse>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<GetProvincesResponse>(mView) {
                    @Override
                    public void onNext(GetProvincesResponse loginResponse) {
                        mView.onGetProvincesSuccess(loginResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        e.printStackTrace();
                    }
                })
        );
    }
}
