package vn.edu.usth.weather;

import static android.app.PendingIntent.getActivity;

import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;

import vn.edu.usth.weather.viewpager.CustomPagerAdapter;

public class WeatherActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;


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
        FragmentManager fm = getSupportFragmentManager();
        CustomPagerAdapter pagerAdapter = new CustomPagerAdapter(fm, getLifecycle());
        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(pagerAdapter);
//        viewPager.setCurrentItem(0);

        // tab layout
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Hanoi, Viet Nam"));
        tabLayout.addTab(tabLayout.newTab().setText("Paris, France"));

        // connecting tab layout to adapter
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
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

    // inflating the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    // responding to action
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
//                refresh();
                refreshAsyncTask();
                return true;

            case R.id.share:
                Toast.makeText(this, "Shared", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
//        if(item.getItemId() == R.id.action_refresh){
//            Toast.makeText(this, "Refresh", Toast.LENGTH_SHORT).show();
//            return true;
//        } else if(item.getItemId() == R.id.share) {
//            Toast.makeText(this, "Shared", Toast.LENGTH_SHORT).show();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
    }

    private void refresh(){
        final Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                String content = msg.getData().getString("response");
                Toast.makeText(getBaseContext(), content, Toast.LENGTH_SHORT).show();
            }
        };
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Bundle bundle = new Bundle();
                bundle.putString("response", "Refreshed");
                Message msg = new Message();
                msg.setData(bundle);
                handler.sendMessage(msg);
            }
        });
        t.start();
    }

    private void refreshAsyncTask(){
        AsyncTask<String, Integer, Bitmap> task = new AsyncTask<String, Integer, Bitmap>() {
            @Override
            protected Bitmap doInBackground(String... params) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return null;
            }
            @Override
            protected void onPostExecute(Bitmap bitmap) {
                Toast.makeText(getBaseContext(), "Refreshed", Toast.LENGTH_SHORT).show();
            }
        };
        task.execute("http://ict.usth.edu.vn/wp-content/uploads/usth/usthlogo.png");
    }

    @Override
    protected void onStart() {
        super.onStart();
        MediaPlayer mPlayer = MediaPlayer.create(WeatherActivity.this, R.raw.neuvianhnhu);
        mPlayer.start();
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
