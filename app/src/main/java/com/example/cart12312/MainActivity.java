package com.example.cart12312;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button bt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt1 = findViewById(R.id.bt1);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //인텐트 선언 ->현재 액티비티, 넘어갈 액티비티
              Intent intent = new Intent(MainActivity.this, RecylerViewActivitiy.class);
              //인텐트 실행
                startActivity(intent);

            }
        });




    }

}