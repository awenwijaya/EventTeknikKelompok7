package com.progmoblanjut.eventteknik.sql;

public class DataPanitiaInti {

    private String id_inti;
    private String nama_ketua;
    private String nama_wakilKetua;
    private String nama_sekretaris;
    private String nama_bendahara;
    private String event_id;

    public DataPanitiaInti(String id_inti, String nama_ketua, String nama_wakilKetua, String nama_sekretaris, String nama_bendahara, String event_id) {
        this.id_inti = id_inti;
        this.nama_ketua = nama_ketua;
        this.nama_wakilKetua = nama_wakilKetua;
        this.nama_sekretaris = nama_sekretaris;
        this.nama_bendahara = nama_bendahara;
        this.event_id = event_id;
    }

    public String getId_inti() {
        return id_inti;
    }

    public void setId(String id_inti) {
        this.id_inti = id_inti;
    }

    public String getNama_ketua() {
        return nama_ketua;
    }

    public void setNama_ketua(String nama_ketua) {
        this.nama_ketua = nama_ketua;
    }

    public String getNama_wakilKetua() {
        return nama_wakilKetua;
    }

    public void setNama_wakilKetua(String nama_wakilKetua) {
        this.nama_wakilKetua = nama_wakilKetua;
    }

    public String getNama_sekretaris() {
        return nama_sekretaris;
    }

    public void setNama_sekretaris(String nama_sekretaris) {
        this.nama_sekretaris = nama_sekretaris;
    }

    public String getNama_bendahara() {
        return nama_bendahara;
    }

    public void setNama_bendahara(String nama_bendahara) {
        this.nama_bendahara= nama_bendahara;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id= event_id;
    }

}
