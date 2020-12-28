package com.progmoblanjut.eventteknik;

import android.app.Activity;
import android.content.Context;
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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.progmoblanjut.eventteknik.sql.SQLiteHelper;

public class TambahKoordinatorAnggotaActivity extends AppCompatActivity {
    private EditText divisi, koordinator, jmlAnggota;
    private Button tambah, batal;
    private ImageButton kembali;
    private String eventID;
    SQLiteHelper helper;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_koordinator_anggota);
        divisi = findViewById(R.id.TambahNamaDivisi);
        koordinator = findViewById(R.id.TambahNamaKoordinator);
        jmlAnggota = findViewById(R.id.JumlahAnggota);
        batal = (Button) findViewById(R.id.btnBatalTambahDivisi);
        tambah = (Button) findViewById(R.id.btnSimpanTambahDivisi);
        kembali = (ImageButton) findViewById(R.id.btnBackTambahDivisi);
        context = TambahKoordinatorAnggotaActivity.this;
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null) {
            eventID = bundle.getString("event_id");
        }
        helper = new SQLiteHelper(this);

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama_divisi = divisi.getText().toString();
                String nama_koordinator = koordinator.getText().toString();
                String jumlah_anggota = jmlAnggota.getText().toString();
                if(TextUtils.isEmpty(nama_divisi)) {
                    divisi.setError("Data tidak boleh kosong!");
                    divisi.requestFocus();
                } else if(TextUtils.isEmpty(nama_koordinator)) {
                    koordinator.setError("Data tidak boleh kosong!");
                    koordinator.requestFocus();
                } else if(TextUtils.isEmpty(jumlah_anggota)) {
                    jmlAnggota.setError("Data tidak boleh kosong!");
                    jmlAnggota.requestFocus();
                } else {
                    boolean isInsert = helper.tambahDivisi(nama_divisi, nama_koordinator, jumlah_anggota, eventID);
                    if(isInsert) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(TambahKoordinatorAnggotaActivity.this);
                        builder.setTitle("Data added!")
                                .setMessage("Do you want to add another data?")
                                .setIcon(R.drawable.ask)
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        divisi.setText(null);
                                        koordinator.setText(null);
                                        jmlAnggota.setText(null);
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent koor = new Intent(TambahKoordinatorAnggotaActivity.this, KoordinatorAnggotaActivity.class);
                                        koor.putExtra("event_id", eventID);
                                        context.startActivity(koor);
                                    }
                                });
                        builder.show();
                    } else {
                        Toast.makeText(TambahKoordinatorAnggotaActivity.this, "Data gagal disimpan", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TambahKoordinatorAnggotaActivity.this);
                builder.setTitle("Cancel?")
                        .setMessage("Are you sure want to cancel fill the data?")
                        .setIcon(R.drawable.warning)
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent home = new Intent(TambahKoordinatorAnggotaActivity.this, KoordinatorAnggotaActivity.class);
                                home.putExtra("event_id", eventID);
                                context.startActivity(home);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                builder.show();
            }
        });

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kembali = new Intent(TambahKoordinatorAnggotaActivity.this, KoordinatorAnggotaActivity.class);
                kembali.putExtra("event_id", eventID);
                context.startActivity(kembali);
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
