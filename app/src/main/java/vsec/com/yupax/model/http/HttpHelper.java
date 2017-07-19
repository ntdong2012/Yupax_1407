package vsec.com.yupax.model.http;

import io.reactivex.Flowable;
import vsec.com.yupax.model.http.request.ChangePasswordRequest;
import vsec.com.yupax.model.http.request.LoginRequest;
import vsec.com.yupax.model.http.request.MerchantListRequest;
import vsec.com.yupax.model.http.request.ResendPasswordRequest;
import vsec.com.yupax.model.http.response.ChangePasswordResponse;
import vsec.com.yupax.model.http.response.LoginResponse;
import vsec.com.yupax.model.http.response.MerchantListResponse;
import vsec.com.yupax.model.http.response.Token;

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
    }

    Flowable<LoginResponse> signIn(LoginRequest loginRequest);

    Flowable<Token> resendPassword(ResendPasswordRequest resendPasswordRequest);

    Flowable<ChangePasswordResponse> changePassword(ChangePasswordRequest changePasswordRequest);

    Flowable<MerchantListResponse> getListMerchant(MerchantListRequest merchantListRequest);

}
