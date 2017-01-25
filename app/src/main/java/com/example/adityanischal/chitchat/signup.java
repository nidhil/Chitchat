package com.example.adityanischal.chitchat;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class signup extends AppCompatActivity {
    EditText et1;
    EditText et2;
    EditText et3;
    EditText et4;
    EditText et5;
    Button b1;

    FirebaseAuth fauth;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        et1=(EditText)findViewById(R.id.editText3);
        et2=(EditText)findViewById(R.id.editText6);
        et3=(EditText)findViewById(R.id.editText9);
        et4=(EditText)findViewById(R.id.editText10);
        et5=(EditText)findViewById(R.id.editText11);
        b1=(Button) findViewById(R.id.button4);

        fauth=FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("users");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startRegister();


            }
        });

    }

    private void startRegister() {

         final String name=et1.getText().toString().trim();
       final String username =et2.getText().toString().trim();
       final String email=et3.getText().toString().trim();
       final String password= et4.getText().toString().trim();
       final String phone=et5.getText().toString().trim();

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(username) && !TextUtils.isEmpty(email) &&
               !TextUtils.isEmpty(password) &&
              !TextUtils.isEmpty(phone)){
        Toast.makeText(signup.this,"you have been signed in......",Toast.LENGTH_LONG).show();


             fauth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                //@Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        String userid = fauth.getCurrentUser().getUid();

                        DatabaseReference current_user_db = databaseReference.child(userid);
                        current_user_db.child("name").setValue(name);
                        current_user_db.child("email").setValue(email);
                        current_user_db.child("username").setValue(username);
                        current_user_db.child("password").setValue(password);
                        current_user_db.child("phone").setValue(phone);

                        Intent intent = new Intent(signup.this, register.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);


                    }

                }
            });

        }
}
}

