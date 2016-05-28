package ua.warko.yalantistask1.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.warko.yalantistask1.R;
import ua.warko.yalantistask1.presenter.DataManager;
import ua.warko.yalantistask1.util.Constants;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.navigationView)
    NavigationView mNavigationView;
    private CallbackManager mCallbackManager;
    private DataManager mDataManager;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mDataManager = new DataManager();
        mCallbackManager = CallbackManager.Factory.create();
        setAppToolbar();
        initTabs();
        startNavigationView();
        setActionBarToggle();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public void startNavigationView() {
        if (mNavigationView != null) {
            mNavigationView.getMenu().clear();
            mNavigationView.inflateMenu(R.menu.drawer_menu_logged_out);
            if (AccessToken.getCurrentAccessToken() != null) {
                mNavigationView.getMenu().clear();
                mNavigationView.inflateMenu(R.menu.drawer_menu_logged_in);
            }
            mNavigationView.setNavigationItemSelectedListener(new NavigationView
                    .OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    mDrawerLayout.closeDrawers();
                    switch (menuItem.getItemId()) {
                        case R.id.nav_item_proposal:
                            startActivity(new Intent(MainActivity.this, MainActivity.class));
                            break;
                        case R.id.login_account:
                            initLogIn();
                            mNavigationView.getMenu().clear();
                            mNavigationView.inflateMenu(R.menu.drawer_menu_logged_in);
                            break;
                        case R.id.nav_profile:
                            startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                            break;
                        case R.id.logout:
                            LoginManager.getInstance().logOut();
                            mNavigationView.getMenu().clear();
                            mNavigationView.inflateMenu(R.menu.drawer_menu_logged_out);
                            break;

                    }
                    return false;
                }
            });
        }
    }


    public void initLogIn() {
        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                mDataManager.saveToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
        LoginManager.getInstance().logInWithReadPermissions(MainActivity.this,
                Arrays.asList(Constants.FACEBOOK_PERMISSION));

    }

    public void setActionBarToggle() {
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                mToolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
        actionBarDrawerToggle.setHomeAsUpIndicator(R.mipmap.ic_menu);
        actionBarDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
    }

    public void setAppToolbar() {
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setTitle(R.string.toolbar_title);
        }
    }

    public void initTabs() {
        mViewPager.setAdapter(new Adapter(getSupportFragmentManager()));
        mTabLayout.post(new Runnable() {
            @Override
            public void run() {
                mTabLayout.setupWithViewPager(mViewPager);

            }
        });

    }


    public class Adapter extends FragmentPagerAdapter {
        private final int PROCESSING = 0;
        private final int DONE = 1;
        private final int PENDING = 2;

        public Adapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return RecyclerFragment.newInstance(PROCESSING);
                case 1:
                    return RecyclerFragment.newInstance(DONE);
                case 2:
                    return RecyclerFragment.newInstance(PENDING);
            }
            return null;
        }

        @Override
        public int getCount() {
            int[] tabs = {PROCESSING, DONE, PENDING};
            return tabs.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.firstTab);
                case 1:
                    return getString(R.string.secondTab);
                case 2:
                    return getString(R.string.thirdTab);

            }
            return null;
        }
    }
}
