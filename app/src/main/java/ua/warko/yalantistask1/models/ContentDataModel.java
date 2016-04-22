package ua.warko.yalantistask1.models;

import java.io.Serializable;

/**
 * Created by Warko on 13.04.2016.
 */
public class ContentDataModel implements Serializable {
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

}
