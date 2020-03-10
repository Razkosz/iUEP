package com.mwmh.iuep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore mFirestore;
    private EditText mEmailField;
    private EditText mPasswordField;
    private EditText mNameField;
    private Button mSignupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
        mEmailField = (EditText) findViewById(R.id.signupEmail);
        mPasswordField = (EditText) findViewById(R.id.signupPassword);
        mNameField = (EditText) findViewById(R.id.signupName);
        mSignupButton = (Button) findViewById(R.id.signupSignupButton);
        mSignupButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                addNewUser();
            }
        });
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
    }

    public void addNewUser(){
        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();
        String name = mNameField.getText().toString();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(name)) {
            Log.i("Login", "SignUp: Name, Age, Email or Password empty");
            Toast.makeText(SignupActivity.this, "All fields are mandatory", Toast.LENGTH_LONG).show();
        } else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        addNewUserToDb(mAuth.getCurrentUser().getUid().toString());
                    } else {
                        Log.e("Login", "Sign Up Failed", task.getException());
                        Toast.makeText(SignupActivity.this, "Sign Up Failed", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    public void addNewUserToDb(String UID){
        String email = mEmailField.getText().toString();
        String name = mNameField.getText().toString();
        String accType = "STD";

        Map<String, Object> userMap = new HashMap<>();
        userMap.put("UID", UID);
        userMap.put("name", name);
        userMap.put("email", email);
        userMap.put("accType", accType);

        mFirestore.collection("users").add(userMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.i("Login", "User ("+ mAuth.getCurrentUser().getUid().toString() +") added to database: " + documentReference.getId().toString());
                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("Login", "User not added to database: " + e.getMessage());
                Toast.makeText(SignupActivity.this, "Sign Up Failed", Toast.LENGTH_LONG).show();
            }
        });


    }
}
