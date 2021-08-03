package com.info.bitirmeprojesi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ChartActivity extends AppCompatActivity {
    private VeritabaniYardimcisi vt;
    private RecyclerView rv;
    private ArrayList<Chart> chartArrayList;
    private ChartAdapter adapter;
    private Button buttonSendOrder;
    private Button buttonSiparisler;

    private FirebaseDatabase database;
    private DatabaseReference OrderServiceRef;

    private String order="";

    private Toolbar toolbarCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        vt= new VeritabaniYardimcisi(this);
        buttonSendOrder=findViewById(R.id.buttonSendOrder);
        buttonSiparisler=findViewById(R.id.buttonSiparisler);
        toolbarCart=findViewById(R.id.toolbarCart);
        rv=findViewById(R.id.rv);
        rv.setHasFixedSize(true);

        toolbarCart.setTitle(R.string.sepet);
        toolbarCart.setLogo(R.drawable.shopping_cart);
        setSupportActionBar(toolbarCart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));

        int roomNo = getIntent().getIntExtra("RoomNumber",0);

        database = FirebaseDatabase.getInstance();
        OrderServiceRef = database.getReference("Order");

        chartArrayList = new ArrayList<>();

        chartArrayList = new chartDAO().tumItemler(vt);

        adapter = new ChartAdapter(this,chartArrayList);
        rv.setAdapter(adapter);

        //new chartDAO().itemEkle(vt,"sprite",5);
        //new chartDAO().itemSil(vt,3);


        /*for (Chart i: chartArrayList){

            order += String.valueOf(i.getChart_count()+" "+i.getChart_name())+" , ";
        }

        Log.e(order,"*********");*/

        buttonSendOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View tasarim = getLayoutInflater().inflate(R.layout.alert_tasarim,null);
                EditText editTextAlert = tasarim.findViewById(R.id.etNote);

                AlertDialog.Builder ad= new AlertDialog.Builder(ChartActivity.this);
                ad.setTitle(R.string.notunuGir);
                ad.setView(tasarim);
                ad.setIcon(R.drawable.not);

                ad.setPositiveButton(R.string.onayla, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        chartArrayList.clear();
                        chartArrayList = new chartDAO().tumItemler(vt);
                        String mesaj = editTextAlert.getText().toString();
                        for (Chart i: chartArrayList){

                            order += String.valueOf(i.getChart_count()+" "+i.getChart_name())+" , ";
                        }
                        if (order.trim().equals("")&&mesaj.trim().equals("")){
                            Toast.makeText(ChartActivity.this,getResources().getString(R.string.Sepetbos),Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ChartActivity.this,OrderActivity.class));
                        }else if(order.trim().equals("") && !mesaj.trim().equals("")){
                            Order newOrder = new Order("",5897,41,"Sepet Boş.",mesaj,roomNo,305,1,"Hazırlanıyor.");
                            OrderServiceRef.push().setValue(newOrder);
                            startActivity(new Intent(ChartActivity.this,ChartActivity.class));
                            new chartDAO().TumItemleriSil(vt);
                            Toast.makeText(ChartActivity.this,getResources().getString(R.string.aktifsiparis),Toast.LENGTH_SHORT).show();
                        }else{
                            //Log.e(order,"*********");
                            Order newOrder = new Order("",5897,41,order,mesaj,roomNo,305,1,"Hazırlanıyor.");
                            OrderServiceRef.push().setValue(newOrder);
                            startActivity(new Intent(ChartActivity.this,ChartActivity.class));
                            new chartDAO().TumItemleriSil(vt);
                            Toast.makeText(ChartActivity.this,getResources().getString(R.string.aktifsiparis),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                ad.setNegativeButton(R.string.iptal, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                ad.create().show();

            }
        });

        buttonSiparisler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChartActivity.this,SiparislerActivity.class));
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ChartActivity.this,TabsActivity.class);
        startActivity(intent);
    }
}