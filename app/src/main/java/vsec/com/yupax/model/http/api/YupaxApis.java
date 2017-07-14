package vsec.com.yupax.model.http.api;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import vsec.com.yupax.model.http.request.ChangePasswordRequest;
import vsec.com.yupax.model.http.request.LoginRequest;
import vsec.com.yupax.model.http.response.ChangePasswordResponse;
import vsec.com.yupax.model.http.response.LoginResponse;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/24/2017.
 */

public interface YupaxApis {

    @POST("unauth")
    Flowable<LoginResponse> signIn(@Body LoginRequest loginRequest);


    @POST("unauth")
    Flowable<ChangePasswordResponse> changePassword(@Body ChangePasswordRequest changePasswordRequest);
}
