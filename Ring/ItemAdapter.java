package com.info.bitirmeprojesi;
import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.CardViewTutucu> {

    private Context mContext ;
    private List<Item> itemList;
    private VeritabaniYardimcisi vt;
    private String item_name="";

    public ItemAdapter(Context mContext, List<Item> itemList) {
        this.mContext = mContext;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public CardViewTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,parent,false);
        return new CardViewTutucu(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewTutucu holder, int position) {

        Item item = itemList.get(position);
        holder.textViewItemName.setText(item.getItem_name());
        holder.imageViewItem.setImageResource(mContext.getResources().getIdentifier(item.getItem_photo(),"drawable",mContext.getPackageName()));
        holder.imageViewAdd.setImageResource(R.drawable.add);

        holder.imageViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item_name=item.getItem_name();
                vt=new VeritabaniYardimcisi(mContext);
                PopupMenu popup = new PopupMenu(mContext,v);
                popup.getMenuInflater().inflate(R.menu.item_count_design,popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String order ="";
                        ArrayList<Chart> chartArrayList;
                        chartArrayList = new chartDAO().tumItemler(vt);
                        for (Chart i: chartArrayList){

                            order += String.valueOf(i.getChart_name())+",";
                        }
                        int count = 0;
                        switch (item.getItemId()){
                            case R.id.count_1:
                                count =1;
                                if(order.contains(item_name)){
                                    Toast.makeText(mContext,mContext.getResources().getString(R.string.zatenEklendi),Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(mContext,item_name+" "+mContext.getResources().getString(R.string.eklendi),Toast.LENGTH_SHORT).show();
                                    new chartDAO().itemEkle(vt,item_name,count);
                                    return true;
                                }
                            case R.id.count_2:
                                count=2;
                                if(order.contains(item_name)){
                                    Toast.makeText(mContext,mContext.getResources().getString(R.string.zatenEklendi),Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(mContext,item_name+" "+mContext.getResources().getString(R.string.eklendi),Toast.LENGTH_SHORT).show();
                                    new chartDAO().itemEkle(vt,item_name,count);
                                    return true;
                                }
                            case R.id.count_3:
                                count=3;
                                if(order.contains(item_name)){
                                    Toast.makeText(mContext,mContext.getResources().getString(R.string.zatenEklendi),Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(mContext,item_name+" "+mContext.getResources().getString(R.string.eklendi),Toast.LENGTH_SHORT).show();
                                    new chartDAO().itemEkle(vt,item_name,count);
                                    return true;
                                }
                            case R.id.count_4:
                                count=4;
                                if(order.contains(item_name)){
                                    Toast.makeText(mContext,mContext.getResources().getString(R.string.zatenEklendi),Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(mContext,item_name+" "+mContext.getResources().getString(R.string.eklendi),Toast.LENGTH_SHORT).show();
                                    new chartDAO().itemEkle(vt,item_name,count);
                                    return true;
                                }
                            case R.id.count_5:
                                count=5;
                                if(order.contains(item_name)){
                                    Toast.makeText(mContext,mContext.getResources().getString(R.string.zatenEklendi),Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(mContext,item_name+" "+mContext.getResources().getString(R.string.eklendi),Toast.LENGTH_SHORT).show();
                                    new chartDAO().itemEkle(vt,item_name,count);
                                    return true;
                                }
                            default:
                                return false;
                        }

                    }
                });
                popup.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class CardViewTutucu extends RecyclerView.ViewHolder{

        public ImageView imageViewItem;
        public TextView textViewItemName;
        public ImageView imageViewAdd;

        public CardViewTutucu(@NonNull View itemView) {
            super(itemView);
            imageViewItem=itemView.findViewById(R.id.imageViewItem);
            textViewItemName=itemView.findViewById(R.id.textViewItemName);
            imageViewAdd=itemView.findViewById(R.id.imageViewAdd);

        }
    }

    /*PopupMenu popup = new PopupMenu(MainActivity.this,buttonzorluk);
                popup.getMenuInflater().inflate(R.menu.menuzorluk,popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()){
                case R.id.action_kolay:
                    hak=7;
                    return true;
                case R.id.action_orta:
                    hak=5;
                    return true;
                case R.id.action_zor:
                    hak=3;
                    return true;
                default:

                    return false;
            }
        }
    });
                popup.show();*/
}

