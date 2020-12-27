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

public class EditPanitiaIntiActivity extends AppCompatActivity {

    private EditText ketua, wakil, bendahara, sekretaris;
    private Button tambah, batal;
    private ImageButton kembali;
    SQLiteHelper helper;
    private String id, nama_ketua, nama_wakil, nama_bendahara, nama_sekretaris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_panitia_inti);
        ketua = findViewById(R.id.NamaKetuaEdit);
        wakil = findViewById(R.id.NamaWakilEdit);
        bendahara = findViewById(R.id.NamaBendaharaEdit);
        sekretaris = findViewById(R.id.NamaSekretarisEdit);
        tambah = findViewById(R.id.btnSimpanEditInti);
        batal = findViewById(R.id.btnBatalEditInti);
        kembali = findViewById(R.id.btnBackEditInti);
        helper = new SQLiteHelper(this);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null) {
            id = bundle.getString("id");
            nama_ketua = bundle.getString("ketua");
            nama_wakil = bundle.getString("wakil_ketua");
            nama_bendahara = bundle.getString("bendahara");
            nama_sekretaris = bundle.getString("sekretaris");
            ketua.setText(nama_ketua);
            wakil.setText(nama_wakil);
            bendahara.setText(nama_bendahara);
            sekretaris.setText(nama_sekretaris);
        }

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama_ketua_inti = ketua.getText().toString();
                String nama_wakil_inti = wakil.getText().toString();
                String nama_bendahara_inti = bendahara.getText().toString();
                String nama_sekretaris_inti = sekretaris.getText().toString();
                if(TextUtils.isEmpty(nama_ketua_inti)) {
                    ketua.setError("Data tidak boleh kosong!");
                    ketua.requestFocus();
                } else if(TextUtils.isEmpty(nama_wakil_inti)) {
                    wakil.setError("Data tidak boleh kosong!");
                    wakil.requestFocus();
                } else if(TextUtils.isEmpty(nama_bendahara_inti)) {
                    bendahara.setError("Data tidak boleh kosong!");
                    bendahara.requestFocus();
                } else if(TextUtils.isEmpty(nama_sekretaris_inti)) {
                    sekretaris.setError("Data tidak boleh kosong!");
                    sekretaris.requestFocus();
                } else {
                    boolean isUpdate = helper.updateInti(id, nama_ketua_inti, nama_wakil_inti, nama_sekretaris_inti, nama_bendahara_inti);
                    if(isUpdate) {
                        Toast.makeText(EditPanitiaIntiActivity.this, "Data updated!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(EditPanitiaIntiActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditPanitiaIntiActivity.this);
                builder.setTitle("Cancel?")
                        .setMessage("Are you sure want to cancel?")
                        .setIcon(R.drawable.warning)
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent home = new Intent(EditPanitiaIntiActivity.this, IntiPanitiaActivity.class);
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
                Intent kembali = new Intent(EditPanitiaIntiActivity.this, IntiPanitiaActivity.class);
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