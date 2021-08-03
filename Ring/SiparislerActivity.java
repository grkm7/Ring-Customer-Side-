package com.info.bitirmeprojesi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SiparislerActivity extends AppCompatActivity {
    private RecyclerView rv;
    private ArrayList<Order> siparislerArrayList;
    private SiparisAdapter adapter;

    private FirebaseDatabase databaseForSiparislerim;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siparisler);

        databaseForSiparislerim = FirebaseDatabase.getInstance();
        myRef = databaseForSiparislerim.getReference("Order");

        rv=findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));

        //Order order1=new Order("",113,321,"5 ekmek","Acele et!",205,5,1);
        siparislerArrayList= new ArrayList<>();
        //siparislerArrayList.add(order1);

        adapter=new SiparisAdapter(this,siparislerArrayList);
        rv.setAdapter(adapter);
        tumKelimeler();
    }
    public void tumKelimeler() {
        Query SifreSorgu = myRef.orderByChild("user_id").equalTo(305);
        SifreSorgu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                siparislerArrayList.clear();

                for (DataSnapshot d : snapshot.getChildren()) {
                    Order order = d.getValue(Order.class);
                    order.setOrder_key(d.getKey());

                    siparislerArrayList.add(order);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}