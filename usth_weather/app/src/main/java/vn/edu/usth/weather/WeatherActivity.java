package vn.edu.usth.weather;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import vn.edu.usth.weather.viewpager.CustomPagerAdapter;

public class WeatherActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.weather_activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // set view pager
        ViewPager2 viewPager = findViewById(R.id.viewpager);
        FragmentStateAdapter pagerAdapter = new CustomPagerAdapter(WeatherActivity.this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);
        // set linear layout
//        LinearLayout linearLayout = new LinearLayout(getBaseContext());
//        View forecast_view = findViewById(R.id.forecast_fragment_container);
//        linearLayout.addView(forecast_view);

//        ForecastFragment forecastFragment = new ForecastFragment();
//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.forecast_fragment_container, forecastFragment)
//                .commit();
//        setContentView(R.layout.background);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("start", "On start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("resume", "On resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("pause", "On pause");
    }



    @Override
    protected void onStop() {
        super.onStop();
        Log.i("stop", "On stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("destroy", "On destroy");
    }
}
