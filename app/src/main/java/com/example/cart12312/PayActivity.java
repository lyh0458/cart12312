package com.example.cart12312;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.TextView;

public class PayActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Intent intent;
    private String totalValue;
    Button yes,no;
    TextView payText;
    TextView pc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        recyclerView = findViewById(R.id.recyclerView);
        yes = (Button)findViewById(R.id.yes);
        no = (Button)findViewById(R.id.no);
        pc = findViewById(R.id.pc);
        //전달한 값 받기
        intent = getIntent();
        totalValue = intent.getStringExtra("total");
        payText = (TextView)findViewById(R.id.payText);
        payText.setText(totalValue);
    }

    //동작 버튼 클릭
    public void mOk(View v) {
        finish();
    }

    //취소 버튼 클릭
    public void mCancle(View v) {
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        //바깥레이어 클릭시 안닫히게
        if(event.getAction() == MotionEvent.ACTION_OUTSIDE);{
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        return;
    }
}