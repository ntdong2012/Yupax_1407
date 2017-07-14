package vsec.com.yupax.base;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/24/2017.
 */

public interface BaseView {

    void showErrorMsg(String msg);

    void useNightMode(boolean isNight);

    void useLanguage(String language);

    //=======  State  =======
    void stateError();;

    void stateLoading();

    void stateMain();
}
