package ua.warko.yalantistask1.pojo.realm;

import java.util.Set;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Warko on 24.05.2016.
 */
public class UserAccessToken extends RealmObject {
    @PrimaryKey
    private String accessToken;
    private String appId;
    private String userId;
    private RealmList<RealmString> permissions;
    private RealmList<RealmString> declinedPermissions;
    private String tokenSource;
    private long expirationDate;
    private long lastRefreshTime;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public RealmList<RealmString> getPermissions() {
        return permissions;
    }

    public void setPermissions(RealmList<RealmString> permissions) {
        this.permissions = permissions;
    }

    public void setPermissions(Set<String> permissions) {
        RealmList<RealmString> realmStrings = new RealmList<>();
        for (String item : permissions) {
            RealmString string = new RealmString();
            string.setPermission(item);
            realmStrings.add(string);
        }
        this.permissions = realmStrings;
    }

    public RealmList<RealmString> getDeclinedPermissions() {
        return declinedPermissions;
    }

    public void setDeclinedPermissions(RealmList<RealmString> declinedPermissions) {
        this.declinedPermissions = declinedPermissions;
    }

    public void setDeclinedPermissions(Set<String> permissions) {
        RealmList<RealmString> realmStrings = new RealmList<>();
        for (String item : permissions) {
            RealmString string = new RealmString();
            string.setPermission(item);
            realmStrings.add(string);
        }
        this.declinedPermissions = realmStrings;
    }

    public String getTokenSource() {
        return tokenSource;
    }

    public void setTokenSource(String tokenSource) {
        this.tokenSource = tokenSource;
    }

    public long getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(long expirationDate) {
        this.expirationDate = expirationDate;
    }

    public long getLastRefreshTime() {
        return lastRefreshTime;
    }

    public void setLastRefreshTime(long lastRefreshTime) {
        this.lastRefreshTime = lastRefreshTime;
    }
}
