package vsec.com.yupax.model.http;

import io.reactivex.Flowable;
import vsec.com.yupax.model.http.request.ActiveUserRequest;
import vsec.com.yupax.model.http.request.BaseRequest;
import vsec.com.yupax.model.http.request.ChangePasswordRequest;
import vsec.com.yupax.model.http.request.CreateUserRequest;
import vsec.com.yupax.model.http.request.GetDistrictRequest;
import vsec.com.yupax.model.http.request.GetNewsRequest;
import vsec.com.yupax.model.http.request.GetPromotionRequest;
import vsec.com.yupax.model.http.request.GetUserInfoRequest;
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
import vsec.com.yupax.model.http.response.GetDistrictResponse;
import vsec.com.yupax.model.http.response.GetPromotionsResponse;
import vsec.com.yupax.model.http.response.GetProvincesResponse;
import vsec.com.yupax.model.http.response.GetUserInfoResponse;
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
        public String UPDATE_INFO_USER = "UPDATE_INFO_USER";
        public String GET_USER_INFO = "GET_USER_INFO";
        public String LIST_DISTRICT = "LIST_DISTRICT";
    }

    Flowable<BaseResponse> updateUserInfo(UserInfoChangeRequest userInfoChangeRequest);

    Flowable<LoginResponse> signIn(LoginRequest loginRequest);

    Flowable<BaseResponse> resendPassword(ResendPasswordRequest resendPasswordRequest);

    Flowable<ChangePasswordResponse> changePassword(ChangePasswordRequest changePasswordRequest);

    Flowable<MerchantListResponse> getListMerchant(MerchantListRequest merchantListRequest);

    Flowable<ListStoreResponse> getListStoreBrand(ListStoreRequest listStoreRequest);

    Flowable<StoreDetailResponse> getStoreDetail(StoreDetailRequest storeDetailRequest);

    Flowable<GetCategoriesResponse> getCategories(BaseRequest baseRequest);

    Flowable<GetUserInfoResponse> getUserInfo(GetUserInfoRequest getUserInfoRequest);

    Flowable<GetProvincesResponse> getProvinces(BaseRequest baseRequest);

    Flowable<ListNewsResponse> getNews(GetNewsRequest getNewsRequest);

    Flowable<BaseResponse> registerUser(CreateUserRequest createUserRequest);

    Flowable<ActiveUserResponse> activeUser(ActiveUserRequest activeUserRequest);

    Flowable<BaseResponse> registerUserToMerchant(RegisterUserToMerchantRequest registerUserToMerchantRequest);

    Flowable<GetPromotionsResponse> getPromotions(GetPromotionRequest getPromotionRequest);
    Flowable<GetDistrictResponse> getDistricts(BaseRequest getDistrictRequest);

}
