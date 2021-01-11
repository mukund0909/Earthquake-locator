package com.example.apifirstapp;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.List;

public class earthquakeadapter extends ArrayAdapter<earthquakedata> {
    public earthquakeadapter(@NonNull Context context,@NonNull List<earthquakedata> objects) {
        super(context,0,objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
      View list=convertView;
      if(list==null)
          list= LayoutInflater.from(getContext()).inflate(R.layout.earthquakelayout,parent,false);
       earthquakedata current=getItem(position);
        TextView magTextView = (TextView) list.findViewById(R.id.magn);
        String s1=Double.toString(current.getmag());
        magTextView.setText(s1);
        int magnitudefloor=(int)Math.floor(current.getmag());
        int magnitude;
        switch(magnitudefloor)
        {
            case 0:
            case 1:
                magnitude = R.color.magnitude1;
                break;
            case 2:
                magnitude = R.color.magnitude2;
                break;
            case 3:
                magnitude = R.color.magnitude3;
                break;
            case 4:
                magnitude = R.color.magnitude4;
                break;
            case 5:
                magnitude = R.color.magnitude5;
                break;
            case 6:
                magnitude = R.color.magnitude6;
                break;
            case 7:
                magnitude = R.color.magnitude7;
                break;
            case 8:
                magnitude = R.color.magnitude8;
                break;
            case 9:
                magnitude= R.color.magnitude9;
                break;
            default:
                magnitude= R.color.magnitude10plus;
                break;
        }
        int color= ContextCompat.getColor(getContext(),magnitude);
        GradientDrawable magnitudeCircle = (GradientDrawable) magTextView.getBackground();
        magnitudeCircle.setColor(color);
        TextView placeTextView = (TextView) list.findViewById(R.id.city);
        TextView preciseTextView = (TextView) list.findViewById(R.id.cityp);
        String s=current.getplace();
        if (s.contains("of")) {
            String[] parts = s.split("of");
            placeTextView.setText(parts[0]+"of");
            preciseTextView.setText(parts[1]);
        } else {
            placeTextView.setText("Near the");
            preciseTextView.setText(s);
        }
        TextView dateTextView = (TextView) list.findViewById(R.id.dates);
        dateTextView.setText(current.getdate());
        TextView timeTextView = (TextView) list.findViewById(R.id.times);
        timeTextView.setText(current.gettime());
        return list;
    }
}
