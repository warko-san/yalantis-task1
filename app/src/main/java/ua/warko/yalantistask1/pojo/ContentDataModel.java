
package ua.warko.yalantistask1.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelPropertyConverter;
import org.parceler.Transient;

import io.realm.ContentDataModelRealmProxy;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import ua.warko.yalantistask1.util.DateFormatter;
import ua.warko.yalantistask1.util.FilesParcelConverter;
import ua.warko.yalantistask1.util.PerformersParcelConverter;

@Parcel(implementations = {ContentDataModelRealmProxy.class},
        value = Parcel.Serialization.BEAN,
        analyze = {ContentDataModel.class})
public class ContentDataModel extends RealmObject {
    @Transient
    @SerializedName("id")
    @Expose
    @PrimaryKey
    private int id;
    @Transient
    @SerializedName("user")
    @Expose
    private User user;
    @Transient
    @SerializedName("geo_address")
    @Expose
    private GeoAddress geoAddress;
    @Transient
    @SerializedName("category")
    @Expose
    private Category category;
    @Transient
    @SerializedName("type")
    @Expose
    private Type type;
    @Transient
    @SerializedName("title")
    @Expose
    private String title;
    @Transient
    @SerializedName("body")
    @Expose
    private String body;
    @Transient
    @SerializedName("created_date")
    @Expose
    private int createdDate;
    @Transient
    @SerializedName("start_date")
    @Expose
    private int startDate;
    @Transient
    @SerializedName("state")
    @Expose
    private State state;
    @Transient
    @SerializedName("ticket_id")
    @Expose
    private String ticketId;
    @Transient
    @SerializedName("files")
    @Expose
    private RealmList<Files> files = new RealmList<>();
    @Transient
    @SerializedName("performers")
    @Expose
    private RealmList<Performer> performers = new RealmList<>();
    @Transient
    @SerializedName("deadline")
    @Expose
    private int deadline;
    @Transient
    @SerializedName("likes_counter")
    @Expose
    private int likesCounter;


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }


    public GeoAddress getGeoAddress() {
        return geoAddress;
    }


    public void setGeoAddress(GeoAddress geoAddress) {
        this.geoAddress = geoAddress;
    }


    public Category getCategory() {
        return category;
    }


    public void setCategory(Category category) {
        this.category = category;
    }


    public Type getType() {
        return type;
    }


    public void setType(Type type) {
        this.type = type;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getBody() {
        return body;
    }


    public void setBody(String body) {
        this.body = body;
    }


    public int getCreatedDate() {
        return createdDate;
    }


    public void setCreatedDate(int createdDate) {
        this.createdDate = createdDate;
    }


    public int getStartDate() {
        return startDate;
    }


    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }


    public State getState() {
        return state;
    }


    public void setState(State state) {
        this.state = state;
    }


    public String getTicketId() {
        return ticketId;
    }


    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }


    public RealmList<Files> getFiles() {
        return files;
    }

    @ParcelPropertyConverter(FilesParcelConverter.class)
    public void setFiles(RealmList<Files> files) {
        this.files = files;
    }


    public RealmList<Performer> getPerformers() {
        return performers;
    }

    @ParcelPropertyConverter(PerformersParcelConverter.class)
    public void setPerformers(RealmList<Performer> performers) {
        this.performers = performers;
    }


    public int getDeadline() {
        return deadline;
    }


    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }


    public int getLikesCounter() {
        return likesCounter;
    }


    public void setLikesCounter(int likesCounter) {
        this.likesCounter = likesCounter;
    }

    public String getNormalDate() {
        return DateFormatter.getNormalDate(getStartDate());
    }

    public String getDays() {
        if (getDeadline() == 0) {
            return "";
        } else
            return DateFormatter.getDaysLeft(getDeadline());
    }

}
