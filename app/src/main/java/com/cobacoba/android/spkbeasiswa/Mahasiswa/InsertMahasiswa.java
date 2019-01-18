package com.cobacoba.android.spkbeasiswa.Mahasiswa;

import android.content.Context;
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

public class InsertMahasiswa extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    Spinner spinner;
    String jur;
    EditText etNim,etNama,etSmt;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_mahasiswa);

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
    }


    private void loadSpinnerData(){
        // database handler
        DBHelper dbHelper = new DBHelper(this, null);

        // Spinner Drop down elements
        List<String> lables = dbHelper.getAllJurusans();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.fab){
            //Toast.makeText(getBaseContext(),"Data Saved" + et_SubjectName.getText().toString() + teacher + et_SubjectRoom.getText().toString(),Toast.LENGTH_SHORT).show();
            if (!etNim.getText().toString().equals("") && !etNama.getText().toString().equals("") && !jur.equals(" please select ") && !etSmt.getText().toString().equals("")) {
                DBHelper dbHelper = new DBHelper(getBaseContext(), null);
                String nim = etNim.getText().toString();
                String nama = etNama.getText().toString();
                String smt = etSmt.getText().toString();

                MahasiswaModel mahasiswa = new MahasiswaModel(nim, nama, jur, smt);
                if(dbHelper.insertMahasiswa(mahasiswa) != -1){
                    Toast.makeText(getBaseContext(),"Data Saved",Toast.LENGTH_SHORT).show();
                    //kosongkanData();
                    //startActivity(new Intent(this, TeacherActivity.class));
                    onBackPressed();
                } else{
                    Toast.makeText(getBaseContext(),"Data Error",Toast.LENGTH_SHORT).show();
                }
                dbHelper.close();
            } else{
                Toast.makeText(getBaseContext(),"please fill in the empty field",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
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
}
