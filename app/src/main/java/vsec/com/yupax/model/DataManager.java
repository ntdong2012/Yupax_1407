package vsec.com.yupax.model;

import io.reactivex.Flowable;
import vsec.com.yupax.model.http.HttpHelper;
import vsec.com.yupax.model.http.request.ChangePasswordRequest;
import vsec.com.yupax.model.http.request.LoginRequest;
import vsec.com.yupax.model.http.request.ResendPasswordRequest;
import vsec.com.yupax.model.http.response.ChangePasswordResponse;
import vsec.com.yupax.model.http.response.LoginResponse;
import vsec.com.yupax.model.http.response.Token;
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
    public Flowable<LoginResponse> signIn(LoginRequest loginRequest) {
        return httpHelper.signIn(loginRequest);
    }

    @Override
    public Flowable<Token> resendPassword(ResendPasswordRequest resendPasswordRequest) {
        return httpHelper.resendPassword(resendPasswordRequest);
    }

    @Override
    public Flowable<ChangePasswordResponse> changePassword(ChangePasswordRequest changePasswordRequest) {
        return httpHelper.changePassword(changePasswordRequest);
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


}
