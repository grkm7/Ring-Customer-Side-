package com.info.bitirmeprojesi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class ScheduleActivity extends AppCompatActivity {
    private RecyclerView rv;
    private ArrayList<DailyPlan> schedulesArrayList;
    private ScheduleAdapter adapter;
    private Button buttonDate;
    private TextView tvDataDurum;

    private Toolbar toolbarSchedule;

    private FirebaseDatabase databaseForSchedule;
    private DatabaseReference myRef;

    private int yil;
    private int ay;
    private int gun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);


        Calendar calendar=Calendar.getInstance();
        yil= calendar.get(Calendar.YEAR);
        ay= calendar.get(Calendar.MONTH);
        gun= calendar.get(Calendar.DAY_OF_MONTH);
        String bugun=String.valueOf(gun)+"/"+String.valueOf(ay+1)+"/"+String.valueOf(yil);
        Log.e("test",bugun);

        databaseForSchedule = FirebaseDatabase.getInstance();
        myRef = databaseForSchedule.getReference("DailyPlan");
        buttonDate=findViewById(R.id.buttonDate);
        tvDataDurum=findViewById(R.id.tvDataDurum);
        tvDataDurum.setText(R.string.veriGirilmemiş);
        tvDataDurum.setVisibility(View.INVISIBLE);


        buttonDate.setText(bugun);



        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog;
                datePickerDialog=new DatePickerDialog(ScheduleActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        //newDate=dayOfMonth+"/"+(month+1)+"/"+year;
                        buttonDate.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                        tumKelimeler();

                    }
                },yil,ay,gun);

                datePickerDialog.setTitle(R.string.Tarihsec);
                datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, getResources().getString(R.string.onayla), datePickerDialog);
                datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE,getResources().getString(R.string.iptal),datePickerDialog);
                datePickerDialog.show();


            }
        });


        rv=findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        toolbarSchedule=findViewById(R.id.toolbarSchedule);
        toolbarSchedule.setTitle(R.string.Aktiviteler);
        setSupportActionBar(toolbarSchedule);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        schedulesArrayList = new ArrayList<>();

        adapter = new ScheduleAdapter(this, schedulesArrayList);
        rv.setAdapter(adapter);
        tumKelimeler();

    }
    public void tumKelimeler() {
        String pickedDate=buttonDate.getText().toString();
        Query SifreSorgu = myRef.orderByChild("date").equalTo(pickedDate);
        SifreSorgu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                schedulesArrayList.clear();

                for (DataSnapshot d : snapshot.getChildren()) {
                    DailyPlan dailyPlan = d.getValue(DailyPlan.class);
                    dailyPlan.setDailyPlan_key(d.getKey());

                    String plan=dailyPlan.getPlan();

                    String[] arrOfStr = plan.split("!", 10);
                    for (String a : arrOfStr){
                        Log.e("test2",a);
                        DailyPlan test = new DailyPlan("",45,"",a);
                        schedulesArrayList.add(test);
                    }

                }
                if (schedulesArrayList.size()==0){
                    tvDataDurum.setVisibility(View.VISIBLE);
                }else{
                    tvDataDurum.setVisibility(View.INVISIBLE);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}