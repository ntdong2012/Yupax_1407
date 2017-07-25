package vsec.com.yupax.model.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import vsec.com.yupax.BuildConfig;
import vsec.com.yupax.app.YupaxApps;
import vsec.com.yupax.utils.Common;
import vsec.com.yupax.utils.log.DLog;


/**
 * Created by nguyenthanhdong0109@gmail.com on 5/21/17.
 */

public class PreferencesHelperImpl implements PreferencesHelper {

    private static final String SHAREDPREFERENCES_NAME = "bts_sp";

    private final SharedPreferences mSPrefs;

    @Inject
    public PreferencesHelperImpl() {
        mSPrefs = YupaxApps.getInstance().getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public void setUserName(String userName) {
        mSPrefs.edit().putString(Common.SPF.USER_NAME, userName).apply();
    }

    @Override
    public String getUserName() {
        return mSPrefs.getString(Common.SPF.USER_NAME, "");
    }

    @Override
    public void setPassword(String password) {
        mSPrefs.edit().putString(Common.SPF.PASS_WORD, password).apply();
    }

    @Override
    public String getPassword() {
        return mSPrefs.getString(Common.SPF.PASS_WORD, "");
    }

    @Override
    public void setToken(String token) {
        mSPrefs.edit().putString(Common.SPF.TOKEN, token).apply();
    }

    @Override
    public String getToken() {
        return mSPrefs.getString(Common.SPF.TOKEN, "");
    }

    @Override
    public void setBaseUrl(String baseUrl) {
        mSPrefs.edit().putString(Common.SPF.SERVER_IP, baseUrl).apply();
    }

    @Override
    public String getBaseUrl() {
        String baseUrl = mSPrefs.getString(Common.SPF.SERVER_IP, BuildConfig.BASEURL);

        if (!baseUrl.startsWith("http")) {
            baseUrl = "http://" + baseUrl + "/";
        }
        DLog.d(baseUrl);
        return baseUrl;

    }

    @Override
    public void setLanguage(String lang) {
        mSPrefs.edit().putString(Common.SPF.LANGUAGE, lang).commit();
    }

    @Override
    public String getLanguage() {
        return mSPrefs.getString(Common.SPF.LANGUAGE, Common.SPF.LANGUAGE_VI);
    }

    @Override
    public String getCurrentMerchantCode() {
        return mSPrefs.getString(Common.SPF.CURRENT_MERCHANT_CODE, "");
    }

    @Override
    public void setCurrentMerchantCode(String str) {
        mSPrefs.edit().putString(Common.SPF.CURRENT_MERCHANT_CODE, str).apply();
    }

    @Override
    public void setCurrentMerchantName(String str) {
        mSPrefs.edit().putString(Common.SPF.CURRENT_MERCHANT_NAME, str).apply();
    }

    @Override
    public String getCurrentMerchantName() {
        return mSPrefs.getString(Common.SPF.CURRENT_MERCHANT_NAME, "");
    }

    @Override
    public void saveFirstName(String str) {
        mSPrefs.edit().putString(Common.SPF.FIRST_NAME, str).apply();
    }

    @Override
    public String getFirstName() {
        return mSPrefs.getString(Common.SPF.FIRST_NAME, "");
    }

    @Override
    public void saveLastName(String str) {
        mSPrefs.edit().putString(Common.SPF.LAST_NAME, str).apply();
    }

    @Override
    public String getLastName() {
        return mSPrefs.getString(Common.SPF.LAST_NAME, "");
    }

    @Override
    public void saveEmail(String email) {
        mSPrefs.edit().putString(Common.SPF.USER_EMAIL, email).apply();
    }

    @Override
    public String getEmail() {
        return mSPrefs.getString(Common.SPF.USER_EMAIL, "");
    }

    @Override
    public void savePhone(String phone) {
        mSPrefs.edit().putString(Common.SPF.USER_PHONE, phone).apply();
    }

    @Override
    public String getPhone() {
        return mSPrefs.getString(Common.SPF.USER_PHONE, "");
    }

    @Override
    public void saveLoginState(boolean isSave) {
        mSPrefs.edit().putBoolean(Common.SPF.SAVE_LOGIN_STATE, isSave).apply();
    }

    @Override
    public boolean getSaveLoginState() {
        return mSPrefs.getBoolean(Common.SPF.SAVE_LOGIN_STATE, false);
    }
}
