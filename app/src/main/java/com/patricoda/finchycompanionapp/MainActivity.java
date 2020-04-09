package com.patricoda.finchycompanionapp;

import android.os.Bundle;

import com.patricoda.finchycompanionapp.fragments.GentlemanFinchyFragment;
import com.patricoda.finchycompanionapp.fragments.KnightFinchyFragment;
import com.patricoda.finchycompanionapp.fragments.LadyFinchyFragment;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends AppCompatActivity {

    private FragmentStateAdapter fragmentStateAdapter;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(new KnightFinchyFragment());
        fragments.add(new GentlemanFinchyFragment());
        fragments.add(new LadyFinchyFragment());

        viewPager = findViewById(R.id.pager);
        fragmentStateAdapter = new ScreenSlidePagerAdapter(this, fragments);
        viewPager.setAdapter(fragmentStateAdapter);
    }

    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        private ArrayList<Fragment> fragments;

        public ScreenSlidePagerAdapter(FragmentActivity fragmentActivity, ArrayList<Fragment> fragments) {
            super(fragmentActivity);
            this.fragments = fragments;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragments.get(position);
        }

        @Override
        public int getItemCount() {
            return fragments.size();
        }
    }
}
