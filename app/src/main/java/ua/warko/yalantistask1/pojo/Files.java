package ua.warko.yalantistask1.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.Transient;

import io.realm.FilesRealmProxy;
import io.realm.RealmObject;

/**
 * Created by Warko on 18.05.2016.
 */
@Parcel(implementations = {FilesRealmProxy.class},
        value = Parcel.Serialization.BEAN,
        analyze = {Files.class})
public class Files extends RealmObject {
    @Transient
    @SerializedName("id")
    @Expose
    private int id;
    @Transient
    @SerializedName("name")
    @Expose
    private String name;
    @Transient
    @SerializedName("filename")
    @Expose
    private String filename;


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


    public String getFilename() {
        return filename;
    }


    public void setFilename(String filename) {
        this.filename = filename;
    }

}
