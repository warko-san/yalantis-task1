package ua.warko.yalantistask1.presenter.retrofit;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import ua.warko.yalantistask1.pojo.ContentDataModel;

/**
 * Created by Warko on 11.05.2016.
 */
public interface ApiService {
    @GET("tickets")
    Observable<List<ContentDataModel>> loadCards(@Query("state") String state,
                                                 @Query("amount") int amount);

    @GET("tickets")
    Observable<List<ContentDataModel>> loadMoreCards(@Query("state") String state,
                                                     @Query("amount") int amount,
                                                     @Query("offset") int offset);

}
