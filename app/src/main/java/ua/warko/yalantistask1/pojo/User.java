
package ua.warko.yalantistask1.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.Transient;

import io.realm.RealmObject;
import io.realm.UserRealmProxy;

@Parcel(implementations = {UserRealmProxy.class},
        value = Parcel.Serialization.BEAN,
        analyze = {User.class})
public class User extends RealmObject {
    @Transient
    @SerializedName("id")
    @Expose
    private int id;
    @Transient
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @Transient
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @Transient
    @SerializedName("middle_name")
    @Expose
    private String middleName;
    @Transient
    @SerializedName("email")
    @Expose
    private String email;
    @Transient
    @SerializedName("birthday")
    @Expose
    private int birthday;
    @Transient
    @SerializedName("phone")
    @Expose
    private String phone;
    @Transient
    @SerializedName("address")
    @Expose
    private Address address;
    @Transient
    @SerializedName("fb_registered")
    @Expose
    private int fbRegistered;
    @Transient
    @SerializedName("push_token")
    @Expose
    private String pushToken;
    @Transient
    @SerializedName("device_type")
    @Expose
    private int deviceType;


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getMiddleName() {
        return middleName;
    }


    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public int getBirthday() {
        return birthday;
    }


    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }


    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }


    public Address getAddress() {
        return address;
    }


    public void setAddress(Address address) {
        this.address = address;
    }


    public int getFbRegistered() {
        return fbRegistered;
    }


    public void setFbRegistered(int fbRegistered) {
        this.fbRegistered = fbRegistered;
    }

    public String getPushToken() {
        return pushToken;
    }


    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }


    public int getDeviceType() {
        return deviceType;
    }


    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

}
