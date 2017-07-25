package vsec.com.yupax.utils;

import java.io.File;

import vsec.com.yupax.app.YupaxApps;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/12/2017.
 */

public class Common {

    public static final int REQUEST_CODE_ASK_PERMISSIONS = 100;

    public static final String PATH_DATA = YupaxApps.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";
    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    public static interface CONTROL_ICON_POSITION {
        public static int ADDRESS = 0;
        public static int NOTIFICATION = 1;
        public static int EXCHANGE = 2;
        public static int PERSONAL = 3;
    }

    public interface SPF {
        public static final String USER_NAME = "user_name";
        public static final String PASS_WORD = "password";
        public static final String TOKEN = "token";
        public static final String SERVER_IP = "server_ip";
        public static final String BACK_PRESS = "back_press";
        public static final String LANGUAGE = "current_language";
        public static final String LANGUAGE_VI = "lang_vi";
        public static final String LANGUAGE_EN = "lang_en";
        public static final String CURRENT_MERCHANT_CODE = "current_merchant_code";
        public static final String CURRENT_MERCHANT_NAME = "current_merchant_name";
        public static final String LAST_NAME = "last_name";
        public static final String FIRST_NAME = "first_name";
        public static final String USER_EMAIL = "user_email";
        public static final String USER_PHONE = "user_phone";
        public static final String SAVE_LOGIN_STATE = "save_login_state";



    }

    public interface HTTP_CODE {
        public static final int CODE_OK = 200;
    }
}
