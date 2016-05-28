package ua.warko.yalantistask1.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.warko.yalantistask1.R;
import ua.warko.yalantistask1.presenter.DataManager;
import ua.warko.yalantistask1.util.Constants;

/**
 * Created by Warko on 23.05.2016.
 */
public class ProfileActivity extends AppCompatActivity {
    @BindView(R.id.profilePicture)
    ImageView mProfileIcon;
    @BindView(R.id.userName)
    TextView mUserFirstName;
    @BindView(R.id.userLastName)
    TextView mUserLastName;
    private DataManager mDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);
        setContentView(R.layout.profile_activity);
        ButterKnife.bind(this);
        mDataManager = new DataManager();
        fetchProfile();
        initiateToolbar();

    }

    private void fetchProfile() {
        Profile currentProfile = Profile.getCurrentProfile();
        mDataManager.saveProfile(currentProfile);
        if (currentProfile != null) {
            mUserFirstName.setText(currentProfile.getFirstName());
            mUserLastName.setText(currentProfile.getLastName());
            Picasso.with(getApplicationContext())
                    .load(currentProfile.getProfilePictureUri(Constants.PROFILE_PIC_URI_SIZE,
                            Constants.PROFILE_PIC_URI_SIZE))
                    .fit()
                    .into(mProfileIcon);
        } else {
            Profile.fetchProfileForCurrentAccessToken();
        }
    }

    private void initiateToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (toolbar != null) {
            toolbar.setNavigationIcon(R.drawable.arrow_left);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(R.string.profile_toolbar_title);
            }
        }
    }
}
