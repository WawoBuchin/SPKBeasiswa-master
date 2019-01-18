package com.cobacoba.android.spkbeasiswa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cobacoba.android.spkbeasiswa.Fuzzy.FuzzyMain;
import com.cobacoba.android.spkbeasiswa.Jurusan.JurusanMain;
import com.cobacoba.android.spkbeasiswa.Laporan.LaporanBeasiswa;
import com.cobacoba.android.spkbeasiswa.Mahasiswa.MahasiswaMain;


public class Home extends AppCompatActivity implements View.OnClickListener{
    Button btnjur, btnmhs, btnhit, btnlap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnjur = (Button) findViewById(R.id.buttonjur);
        btnjur.setOnClickListener(this);
        btnmhs = (Button) findViewById(R.id.buttonmhs);
        btnmhs.setOnClickListener(this);
        btnhit = (Button) findViewById(R.id.buttonhit);
        btnhit.setOnClickListener(this);
        btnlap = (Button) findViewById(R.id.buttonlap);
        btnlap.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonjur:
                Intent intent = new Intent(this, JurusanMain.class);
                startActivity(intent);
                break;
            case R.id.buttonmhs:
                Intent intent1 = new Intent(this, MahasiswaMain.class);
                startActivity(intent1);
                break;
            case R.id.buttonhit:
                Intent intent2 = new Intent(this, FuzzyMain.class);
                startActivity(intent2);
                break;
            case R.id.buttonlap:
                Intent intent3 = new Intent(this, LaporanBeasiswa.class);
                startActivity(intent3);
                break;
        }
    }
}
