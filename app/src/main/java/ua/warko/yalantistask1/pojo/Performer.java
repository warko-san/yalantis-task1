
package ua.warko.yalantistask1.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.Transient;

import io.realm.PerformerRealmProxy;
import io.realm.RealmObject;

@Parcel(implementations = {PerformerRealmProxy.class},
        value = Parcel.Serialization.BEAN,
        analyze = {Performer.class})
public class Performer extends RealmObject {
    @Transient
    @SerializedName("id")
    @Expose
    private int id;
    @Transient
    @SerializedName("organization")
    @Expose
    private String organization;
    @Transient
    @SerializedName("person")
    @Expose
    private String person;
    @Transient
    @SerializedName("deadline")
    @Expose
    private int deadline;


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getOrganization() {
        return organization;
    }


    public void setOrganization(String organization) {
        this.organization = organization;
    }


    public String getPerson() {
        return person;
    }


    public void setPerson(String person) {
        this.person = person;
    }


    public int getDeadline() {
        return deadline;
    }


    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

}
