package vsec.com.yupax.model.http;

import io.reactivex.Flowable;
import vsec.com.yupax.model.http.request.ActiveUserRequest;
import vsec.com.yupax.model.http.request.BaseRequest;
import vsec.com.yupax.model.http.request.ChangePasswordRequest;
import vsec.com.yupax.model.http.request.CreateUserRequest;
import vsec.com.yupax.model.http.request.GetPromotionRequest;
import vsec.com.yupax.model.http.request.ListStoreRequest;
import vsec.com.yupax.model.http.request.LoginRequest;
import vsec.com.yupax.model.http.request.MerchantListRequest;
import vsec.com.yupax.model.http.request.NewsRequest;
import vsec.com.yupax.model.http.request.RegisterUserToMerchantRequest;
import vsec.com.yupax.model.http.request.ResendPasswordRequest;
import vsec.com.yupax.model.http.request.StoreDetailRequest;
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

public interface HttpHelper {

    interface ServiceName {
        public String LOGIN = "LOGIN_USER";
        public String CREATE_USER = "CREATE_USER";
        public String RESEND_PASSWORD = "RESEND_PASSWORD";
        public String CHANGE_PASSWORD = "CHANGE_PASSWORD";
        public String LIST_MERCHANT = "LIST_MERCHANT";
        public String LIST_STORE_BRANCH = "LIST_STORE_BRANCH";
        public String DETAIL_STORE_BRANCH = "DETAIL_STORE_BRANCH";
        public String LIST_CATEGORY = "LIST_CATEGORY";
        public String LIST_PROVINCE = "LIST_PROVINCE";
        public String LIST_NEWS = "LIST_NEWS";
        public String FORGOT_PASSWORD = "FORGOT_PASSWORD";
        public String ACTIVE_USER = "ACTIVE_USER";
        public String CREATE_USER_DETAIL = "CREATE_USER_DETAIL";
        public String LIST_PROMOTION = "LIST_PROMOTION";
    }

    Flowable<LoginResponse> signIn(LoginRequest loginRequest);

    Flowable<BaseResponse> resendPassword(ResendPasswordRequest resendPasswordRequest);

    Flowable<ChangePasswordResponse> changePassword(ChangePasswordRequest changePasswordRequest);

    Flowable<MerchantListResponse> getListMerchant(MerchantListRequest merchantListRequest);

    Flowable<ListStoreResponse> getListStoreBrand(ListStoreRequest listStoreRequest);

    Flowable<StoreDetailResponse> getStoreDetail(StoreDetailRequest storeDetailRequest);

    Flowable<GetCategoriesResponse> getCategories(BaseRequest baseRequest);

    Flowable<GetProvincesResponse> getProvinces(BaseRequest baseRequest);

    Flowable<ListNewsResponse> getNews(NewsRequest newsRequest);

    Flowable<BaseResponse> registerUser(CreateUserRequest createUserRequest);

    Flowable<ActiveUserResponse> activeUser(ActiveUserRequest activeUserRequest);

    Flowable<BaseResponse> registerUserToMerchant(RegisterUserToMerchantRequest registerUserToMerchantRequest);

    Flowable<GetPromotionsResponse> getPromotions(GetPromotionRequest getPromotionRequest);

}
