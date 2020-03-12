package com.mwmh.iuep;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BuildingsListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private ArrayList<String> buildingName;
    private ArrayList<String> buildingID;
    private ArrayList<String> buildingImg;

    public BuildingsListAdapter(Activity context, ArrayList<String> buildingName, ArrayList<String> buildingID, ArrayList<String> buildingImg) {
        super(context, R.layout.activity_buildings_item, buildingName);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.buildingName=buildingName;
        this.buildingID=buildingID;
        this.buildingImg=buildingImg;

    }

    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.activity_buildings_item, null,true);
        Context context = rowView.getContext();

        ImageView imageView = (ImageView) rowView.findViewById(R.id.buildingsIcon);
        imageView.setImageResource(getImageId(context, buildingImg.get(position)));

        TextView textView = (TextView) rowView.findViewById(R.id.buildingsName);
        textView.setText(buildingName.get(position));

        return rowView;

    }

}
