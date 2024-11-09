package vn.edu.usth.weather;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class ForecastFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // onCreateView called after the onCreate
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        View v = new View(getContext());
//        v.setBackgroundColor(0x2000FF00);
        // set linear layout
//        LinearLayout linearLayout = new LinearLayout(getContext());
//        linearLayout.setOrientation(LinearLayout.VERTICAL);
//        //
////        linearLayout.setBackgroundColor(0x2000FF00);
//        //-
//        ImageView cloudy = new ImageView(getContext());
//        cloudy.setImageResource(R.drawable.cloudy);
//
//        //
//        TextView text = new TextView(getContext());
////        text.append("This is a text");
//        text.setText("This is a text");
//        text.setTextSize(30);
//        linearLayout.addView(text);
//        linearLayout.addView(cloudy);
//        linearLayout.addView(v);
//        return linearLayout;

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forecast, container, false);
    }

    private void fetchUsthLogo() {
        new AsyncTask<String, Integer, Bitmap>() {
            @Override
            protected Bitmap doInBackground(String... params) {
                Bitmap bitmap = null;
                HttpURLConnection connection = null;
                InputStream is = null;
                try {
                    // Initialize URL
                    URL url = new URL(params[0]);
                    // Make a request to the server
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setDoInput(true);
                    // Allow reading response code and response data
                    connection.connect();
                    // Receive response
                    int response = connection.getResponseCode();
                    Log.i("USTHWeather", "The response is: " + response);

                    if (response == HttpURLConnection.HTTP_OK) {
                        // Process image response
                        is = connection.getInputStream();
                        bitmap = BitmapFactory.decodeStream(is);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (is != null) {
                        try {
                            is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
                return bitmap;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                if (bitmap != null) {
                    // Update the UI
                    ImageView logo = requireView().findViewById(R.id.forecast);
                    logo.setImageBitmap(bitmap);
                } else {
                    Log.e("USTHWeather", "Failed to fetch the logo");
                }
            }
        }.execute("http://ict.usth.edu.vn/wp-content/uploads/usth/usthlogo.png");
    }


}