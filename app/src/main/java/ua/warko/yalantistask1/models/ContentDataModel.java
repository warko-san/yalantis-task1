package ua.warko.yalantistask1.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Warko on 13.04.2016.
 */
public class ContentDataModel implements Parcelable {
    private String mCreationDate, mRegisteredDate, mDeadline, mHeader, mAddress, mMainText, mMarker,
            mDays;
    private int mCounter;

    public ContentDataModel(int counter, String header,
                            String address, String creationDate, String days, String registeredDate,
                            String deadline, String mainText, String marker) {


        mCounter = counter;
        mHeader = header;
        mAddress = address;
        mCreationDate = creationDate;
        mRegisteredDate = registeredDate;
        mDeadline = deadline;
        mMainText = mainText;
        mMarker = marker;
        mDays = days;
    }

    public ContentDataModel(Parcel in) {
        mCounter = in.readInt();
        mHeader = in.readString();
        mAddress = in.readString();
        mCreationDate = in.readString();
        mRegisteredDate = in.readString();
        mDeadline = in.readString();
        mMainText = in.readString();
        mMarker = in.readString();
        mDays = in.readString();

    }

    public String getHeader() {
        return mHeader;
    }

    public int getCounter() {
        return mCounter;
    }

    public String getAddress() {
        return mAddress;
    }

    public String getCreationDate() {
        return mCreationDate;
    }

    public String getDays() {
        return mDays;
    }

    public String getRegisteredDate() {
        return mRegisteredDate;
    }

    public String getDeadline() {
        return mDeadline;
    }

    public String getMainText() {
        return mMainText;
    }

    public String getMarker() {
        return mMarker;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mCounter);
        dest.writeString(mHeader);
        dest.writeString(mAddress);
        dest.writeString(mCreationDate);
        dest.writeString(mRegisteredDate);
        dest.writeString(mDeadline);
        dest.writeString(mMainText);
        dest.writeString(mMarker);
        dest.writeString(mDays);
    }

    public static final Parcelable.Creator<ContentDataModel> CREATOR
            = new Parcelable.Creator<ContentDataModel>() {
        @Override
        public ContentDataModel createFromParcel(Parcel in) {
            return new ContentDataModel(in);
        }

        @Override
        public ContentDataModel[] newArray(int size) {
            return new ContentDataModel[size];
        }
    };
}
