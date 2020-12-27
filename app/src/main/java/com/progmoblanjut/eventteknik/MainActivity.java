package com.progmoblanjut.eventteknik;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.progmoblanjut.eventteknik.adapter.ListViewAdapter;
import com.progmoblanjut.eventteknik.sql.DataEventKepanitiaan;
import com.progmoblanjut.eventteknik.sql.SQLiteHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton tambah;
    private ListView listView;
    private ListViewAdapter adapter;
    ArrayList<DataEventKepanitiaan> listKepanitiaan = new ArrayList<>();
    private SQLiteHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tambah = (FloatingActionButton) findViewById(R.id.FABTambah);
        listView = (ListView) findViewById(R.id.listViewEvent);
        helper = new SQLiteHelper(this);

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TambahEventActivity.class));
            }
        });
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
        }

        menampilkanData();
    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    private void menampilkanData() {
        listKepanitiaan.clear();
        Cursor res = helper.getDataAll();
        while(res.moveToNext()) {

            String ID = res.getString(0);
            String nama = res.getString(1);
            String tanggal = res.getString(2);
            String tanggal_rapatperdana = res.getString(3);
            String tempat_pelaksanaanacara = res.getString(4);
            String tempat_rapatperdana = res.getString(5);
            String deskripsi_acarakepanitiaan = res.getString(6);

            listKepanitiaan.add(new DataEventKepanitiaan(ID, nama, tanggal, tanggal_rapatperdana, tempat_pelaksanaanacara, tempat_rapatperdana, deskripsi_acarakepanitiaan));
        }
        adapter = new ListViewAdapter(listKepanitiaan, MainActivity.this);
        listView.setAdapter(adapter);
    }

}