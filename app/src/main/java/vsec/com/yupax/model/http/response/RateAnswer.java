package vsec.com.yupax.model.http.response;

/**
 * Created by nguyenthanhdong0109@gmail.com on 6/7/2017.
 */

public class RateAnswer {

    private String hashcode;
    private String questionId;
    private String question;

    public RateAnswer(String question) {
        this.question = question;
    }

    public String getHashcode() {
        return hashcode;
    }

    public void setHashcode(String hashcode) {
        this.hashcode = hashcode;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
