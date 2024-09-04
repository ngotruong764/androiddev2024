package vn.edu.usth.weather;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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

}