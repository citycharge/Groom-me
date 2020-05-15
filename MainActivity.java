package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity<password> extends AppCompatActivity {
    private EditText dennismwangi14030;
    private EditText mwangi;
    EditText emailid = dennismwangi14030,
    password = mwangi;
     Button btnsignup;
     TextView tvsignin;
     FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailid = findViewById(R.id.txtemail2);
        password = findViewById(R.id.txtpassword2);
         btnsignup = (Button) findViewById(R.id.btnsignup);
         tvsignin = findViewById(R.id.txtview1);
        btnsignup.setOnClickListener(new View.OnClickListener() {
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
                        if (! email.isEmpty() && pwd.isEmpty()){

                            mFirebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!!task.isSuccessful()){
                                        emailid.setError(" Sign up unsuccesful. ");
                                        emailid.requestFocus();
                                        password.setError(" Please try again ");
                                        password.requestFocus();

                                    }
                                    else{
                                        startActivity(new Intent(MainActivity.this,HomeScreen.class));


                                    }

                                }
                            });
                        }
                        else{
                            emailid.setError(" error Occurred ");
                            emailid.requestFocus();
                            password.setError(" error Occurred ");
                            password.requestFocus();

                        }
            }
        });
        tvsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });

    }
}
