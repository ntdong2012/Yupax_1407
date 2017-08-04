package vsec.com.yupax.model.http.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ntdong2012 on 8/4/2017.
 */

public class Rate {

    @SerializedName("hashcode")
    private String hashcode;
    @SerializedName("answerId")
    private String answerId;
    @SerializedName("answer")
    private String answer;
    @SerializedName("questions")
    private ArrayList<RateAnswer> questions;

    public Rate(String answer, ArrayList<RateAnswer> questions) {
        this.answer = answer;
        this.questions = questions;
    }

    public Rate() {
    }

    public String getHashcode() {
        return hashcode;
    }

    public void setHashcode(String hashcode) {
        this.hashcode = hashcode;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public ArrayList<RateAnswer> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<RateAnswer> questions) {
        this.questions = questions;
    }
}
