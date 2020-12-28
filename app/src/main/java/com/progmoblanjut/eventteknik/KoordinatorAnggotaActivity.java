package com.progmoblanjut.eventteknik;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.progmoblanjut.eventteknik.adapter.DivisiAdapter;
import com.progmoblanjut.eventteknik.sql.DataDivisi;
import com.progmoblanjut.eventteknik.sql.SQLiteHelper;

import java.util.ArrayList;

public class KoordinatorAnggotaActivity extends AppCompatActivity {

    private FloatingActionButton tambah;
    private ListView listView;
    private DivisiAdapter adapter;
    ArrayList<DataDivisi> list = new ArrayList<>();
    private SQLiteHelper helper;
    String eventID;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koordinator_anggota);
        tambah = (FloatingActionButton) findViewById(R.id.FABTambahDivisi);
        listView = (ListView) findViewById(R.id.listViewDivisi);
        pref = getSharedPreferences("pref", Context.MODE_PRIVATE);
        helper = new SQLiteHelper(KoordinatorAnggotaActivity.this);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null) {
            eventID = bundle.getString("event_id");
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("id_event", eventID);
            editor.apply();
        }
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tambah = new Intent(KoordinatorAnggotaActivity.this, TambahKoordinatorAnggotaActivity.class);
                tambah.putExtra("event_id", eventID);
                startActivity(tambah);
            }
        });

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
        }

        menampilkanData(eventID);
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

    private void menampilkanData(String eventID) {
        list.clear();
        Cursor res = helper.getDataDivisi(eventID);
        while(res.moveToNext()) {
            String id = res.getString(0);
            String nama_divisi = res.getString(1);
            String nama_koordinator = res.getString(2);
            String jml_anggota = res.getString(3);
            String event_id_acara = res.getString(4);
            list.add(new DataDivisi(id, nama_divisi, nama_koordinator, jml_anggota, event_id_acara));
        }
        adapter = new DivisiAdapter(list, KoordinatorAnggotaActivity.this);
        listView.setAdapter(adapter);
    }
}