package com.info.bitirmeprojesi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EatableActivity extends AppCompatActivity {
    private RecyclerView rv;
    private ArrayList<Item> itemArrayList;
    private ItemAdapter adapter;

    private FirebaseDatabase database;
    private DatabaseReference itemRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eatable);

        rv=findViewById(R.id.rv);
        rv.setHasFixedSize(true);

        rv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));

        itemArrayList = new ArrayList<>();

        database = FirebaseDatabase.getInstance();
        itemRef = database.getReference("Item");

        adapter = new ItemAdapter(this,itemArrayList);
        rv.setAdapter(adapter);
        allFoods();
    }

    public void allFoods(){
        Query FoodSorgu = itemRef.orderByChild("order_type").equalTo(1);
        FoodSorgu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                itemArrayList.clear();

                for(DataSnapshot d: snapshot.getChildren()){
                    Item item = d.getValue(Item.class);
                    item.setItem_key(d.getKey());

                    itemArrayList.add(item);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}