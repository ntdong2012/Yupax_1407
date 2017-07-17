package vsec.com.yupax.model.http;

import io.reactivex.Flowable;
import vsec.com.yupax.model.http.request.ChangePasswordRequest;
import vsec.com.yupax.model.http.request.LoginRequest;
import vsec.com.yupax.model.http.request.ResendPasswordRequest;
import vsec.com.yupax.model.http.response.ChangePasswordResponse;
import vsec.com.yupax.model.http.response.LoginResponse;
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
    }

    Flowable<LoginResponse> signIn(LoginRequest loginRequest);

    Flowable<Token> resendPassword(ResendPasswordRequest resendPasswordRequest);


    Flowable<ChangePasswordResponse> changePassword(ChangePasswordRequest changePasswordRequest);
//
//    Flowable<LogResponse> getLogByType(String type);
//
//    Flowable<List<RoomRp>> getRooms();
//
//    Flowable<List<RackRp>> getRacksByRoom(String url);
//
//    Flowable<List<SensorRp>> getDevicesByRoom(String url);
//
//    Flowable<RoomDetailRp> getRoomDetail(String url);
//
//    Flowable<List<CamItem>> getCamsByRoom(String url);
//
//    Flowable<List<Command>> getCommandsByHw(String url);
//
//    Flowable<CommandResponse> lightControl(String url, LightRequest lightRequest);
//
//    Flowable<CommandResponse> doorControl(String url, DoorRq lightRequest);
//
//    Flowable<LogResponse> getLogByRoom(String url);
//
//    Flowable<Response<Void>> logout(String url);

}
