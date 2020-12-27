package com.progmoblanjut.eventteknik;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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

public class TambahPanitiaIntiActivity extends AppCompatActivity {

    private EditText ketua, wakil, sekretaris, bendahara;
    private Button tambah, batal;
    private ImageButton kembali;
    private String eventID;
    SQLiteHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_panitia_inti);
        ketua = findViewById(R.id.NamaKetua);
        wakil = findViewById(R.id.NamaWakil);
        sekretaris = findViewById(R.id.NamaSekretaris);
        bendahara = findViewById(R.id.NamaBendahara);
        batal = (Button) findViewById(R.id.btnBatalPanitiaInti);
        tambah = (Button) findViewById(R.id.btnSimpanPanitiaInti);
        kembali = (ImageButton) findViewById(R.id.btnBackPanitiaInti);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null) {
            eventID = bundle.getString("event_id");
        }
        helper = new SQLiteHelper(this);

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama_ketua = ketua.getText().toString();
                String nama_wakil = wakil.getText().toString();
                String nama_sekretaris = sekretaris.getText().toString();
                String nama_bendahara = bendahara.getText().toString();
                if(TextUtils.isEmpty(nama_ketua)) {
                    ketua.setError("Data tidak boleh kosong!");
                    ketua.requestFocus();
                } else if(TextUtils.isEmpty(nama_wakil)) {
                    wakil.setError("Data tidak boleh kosong!");
                    wakil.requestFocus();
                } else if(TextUtils.isEmpty(nama_sekretaris)) {
                    sekretaris.setError("Data tidak boleh kosong!");
                    sekretaris.requestFocus();
                } else if(TextUtils.isEmpty(nama_bendahara)) {
                    bendahara.setError("Data tidak boleh kosong!");
                    bendahara.requestFocus();
                } else {
                    boolean isInsert = helper.tambahInti(nama_ketua, nama_wakil, nama_sekretaris, nama_bendahara, eventID);
                    if(isInsert) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(TambahPanitiaIntiActivity.this);
                        builder.setTitle("Data added!")
                                .setMessage("Do you want to add another data?")
                                .setIcon(R.drawable.ask)
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        ketua.setText(null);
                                        wakil.setText(null);
                                        sekretaris.setText(null);
                                        bendahara.setText(null);
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent home = new Intent(TambahPanitiaIntiActivity.this, IntiPanitiaActivity.class);
                                        startActivity(home);
                                    }
                                });
                        builder.show();
                    } else {
                        Toast.makeText(TambahPanitiaIntiActivity.this, "Data gagal disimpan", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TambahPanitiaIntiActivity.this);
                builder.setTitle("Cancel?")
                        .setMessage("Are you sure want to cancel fill the data?")
                        .setIcon(R.drawable.warning)
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent home = new Intent(TambahPanitiaIntiActivity.this, IntiPanitiaActivity.class);
                                startActivity(home);
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
                Intent kembali = new Intent(TambahPanitiaIntiActivity.this, IntiPanitiaActivity.class);
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