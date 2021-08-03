package com.info.bitirmeprojesi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class RoomServiceAdapter extends RecyclerView.Adapter<RoomServiceAdapter.CardViewTutucu> {

    private Context mContext;
    private List<RoomService> roomServiceIsteklerim;

    public RoomServiceAdapter(Context mContext, List<RoomService> servicesList) {
        this.mContext = mContext;
        this.roomServiceIsteklerim = servicesList;
    }

    @NonNull
    @Override
    public CardViewTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_roomservice,parent,false);
        return new CardViewTutucu(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewTutucu holder, int position) {
        final RoomService service = roomServiceIsteklerim.get(position);
        holder.textViewRoomNo.setText(String.valueOf(mContext.getResources().getString(R.string.odaNO)+" "+service.getUser_id()));
        holder.textViewTimeSpace.setText(service.getTimeSpace());
        if (service.getService_durum().equals("İptal Edildi.")||service.getService_durum().equals("Tamamlandı.")||service.getService_durum().equals("Çalışan tarafından İptal edildi.")){
            holder.imageViewDelete.setVisibility(View.INVISIBLE);
        }else{
            holder.imageViewDelete.setImageResource(R.drawable.cancel);
        }
        holder.tvServiceDurum.setText(mContext.getResources().getString(R.string.servisDurum)+" "+service.getService_durum());
        holder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (service.getService_durum().equals("İptal Edildi.")){
                    Toast.makeText(mContext,mContext.getResources().getString(R.string.istekzateniptal),Toast.LENGTH_SHORT).show();
                }else {
                    Snackbar.make(v,R.string.istekiptal,Snackbar.LENGTH_LONG).setAction(R.string.evet, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference("RoomService");
                            String Key = service.getRoomService_key().toString();
                            //myRef.child(Key).removeValue();

                            //Map<String,Object> bilgiler = new HashMap<>();
                            Map<String,Object> bilgiler= new HashMap<>();
                            //int us_id=service.getUser_id()*10;
                            //bilgiler.put("user_id",us_id);
                            bilgiler.put("valid",0);
                            bilgiler.put("service_durum","İptal Edildi.");
                            myRef.child(Key).updateChildren(bilgiler);
                            Toast.makeText(mContext,service.getRoomService_id()+mContext.getResources().getString(R.string.iptaledildi),Toast.LENGTH_SHORT).show();
                        }
                    }).show();
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return roomServiceIsteklerim.size();
    }

    public class CardViewTutucu extends RecyclerView.ViewHolder{

        public ImageView imageViewDelete;
        public TextView textViewRoomNo;
        public TextView textViewTimeSpace;
        public TextView tvServiceDurum;

        public CardViewTutucu(@NonNull View itemView) {
            super(itemView);
            imageViewDelete=itemView.findViewById(R.id.imageViewDelete);
            textViewRoomNo=itemView.findViewById(R.id.textViewRoomNo);
            textViewTimeSpace=itemView.findViewById(R.id.textViewTimeSpace);
            tvServiceDurum=itemView.findViewById(R.id.tvServiceDurum);
        }
    }
}
