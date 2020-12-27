package com.progmoblanjut.eventteknik.sql;

public class DataDivisi {
    private String id_divisi;
    private String nama_divisi;
    private String nama_koordinator;
    private String jumlah_anggota;
    private String event_id;

    public DataDivisi(String id_divisi, String nama_divisi, String nama_koordinator, String jumlah_anggota, String event_id) {
        this.id_divisi = id_divisi;
        this.nama_divisi = nama_divisi;
        this.nama_koordinator = nama_koordinator;
        this.jumlah_anggota = jumlah_anggota;
        this.event_id = event_id;
    }

    public String getId_divisi() {
        return id_divisi;
    }

    public void setId(String id_divisi) {
        this.id_divisi = id_divisi;
    }

    public String getNama_divisi() {
        return nama_divisi;
    }

    public void setNama_divisi(String nama_divisi) {
        this.nama_divisi = nama_divisi;
    }

    public String getNama_koordinator() {
        return nama_koordinator;
    }

    public void setNama_koordinator(String nama_koordinator) {
        this.nama_koordinator = nama_koordinator;
    }

    public String getJumlah_anggota() {
        return jumlah_anggota;
    }

    public void setJumlah_anggota(String jumlah_anggota) {
        this.jumlah_anggota = jumlah_anggota;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id= event_id;
    }
}
