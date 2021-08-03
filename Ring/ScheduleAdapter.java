package com.info.bitirmeprojesi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.CardViewTutucu>  {
    private Context mContext;
    private List<DailyPlan> schedulesList;

    public ScheduleAdapter(Context mContext, List<DailyPlan> schedulesList) {
        this.mContext = mContext;
        this.schedulesList = schedulesList;
    }

    @NonNull
    @Override
    public CardViewTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tasarim,parent,false);
        return new CardViewTutucu(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewTutucu holder, int position) {
        DailyPlan program = schedulesList.get(position);
        //holder.textViewSaat.setText(program.getSaat1()+"  -");
        //holder.textViewSaat2.setText(program.getSaat2());
        holder.textViewProgram.setText(program.getPlan());
    }

    @Override
    public int getItemCount() {
        return schedulesList.size();
    }

    public class CardViewTutucu extends RecyclerView.ViewHolder{
        public TextView textViewProgram;

        public CardViewTutucu(@NonNull View itemView) {
            super(itemView);

            textViewProgram=itemView.findViewById(R.id.textViewProgram);

        }
    }
}
