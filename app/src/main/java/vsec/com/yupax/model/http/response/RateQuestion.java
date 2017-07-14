package vsec.com.yupax.model.http.response;

import java.util.ArrayList;

/**
 * Created by nguyenthanhdong0109@gmail.com on 6/7/2017.
 */

public class RateQuestion {

    private String question;

    private ArrayList<RateAnswer> answers;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<RateAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<RateAnswer> answers) {
        this.answers = answers;
    }

    public RateQuestion(String question, ArrayList<RateAnswer> answers) {
        this.question = question;
        this.answers = answers;
    }
}
