package vsec.com.yupax.model.http.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by nguyenthanhdong0109@gmail.com on 6/7/2017.
 */

public class RateQuestionResponse extends BaseResponse {

    @SerializedName("listSurvey")
    private ArrayList<Rate> rates;
    @SerializedName("pageIndex")
    private int pageIndex;
    @SerializedName("pageSize")
    private int pageSize;


    private ArrayList<RateAnswer> answers;

    public ArrayList<Rate> getRates() {
        return rates;
    }

    public void setRates(ArrayList<Rate> rates) {
        this.rates = rates;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public ArrayList<RateAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<RateAnswer> answers) {
        this.answers = answers;
    }
}
