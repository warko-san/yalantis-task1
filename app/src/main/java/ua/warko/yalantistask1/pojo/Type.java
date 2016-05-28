
package ua.warko.yalantistask1.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.Transient;

import io.realm.RealmObject;
import io.realm.TypeRealmProxy;

@Parcel(implementations = {TypeRealmProxy.class},
        value = Parcel.Serialization.BEAN,
        analyze = {Type.class})
public class Type extends RealmObject {
    @Transient
    @SerializedName("id")
    @Expose
    private int id;
    @Transient
    @SerializedName("name")
    @Expose
    private String name;


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

}
