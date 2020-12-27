package com.progmoblanjut.eventteknik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class PanitiaActivity extends AppCompatActivity {

    ImageButton kembali, inti, anggota;
    String eventID;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panitia);
        kembali = (ImageButton) findViewById(R.id.btnBackPanitia);
        inti = (ImageButton) findViewById(R.id.btnInti);
        anggota = (ImageButton) findViewById(R.id.btnAnggota);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            eventID = bundle.getString("id_event");
        }

        inti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inti = new Intent(PanitiaActivity.this, IntiPanitiaActivity.class);
                inti.putExtra("event_id",eventID);
                startActivity(inti);
            }
        });

        anggota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent anggota = new Intent(PanitiaActivity.this, KoordinatorAnggotaActivity.class);
                anggota.putExtra("event_id",eventID);
                startActivity(anggota);
            }
        });

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kembali = new Intent(PanitiaActivity.this, MainActivity.class);
                startActivity(kembali);
            }
        });


    }
}