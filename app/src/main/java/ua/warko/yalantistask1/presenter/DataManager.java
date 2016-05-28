package ua.warko.yalantistask1.presenter;

import com.facebook.AccessToken;
import com.facebook.Profile;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import ua.warko.yalantistask1.pojo.ContentDataModel;
import ua.warko.yalantistask1.pojo.realm.RealmUser;
import ua.warko.yalantistask1.pojo.realm.UserAccessToken;
import ua.warko.yalantistask1.presenter.retrofit.ApiModule;
import ua.warko.yalantistask1.presenter.retrofit.ApiService;
import ua.warko.yalantistask1.util.Constants;

/**
 * Created by Warko on 21.05.2016.
 */
public class DataManager {
    ApiService apiService = ApiModule.getApiService();

    public void loadData(int page) {
        switch (page) {
            case Constants.STATE_PROCESSING:
                loadData(Constants.TAB_ONE_STATE, Constants.QUERY_AMOUNT);
                break;
            case Constants.STATE_DONE:
                loadData(Constants.TAB_TWO_STATE, Constants.QUERY_AMOUNT);
                break;
            case Constants.STATE_PENDING:
                loadData(Constants.TAB_THREE_STATE, Constants.QUERY_AMOUNT);
                break;
        }
    }

    public void loadData(String state, int amount) {
        Observable<List<ContentDataModel>> tabContent = apiService.loadCards(state, amount);
        tabContent
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ContentDataModel>>() {
                    @Override
                    public void onCompleted() {


                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<ContentDataModel> models) {
                        Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();
                        realm.copyToRealmOrUpdate(models);
                        realm.commitTransaction();
                        realm.close();
                    }
                });
    }

    public void loadMoreCards(String state, int amount, int offset) {
        Observable<List<ContentDataModel>> query = apiService.loadMoreCards(state, amount, offset);
        query.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ContentDataModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<ContentDataModel> models) {
                        Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();
                        realm.copyToRealmOrUpdate(models);
                        realm.commitTransaction();
                        realm.close();
                    }
                });

    }

    public RealmResults<ContentDataModel> findByState(Class<ContentDataModel> tClass, int state) {
        Realm realm = Realm.getDefaultInstance();
        switch (state) {
            case Constants.STATE_PROCESSING:
                return realm.where(tClass)
                        .equalTo(Constants.STATE, Constants.PROCESSING_ZERO).or()
                        .equalTo(Constants.STATE, Constants.PROCESSING_NINE).or()
                        .equalTo(Constants.STATE, Constants.PROCESSING_FIVE).or()
                        .equalTo(Constants.STATE, Constants.PROCESSING_SEVEN).or()
                        .equalTo(Constants.STATE, Constants.PROCESSING_EIGHT)
                        .findAllAsync();
            case Constants.STATE_DONE:
                return realm.where(tClass)
                        .equalTo(Constants.STATE, Constants.DONE_TEN).or()
                        .equalTo(Constants.STATE, Constants.DONE_SIX)
                        .findAllAsync();
            case Constants.STATE_PENDING:
                return realm.where(tClass)
                        .equalTo(Constants.STATE, Constants.PENDING_ONE).or()
                        .equalTo(Constants.STATE, Constants.PENDING_THREE).or()
                        .equalTo(Constants.STATE, Constants.PENDING_FOUR)
                        .findAllAsync();

        }
        realm.close();
        return null;
    }

    public void refreshRealm() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
        realm.close();
    }

    public void saveToken(AccessToken token) {
        UserAccessToken userAccessToken = new UserAccessToken();
        userAccessToken.setAccessToken(token.getToken());
        userAccessToken.setAppId(token.getApplicationId());
        userAccessToken.setTokenSource(token.getSource().toString());
        userAccessToken.setUserId(token.getUserId());
        userAccessToken.setPermissions(token.getPermissions());
        userAccessToken.setDeclinedPermissions(token.getDeclinedPermissions());
        userAccessToken.setExpirationDate(token.getExpires().getTime());
        userAccessToken.setLastRefreshTime(token.getLastRefresh().getTime());
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(userAccessToken);
        realm.commitTransaction();
        realm.close();
    }

    public void saveProfile(Profile profile) {
        RealmUser user = new RealmUser();
        user.setUserId(profile.getId());
        user.setUserFirstName(profile.getFirstName());
        user.setUserLastName(profile.getLastName());
        user.setUserPicUri(profile.getProfilePictureUri(Constants.PROFILE_PIC_URI_SIZE,
                Constants.PROFILE_PIC_URI_SIZE).toString());
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(user);
        realm.commitTransaction();
        realm.close();
    }

}
