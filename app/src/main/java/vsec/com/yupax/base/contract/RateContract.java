package vsec.com.yupax.base.contract;

import java.util.ArrayList;

import vsec.com.yupax.base.BasePresenter;
import vsec.com.yupax.base.BaseView;
import vsec.com.yupax.model.http.response.BaseResponse;
import vsec.com.yupax.model.http.response.Rate;
import vsec.com.yupax.model.http.response.RateQuestionResponse;

/**
 * Created by nguyenthanhdong0109@gmail.com on 6/7/2017.
 */

public interface RateContract {

    interface View extends BaseView {

        void getRateQuestionSuccess(RateQuestionResponse rateQuestionResponse);

        void sendRateAnswersSuccess(BaseResponse baseResponse);

        void onLoading();

        void onStopLoading();

    }

    interface Presenter extends BasePresenter<View> {

        void getRateQuestions();

        void sendRateAnswer(ArrayList<Rate> rates);
    }
}
