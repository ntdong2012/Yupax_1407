package vsec.com.yupax.model.http;

import javax.inject.Inject;

import io.reactivex.Flowable;
import vsec.com.yupax.model.http.api.YupaxApis;
import vsec.com.yupax.model.http.request.ActiveUserRequest;
import vsec.com.yupax.model.http.request.BaseRequest;
import vsec.com.yupax.model.http.request.ChangePasswordRequest;
import vsec.com.yupax.model.http.request.CreateUserRequest;
import vsec.com.yupax.model.http.request.GetNewsRequest;
import vsec.com.yupax.model.http.request.GetPromotionRequest;
import vsec.com.yupax.model.http.request.ListStoreRequest;
import vsec.com.yupax.model.http.request.LoginRequest;
import vsec.com.yupax.model.http.request.MerchantListRequest;
import vsec.com.yupax.model.http.request.RegisterUserToMerchantRequest;
import vsec.com.yupax.model.http.request.ResendPasswordRequest;
import vsec.com.yupax.model.http.request.StoreDetailRequest;
import vsec.com.yupax.model.http.request.UserInfoChangeRequest;
import vsec.com.yupax.model.http.response.ActiveUserResponse;
import vsec.com.yupax.model.http.response.BaseResponse;
import vsec.com.yupax.model.http.response.ChangePasswordResponse;
import vsec.com.yupax.model.http.response.GetCategoriesResponse;
import vsec.com.yupax.model.http.response.GetPromotionsResponse;
import vsec.com.yupax.model.http.response.GetProvincesResponse;
import vsec.com.yupax.model.http.response.ListNewsResponse;
import vsec.com.yupax.model.http.response.ListStoreResponse;
import vsec.com.yupax.model.http.response.LoginResponse;
import vsec.com.yupax.model.http.response.MerchantListResponse;
import vsec.com.yupax.model.http.response.StoreDetailResponse;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/20/17.
 */

public class RetrofitHelper implements HttpHelper {

    private YupaxApis yupaxApis;

    @Inject
    public RetrofitHelper(YupaxApis yupaxApis) {
        this.yupaxApis = yupaxApis;
    }

    @Override
    public Flowable<BaseResponse> updateUserInfo(UserInfoChangeRequest userInfoChangeRequest) {
        return yupaxApis.updateUserInfo(userInfoChangeRequest);
    }

    @Override
    public Flowable<LoginResponse> signIn(LoginRequest loginRequest) {
        return yupaxApis.signIn(loginRequest);
    }

    @Override
    public Flowable<BaseResponse> resendPassword(ResendPasswordRequest resendPasswordRequest) {
        return yupaxApis.resendActiveCode(resendPasswordRequest);
    }


    @Override
    public Flowable<ChangePasswordResponse> changePassword(ChangePasswordRequest changePasswordRequest) {
        return yupaxApis.changePassword(changePasswordRequest);
    }

    @Override
    public Flowable<MerchantListResponse> getListMerchant(MerchantListRequest merchantListRequest) {
        return yupaxApis.getMerchants(merchantListRequest);
    }

    @Override
    public Flowable<ListStoreResponse> getListStoreBrand(ListStoreRequest listStoreRequest) {
        return yupaxApis.getListStores(listStoreRequest);
    }

    @Override
    public Flowable<StoreDetailResponse> getStoreDetail(StoreDetailRequest storeDetailRequest) {
        return yupaxApis.getStoreDetail(storeDetailRequest);
    }

    @Override
    public Flowable<GetCategoriesResponse> getCategories(BaseRequest baseRequest) {
        return yupaxApis.getCategories(baseRequest);
    }

    @Override
    public Flowable<GetProvincesResponse> getProvinces(BaseRequest baseRequest) {
        return yupaxApis.getProvinces(baseRequest);
    }

    @Override
    public Flowable<ListNewsResponse> getNews(GetNewsRequest newsRequest) {
        return yupaxApis.getNews(newsRequest);
    }

    @Override
    public Flowable<BaseResponse> registerUser(CreateUserRequest createUserRequest) {
        return yupaxApis.registerUser(createUserRequest);
    }

    @Override
    public Flowable<ActiveUserResponse> activeUser(ActiveUserRequest activeUserRequest) {
        return yupaxApis.activeUser(activeUserRequest);
    }

    @Override
    public Flowable<BaseResponse> registerUserToMerchant(RegisterUserToMerchantRequest registerUserToMerchantRequest) {
        return yupaxApis.registerUserToMerchant(registerUserToMerchantRequest);
    }

    @Override
    public Flowable<GetPromotionsResponse> getPromotions(GetPromotionRequest getPromotionRequest) {
        return yupaxApis.getPromotions(getPromotionRequest);
    }
}
