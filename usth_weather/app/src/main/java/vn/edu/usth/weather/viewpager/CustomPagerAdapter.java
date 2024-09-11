package vn.edu.usth.weather.viewpager;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import vn.edu.usth.weather.WeatherAndForecastFragment;

public class CustomPagerAdapter extends FragmentStateAdapter {
    private static final int NUM_PAGES = 2;

    public CustomPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0 :
                return new WeatherAndForecastFragment();
            case 1 :
                return new WeatherAndForecastFragment();
            default:
                return new WeatherAndForecastFragment();
        }
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }
}
