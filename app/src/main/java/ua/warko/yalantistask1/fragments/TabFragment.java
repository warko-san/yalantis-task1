package ua.warko.yalantistask1.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ua.warko.yalantistask1.R;

/**
 * Created by Warko on 11.04.2016.
 */
public class TabFragment extends Fragment { //[Comment] This fragment is unnecessary, you can make this logic in activity
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_layout, container, false);
        mTabLayout = (TabLayout) view.findViewById(R.id.tabs);
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);

        mViewPager.setAdapter(new Adapter(getChildFragmentManager()));
        mTabLayout.post(new Runnable() {
            @Override
            public void run() {
                mTabLayout.setupWithViewPager(mViewPager);
            }
        });
        return view;
    }

    public class Adapter extends FragmentPagerAdapter {
        public Adapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return RecyclerFragment.newInstance("processing");
                case 1:
                    return RecyclerFragment.newInstance("done");
                case 2:
                    return new ListFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
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
