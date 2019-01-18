package com.cobacoba.android.spkbeasiswa.Mahasiswa;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.cobacoba.android.spkbeasiswa.DBHelper;
import com.cobacoba.android.spkbeasiswa.R;

import java.util.List;

public class UpdateMahasiswa extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener  {
    Spinner spinner;
    String id, jur;
    EditText etNim,etNama,etSmt;
    FloatingActionButton fab;
    DBHelper dbAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_mahasiswa);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        id = getIntent().getStringExtra("id");
        etNim = (EditText) findViewById(R.id.itNIM);
        etNama = (EditText) findViewById(R.id.itNama);
        etSmt = (EditText) findViewById(R.id.itSemester);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);

        // Spinner element
        spinner = (Spinner)findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        loadSpinnerData();


        dbAdapter = new DBHelper(this,null);
        MahasiswaModel mahasiswa = dbAdapter.getDataMahasiswa(id);

        etNim.setText(mahasiswa.getNim());
        etNama.setText(mahasiswa.getNama_mhs());
        etSmt.setText(mahasiswa.getSmt_mhs());
    }
    private void loadSpinnerData(){
        // database handler
        DBHelper dbHelper = new DBHelper(getApplicationContext(), null);

        // Spinner Drop down elements
        List<String> lables = dbHelper.getAllJurusans();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        int selectionPosition= dataAdapter.getPosition(jur);
        spinner.setSelection(selectionPosition);
    }
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        if (adapterView.getId() == R.id.spinner){
            jur = adapterView.getItemAtPosition(position).toString();
            Toast.makeText(adapterView.getContext(),"You selected : " + jur, Toast.LENGTH_LONG).show();
        }



        //Toast.makeText(adapterView.getContext(), "You selected: " + teacher,
        //Toast.LENGTH_LONG).show();
    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.fab) {
            //Toast.makeText(this,"ini"+ getIntent().getStringExtra("id"),Toast.LENGTH_SHORT).show();
            //startActivity(new Intent(this,InsertTeacher.class));
            if (!etNim.getText().toString().equals("") && !etNama.getText().toString().equals("") && !jur.equals(" please select ") && !etSmt.getText().toString().equals("")) {
                dbAdapter = new DBHelper(getBaseContext(), null);
                String nim = etNim.getText().toString();
                String nama = etNama.getText().toString();
                String smt = etSmt.getText().toString();
                MahasiswaModel mahasiswa = new MahasiswaModel(id, nim, nama, jur, smt);
                dbAdapter.updateMahasiswa(mahasiswa);
                Toast.makeText(this, "Berhasil Di update", Toast.LENGTH_SHORT).show();
                onBackPressed();
                dbAdapter.close();
                //startActivity(new Intent(this, TeacherActivity.class));
            } else {
                Toast.makeText(getBaseContext(), "please fill in the empty field", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}