package com.mwmh.iuep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class BuildingsActivity extends AppCompatActivity {

    private BuildingsListAdapter adapter;
    private FirebaseFirestore mFirestore;
    private ArrayList<DocumentSnapshot> buildings;
    private ArrayList<String> buildingName;
    private ArrayList<String> buildingID;
    private ArrayList<String> buildindImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buildings);
        Log.i("Tabs", "Buildings tab");

        mFirestore = FirebaseFirestore.getInstance();
        buildings = new ArrayList<>();
        buildingName = new ArrayList<>();
        buildingID = new ArrayList<>();
        buildindImg = new ArrayList<>();

        downloadBuildings();
}

    public void downloadBuildings(){
        mFirestore.collection("buildings").orderBy("name").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful() && task.getResult().size()>0) {
                    for (DocumentSnapshot document : task.getResult()) {
                        buildings.add(document);
                        buildingName.add(document.getString("name"));
                        buildingID.add(document.getId().toString());
                        buildindImg.add(document.getString("img_url"));
                    }
                    Log.i("Buildings", "Buildings downloaded");
                    createList();
                } else {
                    Log.e("Buildings", "Buildings not loaded from database: " + task.getException());
                }
            }
        });
    }

    public void createList(){
        adapter = new BuildingsListAdapter(this, buildingName, buildingID, buildindImg);
        ListView listView = (ListView) findViewById(R.id.buildingsItem);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Log.i("Buildings", "Building selected: " + buildings.get(position).getId());
                //on click activity
            }
        });
        Log.i("Buildings", "List created");
    }
}
