package com.progmoblanjut.eventteknik;

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

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.progmoblanjut.eventteknik.adapter.IntiAdapter;
import com.progmoblanjut.eventteknik.sql.DataPanitiaInti;
import com.progmoblanjut.eventteknik.sql.SQLiteHelper;

import java.util.ArrayList;

public class IntiPanitiaActivity extends AppCompatActivity {

    private FloatingActionButton tambah;
    private ListView listView;
    private IntiAdapter adapter;
    ArrayList<DataPanitiaInti> list = new ArrayList<>();
    private SQLiteHelper helper;
    String eventID, id_event;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inti_panitia);
        tambah = (FloatingActionButton) findViewById(R.id.FABTambahInti);
        listView = (ListView) findViewById(R.id.listViewPanitiaInti);
        helper = new SQLiteHelper(IntiPanitiaActivity.this);
        pref = getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null) {
            eventID = bundle.getString("event_id");
            editor.putString("event_id", eventID);
            editor.apply();
        }
        id_event = pref.getString("id_event", " ");

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent tambah = new Intent(IntiPanitiaActivity.this, TambahPanitiaIntiActivity.class);
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

    private void menampilkanData(String id_event) {
        list.clear();
        Cursor res = helper.getDataInti(id_event);
        while(res.moveToNext()) {
            String id = res.getString(0);
            String nama_ketuaacara = res.getString(1);
            String nama_wakilKetuaacara = res.getString(2);
            String nama_sekretarisacara = res.getString(3);
            String nama_bendaharaacara = res.getString(4);
            String event_id_acara = res.getString(5);
            list.add(new DataPanitiaInti(id, nama_ketuaacara, nama_wakilKetuaacara, nama_sekretarisacara, nama_bendaharaacara, event_id_acara));
        }
        adapter = new IntiAdapter(list, IntiPanitiaActivity.this);
        listView.setAdapter(adapter);
    }
}