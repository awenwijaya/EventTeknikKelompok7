package com.progmoblanjut.eventteknik.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.progmoblanjut.eventteknik.EditPanitiaIntiActivity;
import com.progmoblanjut.eventteknik.R;
import com.progmoblanjut.eventteknik.sql.DataPanitiaInti;
import com.progmoblanjut.eventteknik.sql.SQLiteHelper;

import java.util.List;

public class IntiAdapter extends BaseAdapter {

    private List<DataPanitiaInti> listInti;
    private Context context;
    private TextView ketua, wakil, bendahara, sekretaris;
    private LinearLayout layout;
    private SQLiteHelper helper;

    public IntiAdapter(List<DataPanitiaInti> listInti, Context context) {
        this.listInti = listInti;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listInti.size();
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
        View v = LayoutInflater.from(context).inflate(R.layout.list_inti, null);
        ketua = v.findViewById(R.id.NamaKetuaList);
        wakil = v.findViewById(R.id.NamaWakilKetuaList);
        bendahara = v.findViewById(R.id.NamaBendaharaList);
        sekretaris = v.findViewById(R.id.NamaSekretarisList);
        layout = v.findViewById(R.id.linearLayoutInti);
        helper = new SQLiteHelper(context);
        ketua.setText(listInti.get(position).getNama_ketua());
        wakil.setText(listInti.get(position).getNama_wakilKetua());
        bendahara.setText(listInti.get(position).getNama_bendahara());
        sekretaris.setText(listInti.get(position).getNama_sekretaris());
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
                                    Intent update = new Intent(context, EditPanitiaIntiActivity.class);
                                    update.putExtra("id", listInti.get(position).getId_inti());
                                    update.putExtra("ketua", listInti.get(position).getNama_ketua());
                                    update.putExtra("wakil_ketua", listInti.get(position).getNama_wakilKetua());
                                    update.putExtra("sekretaris", listInti.get(position).getNama_sekretaris());
                                    update.putExtra("bendahara", listInti.get(position).getNama_bendahara());
                                    context.startActivity(update);
                                }
                                if(i == 1) {
                                    Integer isDelete = helper.deleteInti(listInti.get(position).getId_inti());
                                    if(isDelete > 0) {
                                        Toast.makeText(context, "Successfully deleted!", Toast.LENGTH_SHORT).show();
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
