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
}
