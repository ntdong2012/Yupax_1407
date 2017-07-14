package vsec.com.yupax.di.view;

import android.widget.ProgressBar;

/**
 * Created by nguyenthanhdong0109@gmail.com on 5/12/2017.
 */

public interface BaseView {

    void showProcess(ProgressBar progressBar);

    void hiddenProcess(ProgressBar progressBar);

    void onError();
}
