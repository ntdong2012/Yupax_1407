package vsec.com.yupax.model.prefs;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/21/17.
 */

public interface PreferencesHelper {

    void setUserName(String userName);

    String getUserName();

    void setPassword(String password);

    String getPassword();

    void setToken(String token);

    String getToken();

    void setBaseUrl(String baseUrl);

    String getBaseUrl();

    void setLanguage(String lang);

    String getLanguage();

    String getCurrentMerchantCode();

    void setCurrentMerchantCode(String str);

    void setCurrentMerchantName(String str);

    String getCurrentMerchantName();

    void saveFirstName(String str);

    String getFirstName();

    void saveLastName(String str);

    String getLastName();

    void saveEmail(String email);

    String getEmail();

    void savePhone(String phone);

    String getPhone();

    void saveLoginState(boolean isSave);

    boolean getSaveLoginState();

//    String getAddress();
//    String getBirthDay();
//    String getProvince();
//    String getDistrict();
//    String get

}
