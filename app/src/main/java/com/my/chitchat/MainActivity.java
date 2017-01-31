package com.my.chitchat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//THIS IS FUCKING MAIN ACTIVITY//

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



Intent intent = new Intent(MainActivity.this,register.class);
        startActivity(intent);
}
}