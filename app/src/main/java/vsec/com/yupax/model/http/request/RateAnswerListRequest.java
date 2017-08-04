package vsec.com.yupax.model.http.request;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ntdong2012 on 8/4/2017.
 */

public class RateAnswerListRequest extends BaseRequest{


    @SerializedName("listQuestion")
    private ArrayList<RateAnswerRequest> listQuestion;
    @SerializedName("token")
    private String token;

    public ArrayList<RateAnswerRequest> getListQuestion() {
        return listQuestion;
    }

    public void setListQuestion(ArrayList<RateAnswerRequest> listQuestion) {
        this.listQuestion = listQuestion;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
