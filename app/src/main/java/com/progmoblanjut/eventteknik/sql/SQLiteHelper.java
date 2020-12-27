package com.progmoblanjut.eventteknik.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db_kepanitiaan";

    private static final String TABEL_EVENT = "event";
    private static final String KOLOM_ID = "id";
    private static final String KOLOM_NAMA_EVENT = "nama_event";
    private static final String KOLOM_TANGGAL_PELAKSANAAN = "tanggal_pelaksanaan";
    private static final String KOLOM_TANGGAL_RAPAT_PERDANA = "tanggal_rapat_perdana";
    private static final String KOLOM_TEMPAT_PELAKSANAAN = "tempat_pelaksanaan";
    private static final String KOLOM_TEMPAT_RAPAT_PERDANA = "tempat_rapat_perdana";
    private static final String KOLOM_DESKRIPSI = "deskripsi";

    private static final String TABEL_PENGGUNA = "pengguna";
    private static final String KOLOM_ID_PENGGUNA = "id";
    private static final String KOLOM_NAMA_PENGGUNA = "nama";
    private static final String KOLOM_EMAIL_PENGGUNA = "email";
    private static final String KOLOM_PASSWORD_PENGGUNA = "pengguna";

    private static final String TABEL_INTI = "inti";
    private static final String KOLOM_ID_INTI = "id";
    private static final String KOLOM_NAMA_KETUA = "nama_ketua";
    private static final String KOLOM_NAMA_WAKILKETUA = "nama_wakilKetua";
    private static final String KOLOM_NAMA_SEKRETARIS = "nama_sekretaris";
    private static final String KOLOM_NAMA_BENDAHARA = "nama_bendahara";
    private static final String KOLOM_EVENT_ID_INTI = "event_id";

    private static final String TABEL_DIVISI = "divisi";
    private static final String KOLOM_ID_DIVISI = "id";
    private static final String KOLOM_NAMA_DIVISI = "nama_divisi";
    private static final String KOLOM_NAMA_KOORDINATOR = "nama_koordinator";
    private static final String KOLOM_JUMLAH_ANGGOTA = "jml_anggota";
    private static final String KOLOM_EVENT_ID_DIVISI = "event_id";

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABEL_EVENT + " (" +
                KOLOM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KOLOM_NAMA_EVENT + " TEXT, " +
                KOLOM_TANGGAL_PELAKSANAAN + " TEXT, " +
                KOLOM_TANGGAL_RAPAT_PERDANA + " TEXT, " +
                KOLOM_TEMPAT_PELAKSANAAN + " TEXT," +
                KOLOM_TEMPAT_RAPAT_PERDANA + " TEXT," +
                KOLOM_DESKRIPSI + " TEXT)");

        db.execSQL("CREATE TABLE " + TABEL_PENGGUNA + " (" +
                KOLOM_ID_PENGGUNA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KOLOM_NAMA_PENGGUNA + " TEXT, " +
                KOLOM_EMAIL_PENGGUNA + " TEXT, " +
                KOLOM_PASSWORD_PENGGUNA + " TEXT)");

        db.execSQL("CREATE TABLE " + TABEL_INTI + " (" +
                KOLOM_ID_INTI + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KOLOM_NAMA_KETUA + " TEXT, " +
                KOLOM_NAMA_WAKILKETUA + " TEXT, " +
                KOLOM_NAMA_BENDAHARA + " TEXT, " +
                KOLOM_NAMA_SEKRETARIS + " TEXT," +
                KOLOM_EVENT_ID_INTI + " INTEGER)");

        db.execSQL("CREATE TABLE " + TABEL_DIVISI + " (" +
                KOLOM_ID_DIVISI + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KOLOM_NAMA_DIVISI + " TEXT, " +
                KOLOM_NAMA_KOORDINATOR + " TEXT, " +
                KOLOM_JUMLAH_ANGGOTA + " TEXT, " +
                KOLOM_EVENT_ID_DIVISI + " INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_EVENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_PENGGUNA);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_INTI);
        db.execSQL("DROP TABLE IF EXISTS " + TABEL_DIVISI);
    }

    public boolean insertEvent(String nama_event, String tanggal_pelaksanaan, String tanggal_rapat_perdana, String tempat_pelaksanaan, String tempat_rapat_perdana, String deskripsi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KOLOM_NAMA_EVENT, nama_event);
        values.put(KOLOM_TANGGAL_PELAKSANAAN, tanggal_pelaksanaan);
        values.put(KOLOM_TANGGAL_RAPAT_PERDANA, tanggal_rapat_perdana);
        values.put(KOLOM_TEMPAT_PELAKSANAAN, tempat_pelaksanaan);
        values.put(KOLOM_TEMPAT_RAPAT_PERDANA, tempat_rapat_perdana);
        values.put(KOLOM_DESKRIPSI, deskripsi);
        long result = db.insert(TABEL_EVENT, null, values);
        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getDataAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABEL_EVENT, null);
    }

    public boolean updateData(String id, String nama_event, String tanggal_pelaksanaan, String tanggal_rapat_perdana, String tempat_pelaksanaan, String tempat_rapat_perdana, String deskripsi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KOLOM_ID, id);
        values.put(KOLOM_NAMA_EVENT, nama_event);
        values.put(KOLOM_TANGGAL_PELAKSANAAN, tanggal_pelaksanaan);
        values.put(KOLOM_TANGGAL_RAPAT_PERDANA, tanggal_rapat_perdana);
        values.put(KOLOM_TEMPAT_PELAKSANAAN, tempat_pelaksanaan);
        values.put(KOLOM_TEMPAT_RAPAT_PERDANA, tempat_rapat_perdana);
        values.put(KOLOM_DESKRIPSI, deskripsi);
        db.update(TABEL_EVENT, values, KOLOM_ID + " =? ", new String[]{id});
        return true;
    }

    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABEL_EVENT, KOLOM_ID + " =? ", new String[]{id});
    }

    public boolean insertPengguna(String nama, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KOLOM_NAMA_PENGGUNA, nama);
        values.put(KOLOM_EMAIL_PENGGUNA, email);
        values.put(KOLOM_PASSWORD_PENGGUNA, password);
        long ins = db.insert(TABEL_PENGGUNA, null, values);
        if(ins == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean chkemail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABEL_PENGGUNA + " WHERE " + KOLOM_EMAIL_PENGGUNA + " =? ", new String[]{email});
        if(cursor.getCount()>0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean login(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABEL_PENGGUNA + " WHERE " + KOLOM_EMAIL_PENGGUNA + " =? AND " + KOLOM_PASSWORD_PENGGUNA + " =?", new String[]{email, password});
        if(cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean tambahInti(String nama_ketua, String nama_wakilKetua, String nama_sekretaris, String nama_bendahara, String event_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KOLOM_NAMA_KETUA, nama_ketua);
        values.put(KOLOM_NAMA_WAKILKETUA, nama_wakilKetua);
        values.put(KOLOM_NAMA_SEKRETARIS, nama_sekretaris);
        values.put(KOLOM_NAMA_BENDAHARA, nama_bendahara);
        values.put(KOLOM_EVENT_ID_INTI, event_id);
        long result = db.insert(TABEL_INTI, null, values);
        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getDataInti(String id_event) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABEL_INTI + " WHERE " + KOLOM_EVENT_ID_INTI + " =? ", new String[]{id_event});
    }

    public boolean updateInti(String id, String nama_ketua, String nama_wakilKetua, String nama_sekretaris, String nama_bendahara) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KOLOM_ID_INTI, id);
        values.put(KOLOM_NAMA_KETUA, nama_ketua);
        values.put(KOLOM_NAMA_WAKILKETUA, nama_wakilKetua);
        values.put(KOLOM_NAMA_SEKRETARIS, nama_sekretaris);
        values.put(KOLOM_NAMA_BENDAHARA, nama_bendahara);
        db.update(TABEL_INTI, values, KOLOM_ID_INTI + " =? ", new String[]{id});
        return true;
    }

    public Integer deleteInti(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABEL_INTI, KOLOM_ID_INTI + " =? ", new String[]{id});
    }

    public boolean tambahDivisi(String nama_divisi, String nama_koordinator, String jumlah_anggota, String event_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KOLOM_NAMA_DIVISI, nama_divisi);
        values.put(KOLOM_NAMA_KOORDINATOR, nama_koordinator);
        values.put(KOLOM_JUMLAH_ANGGOTA, jumlah_anggota);
        values.put(KOLOM_EVENT_ID_INTI, event_id);
        long result = db.insert(TABEL_DIVISI, null, values);
        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getDataDivisi(String id_event) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABEL_DIVISI + " WHERE " + KOLOM_EVENT_ID_DIVISI + " =? ", new String[]{id_event});
    }

    public boolean updateDivisi(String id, String divisi_nama, String koordinator_nama, String anggota_jumlah) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KOLOM_ID_DIVISI, id);
        values.put(KOLOM_NAMA_DIVISI, divisi_nama);
        values.put(KOLOM_NAMA_KOORDINATOR, koordinator_nama);
        values.put(KOLOM_JUMLAH_ANGGOTA, anggota_jumlah);
        db.update(TABEL_DIVISI, values, KOLOM_ID_DIVISI + " =? ", new String[]{id});
        return true;
    }

    public Integer deleteDivisi(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABEL_DIVISI, KOLOM_ID_DIVISI + " =? ", new String[]{id});
    }

}
