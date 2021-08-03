package com.info.bitirmeprojesi;



import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.logging.Handler;

public class ChartAdapter extends RecyclerView.Adapter<ChartAdapter.CardViewTutucu> {
    Context mContext;
    private List<Chart> chartList;
    private VeritabaniYardimcisi vt;


    public ChartAdapter(Context mContext, List<Chart> chartList) {
        this.mContext = mContext;
        this.chartList = chartList;
    }

    @NonNull
    @Override
    public CardViewTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_chart,parent,false);
        return new CardViewTutucu(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewTutucu holder, int position) {

        Chart chart = chartList.get(position);

        holder.tvItemName.setText(chart.getChart_name());
        holder.tvItemCount.setText(String.valueOf(chart.getChart_count()));
        holder.ivRemove.setImageResource(R.drawable.remove);
        holder.ivArttir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Count =Integer.parseInt(holder.tvItemCount.getText().toString());
                if (Count==5){
                    Toast.makeText(mContext,mContext.getResources().getString(R.string.bes),Toast.LENGTH_SHORT).show();
                }else{
                    int id=chart.getChart_id();
                    String ad=chart.getChart_name();
                    Count++;
                    holder.tvItemCount.setText(String.valueOf(Count));
                    vt=new VeritabaniYardimcisi(mContext);
                    new chartDAO().itemGuncelle(vt,id,ad,Count);
                }
            }
        });
        holder.ivAzalt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Count =Integer.parseInt(holder.tvItemCount.getText().toString());
                if (Count==1){
                    Toast.makeText(mContext,mContext.getResources().getString(R.string.bir),Toast.LENGTH_SHORT).show();
                }else{
                    int id=chart.getChart_id();
                    String ad=chart.getChart_name();
                    Count--;
                    holder.tvItemCount.setText(String.valueOf(Count));
                    vt=new VeritabaniYardimcisi(mContext);
                    new chartDAO().itemGuncelle(vt,id,ad,Count);
                }
            }
        });
        holder.ivRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vt=new VeritabaniYardimcisi(mContext);
                int id= chart.getChart_id();
                new chartDAO().itemSil(vt,id);
                Toast.makeText(mContext,chart.getChart_name()+" "+mContext.getResources().getString(R.string.silindi),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext,ChartActivity.class);
                mContext.startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return chartList.size();
    }

    public class CardViewTutucu extends RecyclerView.ViewHolder{

        public ImageView ivRemove;
        public TextView tvItemCount;
        public TextView tvItemName;
        public ImageView ivAzalt;
        public ImageView ivArttir;

        public CardViewTutucu(@NonNull View itemView) {
            super(itemView);

            ivRemove=itemView.findViewById(R.id.ivRemove);
            tvItemCount=itemView.findViewById(R.id.tvItemCount);
            tvItemName=itemView.findViewById(R.id.tvItemName);
            ivAzalt=itemView.findViewById(R.id.ivAzalt);
            ivArttir=itemView.findViewById(R.id.ivArttir);
        }
    }

}

