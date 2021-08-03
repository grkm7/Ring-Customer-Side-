package com.info.bitirmeprojesi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TabAdapter extends RecyclerView.Adapter<TabAdapter.CardViewTutucu> {
    private Context mContext ;
    private List<Tabs> tabsList;
    private int RoomNo;

    public TabAdapter(Context mContext, List<Tabs> tabsList, int roomNo) {
        this.mContext = mContext;
        this.tabsList = tabsList;
        RoomNo = roomNo;
    }

    @NonNull
    @Override
    public CardViewTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);
        return new CardViewTutucu(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewTutucu holder, int position) {
        Tabs tab = tabsList.get(position);
        int id = tabsList.get(position).getTab_id();

        holder.textView.setText(tab.getTextView());
        holder.imageView.setImageResource(mContext.getResources().getIdentifier(tab.getImageView(),"drawable",mContext.getPackageName()));   //resmi değiştir.drawable ekledikten sonra.
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id==4){
                    Intent intent = new Intent(mContext,MapActivity.class);
                    mContext.startActivity(intent);
                }else if (id==3){
                    Intent intent = new Intent(mContext, ScheduleActivity.class);
                    mContext.startActivity(intent);
                }else if (id==2){
                    Intent intent = new Intent(mContext, RoomActivity.class);
                    intent.putExtra("RoomNumber",RoomNo);
                    mContext.startActivity(intent);
                }else{
                    Intent intent = new Intent(mContext, OrderActivity.class);
                    intent.putExtra("RoomNumber",RoomNo);
                    mContext.startActivity(intent);
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return tabsList.size();
    }

    public class CardViewTutucu extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public TextView textView ;
        public CardView cv;

        public CardViewTutucu(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.imageView);
            textView=itemView.findViewById(R.id.tvRoomNo);
            cv=itemView.findViewById(R.id.cv);


        }

    }
}

