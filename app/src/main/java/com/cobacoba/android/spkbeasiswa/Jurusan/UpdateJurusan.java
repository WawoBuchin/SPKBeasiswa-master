package com.cobacoba.android.spkbeasiswa.Jurusan;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cobacoba.android.spkbeasiswa.DBHelper;
import com.cobacoba.android.spkbeasiswa.R;

public class UpdateJurusan extends AppCompatActivity implements View.OnClickListener {
    EditText txtTitle, txtDesc;
    FloatingActionButton fabSimpan;
    DBHelper dbAdapter;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_jurusan);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        id = getIntent().getStringExtra("id");
        txtTitle = (EditText) findViewById(R.id.itTitle);
        txtDesc = (EditText) findViewById(R.id.itDesc);

        dbAdapter = new DBHelper(this,null);
        JurusanModel jurusan = dbAdapter.getDataJurusan(id);
        fabSimpan = findViewById(R.id.fab);
        fabSimpan.setOnClickListener(this);

        txtTitle.setText(jurusan.getKode());
        txtDesc.setText(jurusan.getNama());
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.fab){
            //Toast.makeText(this,"ini"+ getIntent().getStringExtra("id"),Toast.LENGTH_SHORT).show();
            //startActivity(new Intent(this,InsertTeacher.class));
            String title = txtTitle.getText().toString();
            String desc = txtDesc.getText().toString();


            JurusanModel jurusan = new JurusanModel(id,title,desc);
            dbAdapter.updateJurusan(jurusan);
            Toast.makeText(this,"Berhasil Di update",Toast.LENGTH_SHORT).show();
            onBackPressed();
            //startActivity(new Intent(this, TeacherActivity.class));
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}