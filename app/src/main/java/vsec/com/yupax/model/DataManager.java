package vsec.com.yupax.model;

import io.reactivex.Flowable;
import vsec.com.yupax.model.http.HttpHelper;
import vsec.com.yupax.model.http.request.ActiveUserRequest;
import vsec.com.yupax.model.http.request.BaseRequest;
import vsec.com.yupax.model.http.request.ChangePasswordRequest;
import vsec.com.yupax.model.http.request.CreateUserRequest;
import vsec.com.yupax.model.http.request.GetNewsRequest;
import vsec.com.yupax.model.http.request.GetPromotionRequest;
import vsec.com.yupax.model.http.request.GetUserInfoRequest;
import vsec.com.yupax.model.http.request.ListStoreRequest;
import vsec.com.yupax.model.http.request.LoginRequest;
import vsec.com.yupax.model.http.request.MerchantListRequest;
import vsec.com.yupax.model.http.request.RateAnswerListRequest;
import vsec.com.yupax.model.http.request.RateRequest;
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
import vsec.com.yupax.model.http.response.RateQuestionResponse;
import vsec.com.yupax.model.http.response.StoreDetailResponse;
import vsec.com.yupax.model.http.response.UserInfoResponse;
import vsec.com.yupax.model.prefs.PreferencesHelper;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/20/17.
 */

public class DataManager implements HttpHelper, PreferencesHelper {

    private HttpHelper httpHelper;
    private PreferencesHelper preferencesHelper;

    public DataManager(HttpHelper httpHelper, PreferencesHelper preferencesHelper) {
        this.httpHelper = httpHelper;
        this.preferencesHelper = preferencesHelper;
    }

    @Override
    public Flowable<BaseResponse> updateUserInfo(UserInfoChangeRequest userInfoChangeRequest) {
        return httpHelper.updateUserInfo(userInfoChangeRequest);
    }

    @Override
    public Flowable<LoginResponse> signIn(LoginRequest loginRequest) {
        return httpHelper.signIn(loginRequest);
    }

    @Override
    public Flowable<BaseResponse> resendPassword(ResendPasswordRequest resendPasswordRequest) {
        return httpHelper.resendPassword(resendPasswordRequest);
    }

    @Override
    public Flowable<ChangePasswordResponse> changePassword(ChangePasswordRequest changePasswordRequest) {
        return httpHelper.changePassword(changePasswordRequest);
    }

    @Override
    public Flowable<MerchantListResponse> getListMerchant(MerchantListRequest merchantListRequest) {
        return httpHelper.getListMerchant(merchantListRequest);
    }

    @Override
    public Flowable<ListStoreResponse> getListStoreBrand(ListStoreRequest listStoreRequest) {
        return httpHelper.getListStoreBrand(listStoreRequest);
    }

    @Override
    public Flowable<StoreDetailResponse> getStoreDetail(StoreDetailRequest storeDetailRequest) {
        return httpHelper.getStoreDetail(storeDetailRequest);
    }

    @Override
    public Flowable<GetCategoriesResponse> getCategories(BaseRequest baseRequest) {
        return httpHelper.getCategories(baseRequest);
    }

    @Override
    public Flowable<GetUserInfoResponse> getUserInfo(GetUserInfoRequest getUserInfoRequest) {
        return httpHelper.getUserInfo(getUserInfoRequest);
    }

    @Override
    public Flowable<GetProvincesResponse> getProvinces(BaseRequest baseRequest) {
        return httpHelper.getProvinces(baseRequest);
    }

    @Override
    public Flowable<ListNewsResponse> getNews(GetNewsRequest newsRequest) {
        return httpHelper.getNews(newsRequest);
    }

    @Override
    public Flowable<BaseResponse> registerUser(CreateUserRequest createUserRequest) {
        return httpHelper.registerUser(createUserRequest);
    }

    @Override
    public Flowable<ActiveUserResponse> activeUser(ActiveUserRequest activeUserRequest) {
        return httpHelper.activeUser(activeUserRequest);
    }

