package com.info.bitirmeprojesi;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
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

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class SiparisAdapter extends RecyclerView.Adapter<SiparisAdapter.CardTasarimTutucu> {

    private Context mContext;
    private List<Order> SiparisList;

    public SiparisAdapter(Context mContext, List<Order> siparisList) {
        this.mContext = mContext;
        SiparisList = siparisList;
    }

    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_siparis,parent,false);
        return new CardTasarimTutucu(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTasarimTutucu holder, int position) {
        Order order = SiparisList.get(position);

        holder.textViewRoomNo1.setText(mContext.getResources().getString(R.string.odaNO)+" "+String.valueOf(order.getUser_id()));
        holder.textViewOrder.setText(order.getOrder());
        holder.textViewOrder.setMovementMethod(new ScrollingMovementMethod().getInstance());
        if (order.getSiparis_durum().equals("İptal Edildi.")||order.getSiparis_durum().equals("Tamamlandı.")||order.getSiparis_durum().equals("Çalışan tarafından İptal edildi.")){
            holder.imageViewDelete1.setVisibility(View.INVISIBLE);
        }else{
            holder.imageViewDelete1.setImageResource(R.drawable.cancel);
        }
        holder.textViewNote.setText(mContext.getResources().getString(R.string.not)+ order.getUser_note());
        holder.tvSiparisDurum.setText(mContext.getResources().getString(R.string.siparisDurum)+" "+order.getSiparis_durum());
        holder.imageViewDelete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,R.string.siparisiptal,Snackbar.LENGTH_LONG).setAction(R.string.evet, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("Order");

                        String Key = order.getOrder_key();

                        Map<String,Object> bilgiler = new HashMap<>();
                        bilgiler.put("valid",0);
                        bilgiler.put("siparis_durum","İptal Edildi.");
                        myRef.child(Key).updateChildren(bilgiler);
                        Toast.makeText(mContext,order.getOrder_id()+" "+mContext.getResources().getString(R.string.iptaledildi),Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return SiparisList.size();
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder{
        public ImageView imageViewDelete1;
        public TextView textViewRoomNo1;
        public TextView textViewOrder;
        public TextView textViewNote;
        public TextView tvSiparisDurum;

        public CardTasarimTutucu(@NonNull View itemView) {
            super(itemView);

            imageViewDelete1 = itemView.findViewById(R.id.imageViewDelete1);
            textViewOrder = itemView.findViewById(R.id.textViewOrder);
            textViewRoomNo1 = itemView.findViewById(R.id.textViewRoomNo1);
            textViewNote = itemView.findViewById(R.id.textViewNote);
            tvSiparisDurum = itemView.findViewById(R.id.tvSiparisDurum);



        }
    }
}
