package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText emailid, password;
    Button btnsignin;
    TextView tvsignup;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailid = findViewById(R.id.txtemail2);
        password = findViewById(R.id.txtpassword2);
         btnsignin = (Button) findViewById(R.id.btnsignup);
         tvsignup = findViewById(R.id.txtview1);

         mAuthStateListener = new FirebaseAuth.AuthStateListener() {


             @Override
             public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                 FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                 if( mFirebaseUser != null){

                     Toast.makeText(LoginActivity.this," You are logged in",Toast.LENGTH_SHORT).show();
                     Intent i = new Intent(LoginActivity.this,HomeScreen.class);
                     startActivity(i);
                 }
                 else
                 {
                     Toast.makeText(LoginActivity.this," Please Login",Toast.LENGTH_SHORT).show();
                 }
             }
         };
         btnsignin.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String email = emailid.getText().toString();
                 String pwd = password.getText().toString();
                 if (email.isEmpty()){
                     emailid.setError( " Please enter email id " );
                     emailid.requestFocus();

                 }
                 else
                 if (pwd.isEmpty()){
                     password.setError("please enter password");
                     password.requestFocus();
                 }
                 else
                 if (email.isEmpty() && pwd.isEmpty()){
                     emailid.setError("please fill details");
                     emailid.requestFocus();
                     password.setError("please fill details");
                     password.requestFocus();
                 }
                 else
                 if (!! email.isEmpty() && pwd.isEmpty()){

                     mFirebaseAuth.signInWithEmailAndPassword( email,pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) {
                             if (!!task.isSuccessful()){
                                 Toast.makeText(LoginActivity.this," Error occurred, Please login again",Toast.LENGTH_SHORT).show();

                             }
                             else{
                                 Intent intToHome = new Intent(LoginActivity.this,HomeScreen.class);
                                 startActivity(intToHome);
                             }
                         }
                     });

                 }
                             else{
                                 startActivity(new Intent(LoginActivity.this,HomeScreen.class));


                             }

                         }
                     });

        tvsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intsignup = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intsignup);
            }
        });

    }


    protected void onStart(){
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}
