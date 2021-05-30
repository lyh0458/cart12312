package com.example.cart12312;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RecylerView extends AppCompatActivity {

    //전역변수 설정
    private ArrayList<MainData> arrayList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private FirebaseDatabase database; //파이어 베이스 연결
    private DatabaseReference databaseReference;
    private Context context;

    Button pay1;
    TextView pc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recylerview);

        pay1 = findViewById(R.id.pay1);
        pc = findViewById(R.id.pc);
        recyclerView = findViewById(R.id.recyclerView); // 리사이클러뷰 xml 아이디 연결
        recyclerView.setHasFixedSize(true); //리사이클러뷰 기존성능 강화
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>(); //MainData 객체를 담아 어레이 리스트 (어탭터 쪽으로)

        database = FirebaseDatabase.getInstance(); //파이어베이스 데이터베이스 연동

        databaseReference = database.getReference("MainData"); //데이터 베이스 테이블 연결

        //스와이프를 이용해 삭제하기
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                arrayList.remove(viewHolder.getLayoutPosition());
                adapter.notifyItemRemoved(viewHolder.getLayoutPosition());
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);


       // 데이터베이스 삭제 테스트
        // databaseReference.child(MainData).setValue(null);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //파이어베이스 데이터베이스의 데이터를 받아오는 곳
                arrayList.clear(); // 기존 배열리스트가 존재하지않게 초기화
                int total = 0;

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {  //반복문으로 데이터 List를 추출해냄
                    MainData maindata = snapshot.getValue(MainData.class);  //만들어뒀던 data 객체에 데이터를 담는다.
                    arrayList.add(maindata); //담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                    //DatabaseReference asd = database.getReference('MainData/cart-6cc4c-default-rtdb'.remove()); 오류
//.removeValue(); 오류

//.setValue(null); 오류

               }

                //가격 total 값
                for (int i = 0 ; i < arrayList.size(); i++) {
                    total +=  arrayList.get(i).getPrice();
                    pc.setText(String.valueOf(total) + "원");
                }
                adapter.notifyDataSetChanged(); // 리스트 저장 및 새로고침

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("RecyclerView", String.valueOf(databaseError.toException())); // 에러문 출력
            }
        });

        //데이터삭제 text
      //  databaseReference.removeEventListener(new ValueEventListener() {
          //  @Override
          //  public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


           // }

           // @Override
          //  public void onCancelled(@NonNull DatabaseError error) {

        //    }
   //     });




        pay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //값을 받기 위한 선언
                Intent intent;
                intent = new Intent(RecylerView.this, PayActivity.class);
                //activity -> activity 로 값을 전달할때
                intent.putExtra("total", pc.getText());
                startActivity(intent);
            }
        });
        adapter = new MainAdapter(arrayList, this);
        recyclerView.setAdapter(adapter); //리사이클러뷰에 어댑터 연결
    }
}