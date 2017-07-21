package vsec.com.yupax.model.http;

import javax.inject.Inject;

import io.reactivex.Flowable;
import vsec.com.yupax.model.http.api.YupaxApis;
import vsec.com.yupax.model.http.request.BaseRequest;
import vsec.com.yupax.model.http.request.ChangePasswordRequest;
import vsec.com.yupax.model.http.request.ListStoreRequest;
import vsec.com.yupax.model.http.request.LoginRequest;
import vsec.com.yupax.model.http.request.MerchantListRequest;
import vsec.com.yupax.model.http.request.ResendPasswordRequest;
import vsec.com.yupax.model.http.request.StoreDetailRequest;
import vsec.com.yupax.model.http.response.ChangePasswordResponse;
import vsec.com.yupax.model.http.response.GetCategoriesResponse;
import vsec.com.yupax.model.http.response.GetProvincesResponse;
import vsec.com.yupax.model.http.response.ListStoreResponse;
import vsec.com.yupax.model.http.response.LoginResponse;
import vsec.com.yupax.model.http.response.MerchantListResponse;
import vsec.com.yupax.model.http.response.StoreDetailResponse;
import vsec.com.yupax.model.http.response.Token;

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
    public Flowable<LoginResponse> signIn(LoginRequest loginRequest) {
        return yupaxApis.signIn(loginRequest);
    }

    @Override
    public Flowable<Token> resendPassword(ResendPasswordRequest resendPasswordRequest) {
        return null;
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
}
