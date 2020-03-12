package com.mwmh.iuep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private String UID;
    private Button mMyAccBtn;
    private Button mScheduleBtn;
    private Button mNewsBtn;
    private Button mCommuteBtn;
    private Button mFloorsBtn;
    private Button mMoodleBtn;
    private Button mBosBtn;
    private Button mMoreBtn;
    private Button mCouncilBtn;
    private Button mContactBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        UID = mAuth.getCurrentUser().getUid().toString();

        mMyAccBtn = (Button) findViewById(R.id.btn_myAcc);
        mMyAccBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

            }
        });
        mScheduleBtn = (Button) findViewById(R.id.btn_schedule);
        mScheduleBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //on click activity
            }
        });
        mNewsBtn = (Button) findViewById(R.id.btn_news);
        mNewsBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //on click activity
            }
        });
        mCommuteBtn = (Button) findViewById(R.id.btn_commute);
        mCommuteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //on click activity
            }
        });
        mFloorsBtn = (Button) findViewById(R.id.btn_floors);
        mFloorsBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //on click activity
                startActivity(new Intent(MainActivity.this, BuildingsActivity.class));
            }
        });
        mMoodleBtn = (Button) findViewById(R.id.btn_moodle);
        mMoodleBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //on click activity
            }
        });
        mBosBtn = (Button) findViewById(R.id.btn_ebos);
        mBosBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //on click activity
            }
        });
        mMoreBtn = (Button) findViewById(R.id.btn_more);
        mMoreBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //on click activity
            }
        });
        mCouncilBtn = (Button) findViewById(R.id.btn_council);
        mCouncilBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //on click activity
            }
        });
        mContactBtn = (Button) findViewById(R.id.btn_contact);
        mContactBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //on click activity
            }
        });

    }
}
