package vsec.com.yupax.model.http;

import javax.inject.Inject;

import io.reactivex.Flowable;
import vsec.com.yupax.model.http.api.YupaxApis;
import vsec.com.yupax.model.http.request.ChangePasswordRequest;
import vsec.com.yupax.model.http.request.LoginRequest;
import vsec.com.yupax.model.http.request.ResendPasswordRequest;
import vsec.com.yupax.model.http.response.ChangePasswordResponse;
import vsec.com.yupax.model.http.response.LoginResponse;
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
}
