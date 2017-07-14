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
    public String getCurrentMerchant() {
        return mSPrefs.getString(Common.SPF.CURRENT_MERCHANT, Common.SPF.YUPAX_MERCHANT);
    }

    @Override
    public void setCurrentMerchant(String str) {
        mSPrefs.edit().putString(Common.SPF.CURRENT_MERCHANT, str).commit();
    }
}
