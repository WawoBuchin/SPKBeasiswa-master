package com.cobacoba.android.spkbeasiswa.Mahasiswa;

import com.cobacoba.android.spkbeasiswa.Jurusan.JurusanModel;

public class MahasiswaModel {
    String id_mhs;
    String nim;
    String nama_mhs;
    String jrsan;
    String smt_mhs;

    public MahasiswaModel() {
    }

    public MahasiswaModel(String id_mhs, String nim, String nama_mhs, String jurusan, String smt_mhs) {
        this.id_mhs = id_mhs;
        this.nim = nim;
        this.nama_mhs = nama_mhs;
        this.jrsan = jurusan;
        this.smt_mhs = smt_mhs;
    }

    public MahasiswaModel(String nim, String nama_mhs, String jurusan, String smt_mhs) {
        this.nim = nim;
        this.nama_mhs = nama_mhs;
        this.jrsan = jurusan;
        this.smt_mhs = smt_mhs;
    }

    public String getId_mhs() {
        return id_mhs;
    }

    public void setId_mhs(String id_mhs) {
        this.id_mhs = id_mhs;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama_mhs() {
        return nama_mhs;
    }

    public void setNama_mhs(String nama_mhs) {
        this.nama_mhs = nama_mhs;
    }

    public String getJrsan() {
        return jrsan;
    }

    public void setJrsan(String jurusan) {
        this.jrsan = jurusan;
    }

    public String getSmt_mhs() {
        return smt_mhs;
    }

    public void setSmt_mhs(String smt_mhs) {
        this.smt_mhs = smt_mhs;
    }
}