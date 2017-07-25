package vsec.com.yupax.presenter;

import javax.inject.Inject;

import vsec.com.yupax.base.RxPresenter;
import vsec.com.yupax.base.contract.ChangeProfileContract;
import vsec.com.yupax.model.DataManager;
import vsec.com.yupax.model.http.HttpHelper;
import vsec.com.yupax.model.http.request.BaseRequest;
import vsec.com.yupax.model.http.request.GetUserInfoRequest;
import vsec.com.yupax.model.http.request.UserInfoChange;
import vsec.com.yupax.model.http.request.UserInfoChangeRequest;
import vsec.com.yupax.model.http.response.BaseResponse;
import vsec.com.yupax.model.http.response.GetDistrictResponse;
import vsec.com.yupax.model.http.response.GetProvincesResponse;
import vsec.com.yupax.model.http.response.GetUserInfoResponse;
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
    public void requestChangeProfile(String firstName, String lastName, String gender, String provinceId, String districtId, String address, String email, long dateOfBirth, String phone) {
        UserInfoChange c = new UserInfoChange();
        c.setAddress(address);
        c.setDistrictId(districtId);
        c.setEmail(email);
        c.setFirstName(firstName);
        c.setLastName(lastName);
        c.setMobile(phone);
        c.setGender(gender);
        c.setProvinceId(provinceId);
        c.setDateOfBirth(""+dateOfBirth);
        UserInfoChangeRequest ui = new UserInfoChangeRequest();
        ui.setToken(manager.getToken());
        ui.setMerchantCode(manager.getCurrentMerchantCode());
        ui = Utils.setupRequestFormat(ui);
        ui.setServiceName(HttpHelper.ServiceName.UPDATE_INFO_USER);
        ui.setUserInfo(c);
        mView.onLoading();
        addSubscribe(manager.updateUserInfo(ui)
                .compose(RxUtil.<BaseResponse>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<BaseResponse>(mView) {
                    @Override
                    public void onNext(BaseResponse loginResponse) {
                        mView.onStopLoading();
                        mView.onChangeProfileSuccess(loginResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        e.printStackTrace();
                    }
                })
        );
    }

    @Override
    public void getProvinces() {

        BaseRequest baseRequest= new BaseRequest();
        baseRequest.setServiceName(HttpHelper.ServiceName.LIST_PROVINCE);
        baseRequest = Utils.setupRequestFormat(baseRequest);
        mView.onLoading();
        addSubscribe(manager.getProvinces(baseRequest)
                .compose(RxUtil.<GetProvincesResponse>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<GetProvincesResponse>(mView) {
                    @Override
                    public void onNext(GetProvincesResponse loginResponse) {
                        mView.onStopLoading();
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

    @Override
    public void saveUserName(String userName) {
        manager.setUserName(userName);
    }

    @Override
    public void getUserInfo() {
        GetUserInfoRequest guir = new GetUserInfoRequest();
        guir.setToken(manager.getToken());
        guir.setMerchantCode(manager.getCurrentMerchantCode());
        guir = Utils.setupRequestFormat(guir);
        guir.setServiceName(HttpHelper.ServiceName.GET_USER_INFO);
        mView.onLoading();
        addSubscribe(manager.getUserInfo(guir)
                .compose(RxUtil.<GetUserInfoResponse>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<GetUserInfoResponse>(mView) {
                    @Override
                    public void onNext(GetUserInfoResponse baseResponse) {
                        mView.onStopLoading();
                        mView.onGetUserInfoSuccess(baseResponse);
                    }
                })
        );
    }

    @Override
    public void getDistricts(String provinceId) {
        BaseRequest gdr = new BaseRequest();
        gdr.setServiceName(HttpHelper.ServiceName.LIST_DISTRICT);
        gdr = Utils.setupRequestFormat(gdr);
        gdr.setProvinceId(provinceId);
        mView.onLoading();
        addSubscribe(manager.getDistricts(gdr)
                .compose(RxUtil.<GetDistrictResponse>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<GetDistrictResponse>(mView) {
                    @Override
                    public void onNext(GetDistrictResponse getDistrictResponse) {
                        mView.onStopLoading();
                        mView.onGetDistrictSuccess(getDistrictResponse);

                    }
                })
        );
    }

    @Override
    public UserInfoChange getUserInfoFromDb() {
        UserInfoChange u = new UserInfoChange();
        return u;
    }
}
