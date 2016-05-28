package ua.warko.yalantistask1.pojo.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Warko on 27.05.2016.
 */
public class RealmUser extends RealmObject {
    @PrimaryKey
    private String userId;
    private String userFirstName;
    private String userLastName;
    private String userPicUri;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserPicUri() {
        return userPicUri;
    }

    public void setUserPicUri(String userPicUri) {
        this.userPicUri = userPicUri;
    }
}
