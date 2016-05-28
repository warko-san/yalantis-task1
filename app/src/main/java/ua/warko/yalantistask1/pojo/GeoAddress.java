
package ua.warko.yalantistask1.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.Transient;

import io.realm.GeoAddressRealmProxy;
import io.realm.RealmObject;

@Parcel(implementations = {GeoAddressRealmProxy.class},
        value = Parcel.Serialization.BEAN,
        analyze = {GeoAddress.class})
public class GeoAddress extends RealmObject {
    @Transient
    @SerializedName("id")
    @Expose
    private int id;
    @Transient
    @SerializedName("address")
    @Expose
    private String address;
    @Transient
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @Transient
    @SerializedName("latitude")
    @Expose
    private String latitude;


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public String getLongitude() {
        return longitude;
    }


    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }


    public String getLatitude() {
        return latitude;
    }


    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

}
