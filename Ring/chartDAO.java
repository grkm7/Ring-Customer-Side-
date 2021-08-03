package com.info.bitirmeprojesi;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class chartDAO {
    public void itemEkle(VeritabaniYardimcisi vt, String item_name,int item_count){
        SQLiteDatabase dbx = vt.getWritableDatabase();
        ContentValues degerler = new ContentValues();

        degerler.put("chart_name",item_name);
        degerler.put("chart_count",item_count);

        dbx.insertOrThrow("chart",null,degerler);
        dbx.close();

    }

    public ArrayList<Chart> tumItemler(VeritabaniYardimcisi vt){
        ArrayList<Chart> itemArrayList = new ArrayList<>();
        SQLiteDatabase dbx = vt.getWritableDatabase();

        Cursor c = dbx.rawQuery("SELECT * FROM chart",null);
        while (c.moveToNext()){
            Chart chart = new Chart(c.getInt(c.getColumnIndex("chart_id"))
                    ,c.getString(c.getColumnIndex("chart_name"))
                    ,c.getInt(c.getColumnIndex("chart_count")));

            itemArrayList.add(chart);
        }
        return itemArrayList;
    }

    public void itemSil(VeritabaniYardimcisi vt, int item_id){
        SQLiteDatabase dbx = vt.getWritableDatabase();
        dbx.delete("chart","chart_id=?",new String[]{String.valueOf(item_id)});
        dbx.close();
    }

    public void TumItemleriSil(VeritabaniYardimcisi vt){
        SQLiteDatabase dbx = vt.getWritableDatabase();
        dbx.execSQL("delete from chart");
        dbx.close();
    }

    public void itemGuncelle(VeritabaniYardimcisi vt, int chart_id,String item_name,int item_count){
        SQLiteDatabase dbx = vt.getWritableDatabase();
        ContentValues degerler = new ContentValues();

        degerler.put("chart_name",item_name);
        degerler.put("chart_count",item_count);

        dbx.update("chart",degerler,"chart_id=?",new String[]{String.valueOf(chart_id)});
        dbx.close();

    }
}
