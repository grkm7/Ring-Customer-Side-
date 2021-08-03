package com.info.bitirmeprojesi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText etID;
    private EditText etPassword;
    private Button button;
    private TextView tvSonuc;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private DatabaseReference UserintRef;

    private String sifre;
    private String password;

    private int sifreUserInt;
    private int passwordUserInt;

    private int databaseIdUserInt;

    public int roomNo;

    private VeritabaniYardimcisi vt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etID=findViewById(R.id.etID);
        etPassword=findViewById(R.id.etPassword);
        button=findViewById(R.id.buttonRoom);
        tvSonuc=findViewById(R.id.tvSonuc);

        tvSonuc.setText(R.string.girisKontrol);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("RoomService");

        UserintRef=database.getReference("UserInt");

        Employee employee1 = new Employee("",465798,798465,"Mehmet");
        Employee employee2 = new Employee("",000,000,"Elizabeth Topuz");
        RoomService roomService1=new RoomService("",545,"14:30",350,1,"İşleme Alındı.");
        Order order1 = new Order("",978645,68,"2 cola , 2 pizza","Be quick please!",250,789456,0,"Hazırlanıyor.");
        ItemCategory ıtemcategory1= new ItemCategory("",1,2);
        User user1= new User("","405","45","Hande Hanım","9");
        UserInt userInt1= new UserInt("",405,45,"Hande Hanım",9);
        //Map<S, O> map1 = new Map<S, O>("","www.image.com",645);
        Hotel hotel1=new Hotel("",78945,"Hotel1","www.image.com");
        DailyPlan dailyPlan = new DailyPlan("",56,"20/04/2021","12:00 - 13:00 Pool Party! 13:00 - 14:00 Dinner!");
        //UserInt userInt1= new UserInt("",7,1,"Ahmet",971);
        //UserintRef.push().setValue(employee2);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int id =Integer.parseInt(etID.getText().toString());

                if(TextUtils.isEmpty(etID.getText().toString())){
                    tvSonuc.setText(R.string.girisKontrol3);
                }else{
                    int idUserInt =Integer.parseInt(etID.getText().toString());

                    Query SifreSorgu = UserintRef.orderByChild("user_id").equalTo(idUserInt);
                    SifreSorgu.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot d: snapshot.getChildren()){
                                UserInt userInt=d.getValue(UserInt.class);
                                String key = d.getKey();

                                userInt.setUser_key(key);
                                sifreUserInt=userInt.getUser_password();
                                databaseIdUserInt=userInt.getUser_id();



                            }
                            if(TextUtils.isEmpty(etPassword.getText().toString())){
                                tvSonuc.setText(R.string.girisKontrol4);
                            }else{
                                passwordUserInt=Integer.parseInt(etPassword.getText().toString());

                                if(sifreUserInt==passwordUserInt&&databaseIdUserInt==idUserInt){
                                    roomNo=databaseIdUserInt;
                                    tvSonuc.setText(R.string.girisKontrol5);
                                    ///////
                                    /*vt=new VeritabaniYardimcisi(MainActivity.this);
                                    SQLiteDatabase dbx = vt.getWritableDatabase();
                                    ContentValues deger = new ContentValues();
                                    deger.put("room_no",idUserInt);
                                    dbx.insertOrThrow("room",null,deger);
                                    dbx.close();*/
                                    /////

                                    Intent intent = new Intent(MainActivity.this,TabsActivity.class);
                                    intent.putExtra("roomNo",idUserInt);
                                    startActivity(intent);
                                    finish();
                                }else{
                                    tvSonuc.setText(R.string.girisKontrol2);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });


        /*button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=etID.getText().toString();
                Query SifreSorgu = myRef.orderByChild("user_id").equalTo(id);
                SifreSorgu.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot d:snapshot.getChildren()){
                            User user=d.getValue(User.class);
                            String key = d.getKey();

                            user.setUser_key(key);
                            sifre=user.getUser_password();

                        }
                        Log.e("mesaj",sifre);

                        password=etPassword.getText().toString();

                        if(sifre.equals(password)){
                            tvSonuc.setText("Başarılı");
                            Intent intent = new Intent(MainActivity.this,TabsActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            tvSonuc.setText("Şifre veya ID yanlış");
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        });*/
    }
}