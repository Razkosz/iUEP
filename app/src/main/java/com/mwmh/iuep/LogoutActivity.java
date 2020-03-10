package com.mwmh.iuep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;

public class LogoutActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        mAuth =FirebaseAuth.getInstance();
        startLogout();
    }

    private void startLogout(){
        Log.i("Login", "User logged out: " + mAuth.getCurrentUser().getUid().toString());
        mAuth.signOut();
        startActivity(new Intent(LogoutActivity.this, LoginActivity.class));
    }
}