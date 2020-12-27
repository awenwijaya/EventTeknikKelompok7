package com.progmoblanjut.eventteknik.sql;

public class DataEventKepanitiaan {

    private String id;
    private String nama_event;
    private String tanggal_pelaksanaan;
    private String tanggal_rapat_perdana;
    private String tempat_pelaksanaan;
    private String tempat_rapat_perdana;
    private String deskripsi;

    public DataEventKepanitiaan(String id, String nama_event, String tanggal_pelaksanaan, String tanggal_rapat_perdana, String tempat_pelaksanaan, String tempat_rapat_perdana, String deskripsi) {
        this.id = id;
        this.nama_event = nama_event;
        this.tanggal_pelaksanaan = tanggal_pelaksanaan;
        this.tanggal_rapat_perdana = tanggal_rapat_perdana;
        this.tempat_pelaksanaan = tempat_pelaksanaan;
        this.tempat_rapat_perdana = tempat_rapat_perdana;
        this.deskripsi = deskripsi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama_event() {
        return nama_event;
    }

    public void setNama_event(String nama_event) {
        this.nama_event = nama_event;
    }

    public String getTanggal_pelaksanaan() {
        return tanggal_pelaksanaan;
    }

    public void setTanggal_pelaksanaan(String tanggal_pelaksanaan) {
        this.tanggal_pelaksanaan = tanggal_pelaksanaan;
    }

    public String getTanggal_rapat_perdana() {
        return tanggal_rapat_perdana;
    }

    public void setTanggal_rapat_perdana(String tanggal_rapat_perdana) {
        this.tanggal_rapat_perdana = tanggal_rapat_perdana;
    }

    public String getTempat_pelaksanaan() {
        return tempat_pelaksanaan;
    }

    public void setTempat_pelaksanaan(String tempat_pelaksanaan) {
        this.tempat_pelaksanaan = tempat_pelaksanaan;
    }

    public String getTempat_rapat_perdana() {
        return tempat_rapat_perdana;
    }

    public void setTempat_rapat_perdana(String tempat_rapat_perdana) {
        this.tempat_rapat_perdana = tempat_rapat_perdana;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
