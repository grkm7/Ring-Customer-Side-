package com.info.bitirmeprojesi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class TabsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private RecyclerView rv;
    private ArrayList<Tabs> tabsArrayList;
    private TabAdapter adapter ;

    private int roomNo;

    private VeritabaniYardimcisi vt;

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

        roomNo=getIntent().getIntExtra("roomNo",0);

        rv=findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        navigationView=findViewById(R.id.navigationView);
        drawer=findViewById(R.id.drawer);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Menu menu = navigationView.getMenu();
        //MenuItem bb = menu.findItem(R.id.departmentname);
        //bb.setTitle(String.valueOf(roomNo));
        ////
        vt=new VeritabaniYardimcisi(TabsActivity.this);
        SQLiteDatabase dbx = vt.getWritableDatabase();
        Cursor c = dbx.rawQuery("SELECT * FROM room",null);
        while (c.moveToNext()){
            Room room = new Room(c.getInt(c.getColumnIndex("room_id"))
                    ,c.getInt(c.getColumnIndex("room_no")));

            //itemArrayList.add(chart);
            Log.e("oda no",String.valueOf(room.getRoom_no()));
            MenuItem bb = menu.findItem(R.id.departmentname);
            bb.setTitle(String.valueOf(room.getRoom_no()));
        }
        ////
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this,drawer,toolbar,0,0);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        View baslik = navigationView.inflateHeaderView(R.layout.navigation_baslik);

        //oda_no(vt);

        rv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        Tabs t1 = new Tabs(1,getResources().getString(R.string.SiparisServis),"order");
        Tabs t2 = new Tabs(2,getResources().getString(R.string.OdaServis),"cleaning");
        Tabs t3 = new Tabs(3,getResources().getString(R.string.Aktiviteler),"schedule");
        Tabs t4 = new Tabs(4,getResources().getString(R.string.Harita),"map");

        tabsArrayList = new ArrayList<>();

        tabsArrayList.add(t1);
        tabsArrayList.add(t2);
        tabsArrayList.add(t3);
        tabsArrayList.add(t4);


        adapter = new TabAdapter(this,tabsArrayList,roomNo);
        rv.setAdapter(adapter);

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    public void oda_no(VeritabaniYardimcisi vt){
        SQLiteDatabase dbx = vt.getWritableDatabase();

        Cursor c = dbx.rawQuery("SELECT * FROM room",null);
        while (c.moveToNext()){
            int room_no = c.getInt(c.getColumnIndex("room_no"));
            Log.e(String.valueOf(room_no),"*****************");


        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.profile2){
            Toast.makeText(getApplicationContext(), "lalala", Toast.LENGTH_SHORT).show();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}