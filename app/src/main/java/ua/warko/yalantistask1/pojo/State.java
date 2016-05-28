
package ua.warko.yalantistask1.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.Transient;

import io.realm.RealmObject;
import io.realm.StateRealmProxy;

@Parcel(implementations = {StateRealmProxy.class},
        value = Parcel.Serialization.BEAN,
        analyze = {State.class})
public class State extends RealmObject {
    @Transient
    @SerializedName("id")
    @Expose
    public int id;
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
