package vsec.com.yupax.model.http.request;

/**
 * Created by nguyenthanhdong0109@gmail.com on 7/23/17.
 */

public class ActiveUser {

    private String username;
    private String activeCode;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getActiveCode() {
        return activeCode;
    }

    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode;
    }
}
