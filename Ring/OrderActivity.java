package com.info.bitirmeprojesi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OrderActivity extends AppCompatActivity {
    private Button buttonEat;
    private  Button buttonDrink;
    private  Button buttonChart;

    private VeritabaniYardimcisi vt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        buttonDrink=findViewById(R.id.buttonDrink);
        buttonEat=findViewById(R.id.buttonEat);
        buttonChart=findViewById(R.id.buttonChart);

        vt= new VeritabaniYardimcisi(this);

        int roomNo = getIntent().getIntExtra("RoomNumber",0);


        buttonDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderActivity.this,DrinkableActivity.class);
                startActivity(intent);
            }
        });

        buttonEat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderActivity.this,EatableActivity.class);
                startActivity(intent);
            }
        });
        buttonChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderActivity.this,ChartActivity.class);
                intent.putExtra("RoomNumber",roomNo);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(OrderActivity.this,TabsActivity.class);
        startActivity(intent);
    }
}