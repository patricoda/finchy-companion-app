package com.patricoda.finchycompanionapp;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.patricoda.finchycompanionapp.view.fragment.FinchyTabFragment;
import com.patricoda.finchycompanionapp.view.fragment.TabFragment;

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
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<TabFragment> fragments = new ArrayList<>();
        fragments.add(new FinchyTabFragment("Chapstick Finchy", R.layout.chapstick_finchy_fragment, "ds3"));
        fragments.add(new FinchyTabFragment("Posh Finchy",R.layout.posh_finchy_fragment, "bb"));
        fragments.add(new FinchyTabFragment("Femchy", R.layout.female_finchy_fragment, ""));

        viewPager = findViewById(R.id.pager);
        fragmentStateAdapter = new ScreenSlidePagerAdapter(this, fragments);
        viewPager.setAdapter(fragmentStateAdapter);

        tabLayout = findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(fragments.get(position).getTabTitle())
        ).attach();
    }

    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        private ArrayList<? extends Fragment> fragments;

        public ScreenSlidePagerAdapter(FragmentActivity fragmentActivity, ArrayList<? extends Fragment> fragments) {
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
