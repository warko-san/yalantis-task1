
package ua.warko.yalantistask1.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.Transient;

import io.realm.AddressRealmProxy;
import io.realm.RealmObject;

@Parcel(implementations = {AddressRealmProxy.class},
        value = Parcel.Serialization.BEAN,
        analyze = {Address.class})
public class Address extends RealmObject {
    @Transient
    @SerializedName("id")
    @Expose
    private int id;
    @Transient
    @SerializedName("district")
    @Expose
    private District district;
    @Transient
    @SerializedName("city")
    @Expose
    private City city;
    @Transient
    @SerializedName("street")
    @Expose
    private Street street;
    @Transient
    @SerializedName("house")
    @Expose
    private House house;
    @Transient
    @SerializedName("flat")
    @Expose
    private String flat;


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public District getDistrict() {
        return district;
    }


    public void setDistrict(District district) {
        this.district = district;
    }


    public City getCity() {
        return city;
    }


    public void setCity(City city) {
        this.city = city;
    }


    public Street getStreet() {
        return street;
    }


    public void setStreet(Street street) {
        this.street = street;
    }


    public House getHouse() {
        return house;
    }


    public void setHouse(House house) {
        this.house = house;
    }


    public String getFlat() {
        return flat;
    }


    public void setFlat(String flat) {
        this.flat = flat;
    }

}
