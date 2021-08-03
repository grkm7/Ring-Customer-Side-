package com.info.bitirmeprojesi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class RoomActivity extends AppCompatActivity {
    private Button buttonRoom;
    private TextView tvRoomNo;
    private EditText etTimeSpace;

    private FirebaseDatabase database;
    private DatabaseReference RoomServiceRef;

    private  String timeSpace;
    private int pickedHour;
    private int pickedMinute;

    private int saat;
    private int dakika;

    private RecyclerView rv;
    private ArrayList<RoomService> RoomServiceArrayList;
    private RoomServiceAdapter adapter;

    private Toolbar toolbarRoomService;

    private VeritabaniYardimcisi vt;

    //private int roomNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        database = FirebaseDatabase.getInstance();
        RoomServiceRef = database.getReference("RoomService");

        buttonRoom=findViewById(R.id.buttonRoom);
        tvRoomNo=findViewById(R.id.tvRoomNo);
        etTimeSpace=findViewById(R.id.etTimeSpace);
        toolbarRoomService=findViewById(R.id.toolbarRoomService);
        //NavigationView navigationView=findViewById(R.id.navigationView);

        toolbarRoomService.setTitle(R.string.OdaServis);
        setSupportActionBar(toolbarRoomService);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rv=findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));

        //RoomService r4 = new RoomService("",132,"14:00",305,1);

        RoomServiceArrayList = new ArrayList<>();

        //RoomServiceArrayList.add(r4);

        adapter = new RoomServiceAdapter(this, RoomServiceArrayList);
        rv.setAdapter(adapter);
        tumOdaIstekler();

        //Menu menu1 = navigationView.getMenu();
        //MenuItem bb = menu1.findItem(R.id.departmentname);
        //int roomNo =Integer.parseInt(bb.getTitle().toString());

        vt=new VeritabaniYardimcisi(RoomActivity.this);
        ArrayList<Chart> itemArrayList = new ArrayList<>();
        SQLiteDatabase dbx = vt.getWritableDatabase();
        Cursor c = dbx.rawQuery("SELECT * FROM room",null);
        while (c.moveToNext()){
            Room room = new Room(c.getInt(c.getColumnIndex("room_id"))
                    ,c.getInt(c.getColumnIndex("room_no")));

            //itemArrayList.add(chart);
            Log.e("oda no",String.valueOf(room.getRoom_no()));
            tvRoomNo.setText(String.valueOf(room.getRoom_no()));

        }

        //tvRoomNo.setText(String.valueOf(roomNo));

        etTimeSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                saat = calendar.get(Calendar.HOUR_OF_DAY);
                dakika = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePicker;

                timePicker = new TimePickerDialog(RoomActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    etTimeSpace.setText(hourOfDay+" : "+minute);
                    pickedHour=hourOfDay;
                    pickedMinute=minute;
                    timeSpace =String.valueOf(hourOfDay)+":"+String.valueOf(minute);
                    }
                },saat,dakika,true);

                timePicker.setTitle(R.string.selectHour);

                timePicker.setButton(DialogInterface.BUTTON_POSITIVE,getResources().getString(R.string.onayla),timePicker);
                timePicker.setButton(DialogInterface.BUTTON_NEGATIVE,getResources().getString(R.string.iptal),timePicker);

                timePicker.show();
            }
        });

        buttonRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(String.valueOf(pickedHour)+String.valueOf(pickedMinute),String.valueOf(saat)+String.valueOf(dakika));
                if (pickedHour>saat ) {
                    Log.e("ROOM number", timeSpace);
                    int roomNo=Integer.parseInt(tvRoomNo.getText().toString());
                    RoomService newRoomService = new RoomService("", 456, timeSpace, roomNo,1,"İşleme Alındı.");
                    RoomServiceRef.push().setValue(newRoomService);

                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.aktifistek), Toast.LENGTH_SHORT).show();

                    //Intent intent = new Intent(RoomActivity.this, TabsActivity.class);
                    //startActivity(intent);
                    Intent intent = new Intent(RoomActivity.this, RoomActivity.class);
                    //intent.putExtra("RoomNumber",roomNo);
                    startActivity(intent);
                    finish();
                }
                if (pickedHour== saat && pickedMinute>dakika){
                    Log.e("ROOM number", timeSpace);
                    int roomNo=Integer.parseInt(tvRoomNo.getText().toString());
                    RoomService newRoomService = new RoomService("", 456, timeSpace, roomNo,1,"İşleme Alındı.");
                    RoomServiceRef.push().setValue(newRoomService);

                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.aktifsiparis), Toast.LENGTH_SHORT).show();

                    //Intent intent = new Intent(RoomActivity.this, TabsActivity.class);
                    //startActivity(intent);
                    Intent intent = new Intent(RoomActivity.this, RoomActivity.class);
                    //intent.putExtra("RoomNumber",roomNo);
                    startActivity(intent);
                    finish();
                }
                if (pickedHour<saat){
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.gecersizsaat),Toast.LENGTH_SHORT).show();
                }
                if (pickedHour==saat && pickedMinute<dakika){
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.gecersizsaat),Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
    public void tumOdaIstekler() {
        Query SifreSorgu = RoomServiceRef.orderByChild("user_id").equalTo(305);
        SifreSorgu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                RoomServiceArrayList.clear();

                for (DataSnapshot d : snapshot.getChildren()) {
                    RoomService room = d.getValue(RoomService.class);
                    room.setRoomService_key(d.getKey());

                    RoomServiceArrayList.add(room);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}