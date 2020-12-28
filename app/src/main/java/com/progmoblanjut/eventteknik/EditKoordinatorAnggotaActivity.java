package com.progmoblanjut.eventteknik;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class EditKoordinatorAnggotaActivity extends AppCompatActivity {
    private EditText divisi, koordinator, jmlAnggota;
    private Button tambah, batal;
    private ImageButton kembali;
    SQLiteHelper helper;
    private String id, nama_divisi, nama_koordinator, jml_anggota, id_event;
    SharedPreferences editpref;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_koordinator_anggota);
        divisi = findViewById(R.id.NamaDivisi);
        koordinator = findViewById(R.id.NamaKoordinator);
        jmlAnggota = findViewById(R.id.JumlahAnggota);
        tambah = findViewById(R.id.btnSimpanEditDivisi);
        batal = findViewById(R.id.btnBatalEditDivisi);
        kembali = findViewById(R.id.btnBackEditDivisi);
        editpref = getSharedPreferences("pref", Context.MODE_PRIVATE);
        id_event = editpref.getString("id_event", " ");
        context = EditKoordinatorAnggotaActivity.this;
        helper = new SQLiteHelper(this);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null) {
            id = bundle.getString("id");
            nama_divisi = bundle.getString("divisi");
            nama_koordinator = bundle.getString("koordinator");
            jml_anggota = bundle.getString("jmlAnggota");
            divisi.setText(nama_divisi);
            koordinator.setText(nama_koordinator);
            jmlAnggota.setText(jml_anggota);
        }

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String divisi_nama = divisi.getText().toString();
                String koordinator_nama = koordinator.getText().toString();
                String anggota_jml = jmlAnggota.getText().toString();
                if(TextUtils.isEmpty(divisi_nama)) {
                    divisi.setError("Data tidak boleh kosong!");
                    divisi.requestFocus();
                } else if(TextUtils.isEmpty(koordinator_nama)) {
                    koordinator.setError("Data tidak boleh kosong!");
                    koordinator.requestFocus();
                } else if(TextUtils.isEmpty(anggota_jml)) {
                    jmlAnggota.setError("Data tidak boleh kosong!");
                    jmlAnggota.requestFocus();
                } else {
                    boolean isUpdate = helper.updateDivisi(id, divisi_nama, koordinator_nama, anggota_jml);
                    if(isUpdate) {
                        Toast.makeText(EditKoordinatorAnggotaActivity.this, "Data updated!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(EditKoordinatorAnggotaActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditKoordinatorAnggotaActivity.this);
                builder.setTitle("Cancel?")
                        .setMessage("Are you sure want to cancel?")
                        .setIcon(R.drawable.warning)
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent home = new Intent(EditKoordinatorAnggotaActivity.this, KoordinatorAnggotaActivity.class);
                                home.putExtra("event_id", id_event);
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
                Intent kembali = new Intent(EditKoordinatorAnggotaActivity.this, KoordinatorAnggotaActivity.class);
                kembali.putExtra("event_id", id_event);
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
