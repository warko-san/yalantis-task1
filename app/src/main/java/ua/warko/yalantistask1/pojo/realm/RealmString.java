package ua.warko.yalantistask1.pojo.realm;

import io.realm.RealmObject;

/**
 * Created by Warko on 26.05.2016.
 */
public class RealmString extends RealmObject {

    private String permission;

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
