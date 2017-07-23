package vsec.com.yupax.model.http.api;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.POST;
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
 * Created by nguyenthanhdong0109@gmail.com on 5/24/2017.
 */

public interface YupaxApis {

    @POST("unauth")
    Flowable<LoginResponse> signIn(@Body LoginRequest loginRequest);


    @POST("unauth")
    Flowable<ChangePasswordResponse> changePassword(@Body ChangePasswordRequest changePasswordRequest);

    @POST("auth")
    Flowable<MerchantListResponse> getMerchants(@Body MerchantListRequest merchantListRequest);


    @POST("auth")
    Flowable<BaseResponse> registerUserToMerchant(@Body RegisterUserToMerchantRequest registerUserToMerchantRequest);

    @POST("auth-merchant")
    Flowable<ListStoreResponse> getListStores(@Body ListStoreRequest listStoreRequest);

    @POST("auth-merchant")
    Flowable<StoreDetailResponse> getStoreDetail(@Body StoreDetailRequest storeDetailRequest);

    @POST("service")
    Flowable<GetCategoriesResponse> getCategories(@Body BaseRequest baseRequest);

    @POST("service")
    Flowable<GetProvincesResponse> getProvinces(@Body BaseRequest baseRequest);

    @POST("auth-merchant")
    Flowable<ListNewsResponse> getNews(@Body NewsRequest newsRequest);

    @POST("auth-merchant")
    Flowable<GetPromotionsResponse> getPromotions(@Body GetPromotionRequest getPromotionRequest);

    @POST("unauth")
    Flowable<BaseResponse> registerUser(@Body CreateUserRequest createUserRequest);

    @POST("unauth")
    Flowable<BaseResponse> resendActiveCode(@Body ResendPasswordRequest resendPasswordRequest);

    @POST("unauth")
    Flowable<ActiveUserResponse> activeUser(@Body ActiveUserRequest activeUserRequest);



}
