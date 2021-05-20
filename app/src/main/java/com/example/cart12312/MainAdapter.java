package com.example.cart12312;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CustomViewHolder> {

    private ArrayList<MainData> arrayList;
    private Context context; //액티비티 마다 콘텍스트가 있는데 어탭터에서 액티비티 액션을 가져올때 콘텍스트를 쓸대 필요함

    public MainAdapter(ArrayList<MainData> arrayList, RecylerView recylerView) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MainAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);//리사이클러뷰 한 컬럼을 만듬
        CustomViewHolder holder = new CustomViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.CustomViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getProfile())
                .into(holder.profile);//arraylist를 MainData에 연결을 해놨는데  파이어베이스에서 데이터를 받아서 MainData에 담아서 Adater에 보낸다
        holder.name.setText(arrayList.get(position).getName());//리스트 컬럼을 여러개 만들기 위해 담당
        holder.price.setText(String.valueOf(arrayList.get(position).getPrice()));


    }

    @Override
    public int getItemCount() {
        //삼항 연산자 arryList가 참이면 실행 아니면 실행을 안한다다
        return (null != arrayList ? arrayList.size() : 0);

    }



   public class CustomViewHolder extends RecyclerView.ViewHolder {

        ImageView profile;
        TextView name;
        TextView price;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.profile = (ImageView) itemView.findViewById(R.id.profile);
            this.name = (TextView) itemView.findViewById(R.id.name);
            this.price = (TextView) itemView.findViewById(R.id.price);

        }
    }
}
