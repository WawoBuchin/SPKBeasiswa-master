package com.cobacoba.android.spkbeasiswa.Fuzzy;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cobacoba.android.spkbeasiswa.DBHelper;
import com.cobacoba.android.spkbeasiswa.R;

import java.util.List;

public class FuzzyMain extends AppCompatActivity implements View.OnClickListener , AdapterView.OnItemSelectedListener{
    Spinner spinner_Fuzzy;
    String nama,status;
    EditText etUang,etTang,etIPK;
    TextView tvSkor,tvStatus;
    FloatingActionButton fab;
    int i;
    float kemampuanEkonomi;
    float penghasilan1, tanggungan1, ipk;
    float[] derajatEkonomi = new float[3];
    float[] derajatIpk = new float[2];
    float[] p = new float[6];
    float[] z = new float[6];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuzzy_main);
        etUang = (EditText) findViewById(R.id.itUang);
        etTang = (EditText) findViewById(R.id.itTang);
        etIPK = (EditText) findViewById(R.id.itipk);
        tvSkor = (TextView) findViewById(R.id.tvSkor);
        tvStatus = (TextView) findViewById(R.id.tvStatus);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);

        // Spinner element
        spinner_Fuzzy = (Spinner)findViewById(R.id.spinner_Fuzzy);

        // Spinner click listener
        spinner_Fuzzy.setOnItemSelectedListener(this);
        loadSpinnerData();
    }


    private void loadSpinnerData(){
        // database handler
        DBHelper dbHelper = new DBHelper(this, null);

        // Spinner Drop down elements
        List<String> lables = dbHelper.getAllMahasiswas();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner_Fuzzy.setAdapter(dataAdapter);
    }



    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.fab){
            //Toast.makeText(getBaseContext(),"Data Saved" + et_SubjectName.getText().toString() + teacher + et_SubjectRoom.getText().toString(),Toast.LENGTH_SHORT).show();
            if (!nama.equals(" please select ") && !etUang.getText().toString().equals("") && !etTang.getText().toString().equals("") &&  !etIPK.getText().toString().equals("")) {
                DBHelper dbHelper = new DBHelper(getBaseContext(), null);
                String penghasilan = etUang.getText().toString();
                String tanggungan = etTang.getText().toString();
                String ipka = etIPK.getText().toString();
                ipk = Float.parseFloat(ipka);
                penghasilan1 = Float.parseFloat(etUang.getText().toString());
                tanggungan1 = Float.parseFloat(etTang.getText().toString());

                //Hitung Kemampuan Ekonomi
                kemampuanEkonomi = hitKemampuanEkonomi(penghasilan1, tanggungan1);
                //printf("\nKemampuan Ekonomi = %.3f\n", kemampuanEkonomi);

                //Ekonomi Rendah
                derajatEkonomi[0] = hitEkonomiRendah(kemampuanEkonomi);
                //Ekonomi Menengah
                derajatEkonomi[1] = hitEkonomiMenengah(kemampuanEkonomi);
                //Ekonomi Tinggi
                derajatEkonomi[2] = hitEkonomiTinggi(kemampuanEkonomi);
                //IPK Rendah
                derajatIpk[0] = hitIpkRendah(ipk);
                //IPK Tinggi
                derajatIpk[1] = hitIpkTinggi(ipk);

                //Predikat
                p[0] = alphaPredikat(derajatEkonomi[0], derajatIpk[0]);
                p[1] = alphaPredikat(derajatEkonomi[0], derajatIpk[1]);
                p[2] = alphaPredikat(derajatEkonomi[1], derajatIpk[0]);
                p[3] = alphaPredikat(derajatEkonomi[1], derajatIpk[1]);
                p[4] = alphaPredikat(derajatEkonomi[2], derajatIpk[0]);
                p[5] = alphaPredikat(derajatEkonomi[2], derajatIpk[1]);


                //z
                z[0] = himpunanPertimbangan(p[0]);
                z[1] = himpunanSetuju(p[1]);
                z[2] = himpunanPertimbangan(p[2]);
                z[3] = himpunanSetuju(p[3]);
                z[4] = himpunanPertimbangan(p[4]);
                z[5] = himpunanSetuju(p[5]);


                float a = (p[0]*z[0]) + (p[1]*z[1]) + (p[2]*z[2]) + (p[3]*z[3]) + (p[4]*z[4]) + (p[5]*z[5]);
                float b = (p[0]+p[1]+p[2]+p[3]+p[4]+p[5]);
                float akhir = a/b;
                if(akhir <=2 ){
                    status = "Tidak dapat";
                }else if(akhir > 2 && akhir < 3){
                    status = "Dipertimbangkan";
                }else{
                    status = "Dapat beasiswa";
                }
                for(i=0;i<=5;i++){
                    Log.d("hasil","z["+i+"] =" +z[i]);
                }
                //Log.d("hasil","hasil " +akhir);
                //Log.d("hasil","hasilnya adalah " + kemampuanEkonomi);

                //Output
                /*for(i = 0; i<=2; i++){
                    Log.d("Tingkat Ekonomi","=  " + derajatEkonomi[i]);
                }
                for(i = 0; i<=1; i++){
                    Log.d("Tingkat IPK","=  " + derajatIpk[i]);
                }

                for(i=0;i<=5;i++){
                    Log.d("Predikat p["+i+"]  ","=  " + p[i]);
                }*/

                //String status = "disetujui";
                //Toast.makeText(getBaseContext(),"please fill in the empty field" + akhir,Toast.LENGTH_SHORT).show();
                FuzzyModel fuzzy = new FuzzyModel(nama,penghasilan, tanggungan, ipka ,String.valueOf(akhir), status);
                if(dbHelper.insertFuzzy(fuzzy) != -1){
                    tvStatus.setText(status);
                    tvSkor.setText(String.valueOf(akhir));
                    Toast.makeText(getBaseContext(),"Data Saved",Toast.LENGTH_SHORT).show();
                    //kosongkanData();
                    //startActivity(new Intent(this, TeacherActivity.class));
                   // onBackPressed();
                } else{
                    Toast.makeText(getBaseContext(),"Data Error" + dbHelper.insertFuzzy(fuzzy),Toast.LENGTH_SHORT).show();
                }
                dbHelper.close();
            } else{
                Toast.makeText(getBaseContext(),"please fill in the empty field",Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        if (adapterView.getId() == R.id.spinner_Fuzzy){
            nama = adapterView.getItemAtPosition(position).toString();
            Toast.makeText(adapterView.getContext(),"You selected : " + nama, Toast.LENGTH_LONG).show();
        }



        //Toast.makeText(adapterView.getContext(), "You selected: " + teacher,
        //Toast.LENGTH_LONG).show();
    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public float hitKemampuanEkonomi(float penghasilan, float tanggungan){
        return penghasilan/tanggungan;
    }

    public float hitEkonomiRendah(float kemampuanEkonomi){
        float x=0;
        if(kemampuanEkonomi >= 1000000){
            x = 0;
        }else if(kemampuanEkonomi>=750000 && kemampuanEkonomi<=1000000){
            x = (1000000 - kemampuanEkonomi)/250000;
        }else if(kemampuanEkonomi<=750000){
            x= 1;
        }
        return x;
    }

    public float hitEkonomiMenengah(float kemampuanEkonomi){
        float x=0;
        if(kemampuanEkonomi<=750000 || kemampuanEkonomi >=2000000){
            x = 0;
        }else if(kemampuanEkonomi >= 750000 && kemampuanEkonomi <=1000000){
            x = (kemampuanEkonomi - 750000) / 250000;
        }else if(kemampuanEkonomi >= 1750000 && kemampuanEkonomi <= 2000000){
            x = (2000000 - kemampuanEkonomi) / 250000;
        }else if(kemampuanEkonomi >= 1000000 && kemampuanEkonomi <= 1750000){
            x = 1;
        }
        return x;
    }

    public float hitEkonomiTinggi(float kemampuanEkonomi){
        float x =0;
        if(kemampuanEkonomi<=1750000){
            x = 0;
        }else if(kemampuanEkonomi >= 1750000 && kemampuanEkonomi <=2000000){
            x = (kemampuanEkonomi - 1750000) / 250000;
        }else if(kemampuanEkonomi >= 2000000){
            x = 1;
        }
        return x;
    }

    public float hitIpkRendah(float ipk){
        float w = 0;
        if(ipk>=3.5){
            w = 0;
        }else if(ipk>=3 && ipk<=3.5){
            w = (float) ((3.5 - ipk) / 0.5);
        }else if(ipk<=3){
            w = 1;
        }
        return w;
    }

    public float hitIpkTinggi(float ipk){
        float w = 0;
        if(ipk<=3){
            w = 0;
        }else if(ipk>=3 && ipk<=3.5){
            w = (float) ((ipk - 3) / 0.5);
        }else if(ipk >= 3.5){
            w = 1;
        }
        return w;
    }

    public float alphaPredikat(float derajatEkonomi, float derajatIpk){
        if(derajatEkonomi > derajatIpk){
            return derajatIpk;
        }else{
            return derajatEkonomi;
        }
    }
    /*
    public float himpunanPertimbangan(float predikat){
        float z = 3 - predikat;
        return z;
    }

    public float himpunanSetuju(float predikat){
        float z = 2 - predikat;
        return z;
    }*/

    public float himpunanPertimbangan(float predikat){
        float z = ((predikat*(3-2))-3)*-1;
        return z;
    }

    public float himpunanSetuju(float predikat){
        float z = ((predikat*(3-2))+2);
        return z;
    }
}