    @Override
    public Flowable<BaseResponse> registerUserToMerchant(RegisterUserToMerchantRequest registerUserToMerchantRequest) {
        return httpHelper.registerUserToMerchant(registerUserToMerchantRequest);
    }


    @Override
    public void setUserName(String userName) {
        preferencesHelper.setUserName(userName);
    }

    @Override
    public String getUserName() {
        return preferencesHelper.getUserName();
    }

    @Override
    public void setPassword(String password) {
        preferencesHelper.setPassword(password);
    }

    @Override
    public String getPassword() {
        return preferencesHelper.getPassword();
    }

    @Override
    public void setToken(String token) {
        preferencesHelper.setToken(token);
    }

    @Override
    public String getToken() {
        return preferencesHelper.getToken();
    }

    @Override
    public void setBaseUrl(String baseUrl) {
        preferencesHelper.setBaseUrl(baseUrl);
    }

    @Override
    public String getBaseUrl() {
        return preferencesHelper.getBaseUrl();
    }


    @Override
    public void setLanguage(String lang) {
        preferencesHelper.setLanguage(lang);
    }

    @Override
    public String getLanguage() {
        return preferencesHelper.getLanguage();
    }

    @Override
    public String getCurrentMerchantCode() {
        return preferencesHelper.getCurrentMerchantCode();
    }

    @Override
    public void setCurrentMerchantCode(String str) {
        preferencesHelper.setCurrentMerchantCode(str);
    }

    @Override
    public void setCurrentMerchantName(String str) {
        preferencesHelper.setCurrentMerchantName(str);
    }

    @Override
    public String getCurrentMerchantName() {
        return preferencesHelper.getCurrentMerchantName();
    }

    @Override
    public void saveFirstName(String str) {
        preferencesHelper.saveFirstName(str);
    }

    @Override
    public String getFirstName() {
        return preferencesHelper.getFirstName();
    }

    @Override
    public void saveLastName(String str) {
        preferencesHelper.saveLastName(str);
    }

    @Override
    public String getLastName() {
        return preferencesHelper.getLastName();
    }

    @Override
    public void saveEmail(String email) {
        preferencesHelper.saveEmail(email);
    }

    @Override
    public String getEmail() {
        return preferencesHelper.getEmail();
    }

    @Override
    public void savePhone(String phone) {
        preferencesHelper.savePhone(phone);
    }

    @Override
    public String getPhone() {
        return preferencesHelper.getPhone();
    }

    @Override
    public void saveLoginState(boolean isSave) {
        preferencesHelper.saveLoginState(isSave);
    }

    @Override
    public boolean getSaveLoginState() {
        return preferencesHelper.getSaveLoginState();
    }


    @Override
    public Flowable<GetPromotionsResponse> getPromotions(GetPromotionRequest getPromotionRequest) {
        return httpHelper.getPromotions(getPromotionRequest);
    }

    @Override
    public Flowable<GetDistrictResponse> getDistricts(BaseRequest getDistrictRequest) {
        return httpHelper.getDistricts(getDistrictRequest);
    }

    @Override
    public Flowable<RateQuestionResponse> getRateQuestionList(RateRequest rateRequest) {
        return httpHelper.getRateQuestionList(rateRequest);
    }

    @Override
    public Flowable<BaseResponse> sendRateAnswers(RateAnswerListRequest rateAnswerListRequest) {
        return httpHelper.sendRateAnswers(rateAnswerListRequest);
    }

    public void onSaveUserInfo(UserInfoResponse userInfoResponse) {
        preferencesHelper.setUserName(userInfoResponse.getUsername());
        preferencesHelper.setToken(userInfoResponse.getToken());
        preferencesHelper.saveLastName(userInfoResponse.getLastName());
        preferencesHelper.saveFirstName(userInfoResponse.getFirstName());
        preferencesHelper.saveEmail(userInfoResponse.getEmail());
        preferencesHelper.savePhone(userInfoResponse.getMobile());

    }

}
