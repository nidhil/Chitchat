package com.example.adityanischal.chitchat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {
    EditText e1;
    EditText e2;
    TextView t1;
    Button b1;

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText2);
        t1=(TextView) findViewById(R.id.textView2);
        b1=(Button) findViewById(R.id.button);
        
        firebaseAuth=FirebaseAuth.getInstance();
       databaseReference= FirebaseDatabase.getInstance().getReference().child("users");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLogin();
            }
        });
    }

    private void startLogin() {
        String email=e1.getText().toString().trim();
        String password =e2.getText().toString().trim();


        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){

            firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){
                        checkUserExist();

                    }else {
                        Toast.makeText(login.this,"login error",Toast.LENGTH_LONG).show();

                    }

                }
            });

        }
    }

    private void checkUserExist() {

       final String userid= firebaseAuth.getCurrentUser().getUid();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
              if (dataSnapshot.hasChild(userid)) {
                  Intent intent = new Intent(login.this, userlist.class);
                  intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                  startActivity(intent);


              }else{


              }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
