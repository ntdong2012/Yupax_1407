package vsec.com.yupax.base.contract;

import vsec.com.yupax.base.BasePresenter;
import vsec.com.yupax.base.BaseView;
import vsec.com.yupax.model.http.response.RateQuestionResponse;

/**
 * Created by nguyenthanhdong0109@gmail.com on 6/7/2017.
 */

public interface RateContract {

    interface View extends BaseView {

        void getRateQuestionSuccess(RateQuestionResponse rateQuestionResponse);

        void onLoading();

        void onStopLoading();

    }

    interface Presenter extends BasePresenter<View> {

        void getRateQuestions();
    }
}
