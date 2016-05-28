
package ua.warko.yalantistask1.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.Transient;

import io.realm.DistrictRealmProxy;
import io.realm.RealmObject;

@Parcel(implementations = {DistrictRealmProxy.class},
        value = Parcel.Serialization.BEAN,
        analyze = {District.class})
public class District extends RealmObject {
    @Transient
    @SerializedName("id")
    @Expose
    private int id;
    @Transient
    @SerializedName("name")
    @Expose
    private String name;
    @Transient
    @SerializedName("ru_name")
    @Expose
    private String ruName;


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


    public String getRuName() {
        return ruName;
    }


    public void setRuName(String ruName) {
        this.ruName = ruName;
    }

}
