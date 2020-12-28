package com.progmoblanjut.eventteknik.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.progmoblanjut.eventteknik.EditKoordinatorAnggotaActivity;
import com.progmoblanjut.eventteknik.R;
import com.progmoblanjut.eventteknik.sql.DataDivisi;
import com.progmoblanjut.eventteknik.sql.DataEventKepanitiaan;
import com.progmoblanjut.eventteknik.sql.SQLiteHelper;

import java.util.List;

public class DivisiAdapter extends BaseAdapter{
    private List<DataDivisi> listDivisi;
    private Context context;
    private TextView divisi, koordinator, jmlAnggota;
    private LinearLayout layout;
    private SQLiteHelper helper;

    public DivisiAdapter(List<DataDivisi> listDivisi, Context context) {
        this.listDivisi = listDivisi;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listDivisi.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_divisi, null);
        divisi = v.findViewById(R.id.NamaDivisiList);
        koordinator = v.findViewById(R.id.NamaKoordinatorList);
        jmlAnggota = v.findViewById(R.id.JumlahAnggotaList);
        layout = v.findViewById(R.id.linearLayoutDivisi);
        helper = new SQLiteHelper(context);
        divisi.setText(listDivisi.get(position).getNama_divisi());
        koordinator.setText(listDivisi.get(position).getNama_koordinator());
        jmlAnggota.setText(listDivisi.get(position).getJumlah_anggota());
        layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final CharSequence[] items = {"Update", "Delete"};
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Select Action")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                if(i == 0) {
                                    Intent update = new Intent(context, EditKoordinatorAnggotaActivity.class);
                                    update.putExtra("id", listDivisi.get(position).getId_divisi());
                                    update.putExtra("divisi", listDivisi.get(position).getNama_divisi());
                                    update.putExtra("koordinator", listDivisi.get(position).getNama_koordinator());
                                    update.putExtra("jmlAnggota", listDivisi.get(position).getJumlah_anggota());
                                    context.startActivity(update);
                                }
                                if(i == 1) {
                                    Integer isDelete = helper.deleteDivisi(listDivisi.get(position).getId_divisi());
                                    if(isDelete > 0) {
                                        Toast.makeText(context, "Successfully deleted!", Toast.LENGTH_SHORT).show();
                                        notifyDataSetChanged();
                                    } else {
                                        Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
                builder.show();
                return true;
            }
        });
        return v;
    }
}
