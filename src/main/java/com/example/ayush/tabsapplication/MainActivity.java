package com.example.ayush.tabsapplication;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;


public class MainActivity extends AppCompatActivity implements ActionBar.TabListener {


    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = (ViewPager) findViewById(R.id.viewPager);

        ActionBar bar = getSupportActionBar();

        //sets the kind of navigation for the action bar to tabs
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        //creates the two tabs
        bar.addTab(bar.newTab().setText("Audio").setTabListener(this));
        bar.addTab(bar.newTab().setText("Video").setTabListener(this));


        //adapter is intialized and attached to the Viewpager
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //on swiping, the tab gets changed with respect to the current position
                getSupportActionBar().setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        //if a tab is selected, then the pager sets the tab with respect to the position selected
        pager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    public static class AudioFragment extends Fragment {
        @Nullable
        @Override
        //inflates the audio fragment
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            super.onCreateView(inflater, container, savedInstanceState);
            View v = inflater.inflate(R.layout.audio_fragment, null);
            return v;
        }
    }

    public static class VideoFragment extends Fragment {
        @Nullable
        @Override
        //inflates the video fragment
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            super.onCreateView(inflater, container, savedInstanceState);
            View v = inflater.inflate(R.layout.video_fragment, null);
            return v;
        }
    }



    class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment f = null;
            //position of each tab is given here
            if (position == 0) {
                f = new AudioFragment();
            } else if (position == 1) {
                f = new VideoFragment();
            }
            return f;
        }

        @Override
        public int getCount() {
            //creates 2 tabs
            return 2;
        }
    }
}
