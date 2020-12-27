package com.progmoblanjut.eventteknik;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.progmoblanjut.eventteknik.sql.SQLiteHelper;

public class EditEventActivity extends AppCompatActivity {

    private EditText namaEvent, tanggal, tanggalRapatPerdana, tempatRapatPerdana, tempatPelaksanaan, deskripsi;
    private Button tambah, batal;
    private ImageButton kembali;
    SQLiteHelper helper;
    private String id, nama_acara, tanggal_acara, tanggal_rapatperdana, tempat_pelaksanaanacara, tempat_rapatperdana, deskripsiAcara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);

        namaEvent = findViewById(R.id.NamaEventEdit);
        tanggal = findViewById(R.id.TanggalPelaksanaanEdit);
        tanggalRapatPerdana = findViewById(R.id.TanggalRapatPerdanaEdit);
        tempatRapatPerdana = findViewById(R.id.TempatRapatPerdanaEdit);
        tempatPelaksanaan = findViewById(R.id.TempatPelaksanaanEdit);
        deskripsi = findViewById(R.id.DeskripsiEdit);
        tambah = (Button) findViewById(R.id.btnSimpanEdit);
        batal = (Button) findViewById(R.id.btnBatalEdit);
        kembali = (ImageButton) findViewById(R.id.btnBackEdit);
        helper = new SQLiteHelper(this);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null) {
            id = bundle.getString("id");
            nama_acara = bundle.getString("nama_event");
            tanggal_acara = bundle.getString("tanggal_pelaksanaan");
            tanggal_rapatperdana = bundle.getString("tanggal_rapat_perdana");
            tempat_pelaksanaanacara = bundle.getString("tempat_pelaksanaan");
            tempat_rapatperdana = bundle.getString("tempat_rapat_perdana");
            deskripsiAcara = bundle.getString("deskripsi");

            namaEvent.setText(nama_acara);
            tanggal.setText(tanggal_acara);
            tanggalRapatPerdana.setText(tanggal_rapatperdana);
            tempatRapatPerdana.setText(tempat_rapatperdana);
            tempatPelaksanaan.setText(tempat_pelaksanaanacara);
            deskripsi.setText(deskripsiAcara);

        }

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama_event_acara = namaEvent.getText().toString();
                String tanggal_pelaksanaan_acara = tanggal.getText().toString();
                String tanggal_rapat_perdana_acara = tanggalRapatPerdana.getText().toString();
                String tempat_rapat_perdana_acara = tempatRapatPerdana.getText().toString();
                String tempat_pelaksanaan_acara = tempatPelaksanaan.getText().toString();
                String deskripsi_acara = deskripsi.getText().toString();

                if(TextUtils.isEmpty(nama_event_acara)) {
                    namaEvent.setError("Data tidak boleh kosong!");
                    namaEvent.requestFocus();
                } else if(TextUtils.isEmpty(tanggal_pelaksanaan_acara)) {
                    tanggal.setError("Data tidak boleh kosong!");
                    tanggal.requestFocus();
                } else if(TextUtils.isEmpty(tanggal_rapat_perdana_acara)) {
                    tanggalRapatPerdana.setError("Data tidak boleh kosong!");
                    tanggalRapatPerdana.requestFocus();
                } else if(TextUtils.isEmpty(tempat_rapat_perdana_acara)) {
                    tempatRapatPerdana.setError("Data tidak boleh kosong!");
                    tempatRapatPerdana.requestFocus();
                } else if(TextUtils.isEmpty(tempat_pelaksanaan_acara)) {
                    tempatPelaksanaan.setError("Data tidak boleh kosong!");
                    tempatPelaksanaan.requestFocus();
                } else if(TextUtils.isEmpty(deskripsi_acara)) {
                    deskripsi.setError("Data tidak boleh kosong!");
                    deskripsi.requestFocus();
                } else {
                    boolean isUpdate = helper.updateData(id, nama_event_acara, tanggal_pelaksanaan_acara, tanggal_rapat_perdana_acara, tempat_pelaksanaan_acara, tempat_rapat_perdana_acara, deskripsi_acara);
                    if(isUpdate) {
                        Toast.makeText(EditEventActivity.this, "Data updated!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(EditEventActivity.this, "Data gagal disimpan", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditEventActivity.this);
                builder.setTitle("Cancel?")
                        .setMessage("Are you sure want to cancel?")
                        .setIcon(R.drawable.warning)
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent home = new Intent(EditEventActivity.this, MainActivity.class);
                                startActivity(home);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kembali = new Intent(EditEventActivity.this, MainActivity.class);
                startActivity(kembali);
            }
        });

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
        }
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

}