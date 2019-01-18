package com.cobacoba.android.spkbeasiswa.Laporan;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cobacoba.android.spkbeasiswa.DBHelper;
import com.cobacoba.android.spkbeasiswa.Fuzzy.FuzzyRecyclerViewAdapter;
import com.cobacoba.android.spkbeasiswa.R;

public class LaporanBeasiswa extends AppCompatActivity {
    RecyclerView rv;
    FuzzyRecyclerViewAdapter adapter;
    DBHelper dbHelper;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan_beasiswa);

        rv = findViewById(R.id.listLaporan);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        adapter = new FuzzyRecyclerViewAdapter(new DBHelper(this,null).getAllFuzzy());
        rv.setAdapter(adapter);
    }
}
