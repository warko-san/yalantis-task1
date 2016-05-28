package ua.warko.yalantistask1.util;

import android.os.Parcel;
import android.os.Parcelable;

import org.parceler.Parcels;
import org.parceler.TypeRangeParcelConverter;

import io.realm.RealmList;
import io.realm.RealmObject;
import ua.warko.yalantistask1.pojo.Files;

/**
 * Created by Warko on 22.05.2016.
 */
public class FilesParcelConverter implements TypeRangeParcelConverter
        <RealmList<Files>, RealmList<Files>> {
    private static final int NULL = -1;

    @Override
    public void toParcel(RealmList<Files> input, Parcel parcel) {
        parcel.writeInt(input == null ? NULL : input.size());
        if (input != null) {
            for (RealmObject item : input) {
                parcel.writeParcelable(Parcels.wrap(item), 0);
            }
        }
    }

    @Override
    public RealmList<Files> fromParcel(Parcel parcel) {
        int size = parcel.readInt();
        RealmList<Files> list = new RealmList<>();
        for (int i = 0; i < size; i++) {
            Parcelable parcelable = parcel.readParcelable(getClass().getClassLoader());
            list.add((Files) Parcels.unwrap(parcelable));
        }
        return list;
    }
}
