package com.cobacoba.android.spkbeasiswa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.cobacoba.android.spkbeasiswa.Fuzzy.FuzzyModel;
import com.cobacoba.android.spkbeasiswa.Jurusan.JurusanModel;
import com.cobacoba.android.spkbeasiswa.Mahasiswa.MahasiswaModel;


import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    //logcat tag
    private static final String LOG = "DatabaseHelper";

    //database version
    private static final int DATABASE_VERSION = 1;

    //database name
    private static final String DATABASE_NAME = "SPK_Beasiswa";

    public DBHelper(Context context, SQLiteDatabase.CursorFactory factory){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    //table jurusan
    private static final String TABLE_JURUSAN = "tb_jurusan";
    //column
    private static final String COL_ID_JUR = "id";
    private static final String COL_KD_JUR = "kd_jur";
    private static final String COL_NAMA_JUR = "nama_jur";

    private static final String CREATE_TABLE_JURUSAN = "CREATE TABLE " + TABLE_JURUSAN + " ("
            + COL_ID_JUR + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_KD_JUR + " TEXT,"
            + COL_NAMA_JUR + " TEXT )";

    //table mahasiswa
    private static final String TABLE_MAHASISWA = "tb_mahasiswa";
    //column
    private static final String COL_ID_MHS = "id";
    private static final String COL_NIM_MHS = "nim";
    private static final String COL_NAMA_MHS = "nama";
    private static final String COL_JURUSAN = "jurusan";
    private static final String COL_SMT_MHS = "semester";

    private static final String CREATE_TABLE_MAHASISWA = "CREATE TABLE " + TABLE_MAHASISWA + " ("
            + COL_ID_MHS + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NIM_MHS + " TEXT, "
            + COL_NAMA_MHS + " TEXT, " + COL_JURUSAN + " TEXT, " + COL_SMT_MHS + " INTEGER )";

    //table fuzzy
    private static final String TABLE_FUZZY = "tb_fuzzy";
    //column
    private static final String COL_ID_FUZZY = "id";
    private static final String COL_NAMA_FUZZY = "nama";
    private static final String COL_UANG_FUZZY = "uang";
    private static final String COL_TANG_FUZZY = "tanggungan";
    private static final String COL_IPK_FUZZY = "ipk";
    private static final String COL_SKOR_FUZZY = "skor";
    private static final String COL_STATUS_FUZZY = "status";

    private static final String CREATE_TABLE_FUZZY = "CREATE TABLE " + TABLE_FUZZY + " ("
            + COL_ID_FUZZY + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAMA_FUZZY + " TEXT, "
            + COL_UANG_FUZZY + " TEXT, " + COL_TANG_FUZZY + " TEXT, " + COL_IPK_FUZZY + " TEXT, "
            + COL_SKOR_FUZZY + " TEXT," + COL_STATUS_FUZZY + " TEXT )";



    @Override
    public void onCreate(SQLiteDatabase db){
        //create table
        db.execSQL(CREATE_TABLE_JURUSAN);
        db.execSQL(CREATE_TABLE_MAHASISWA);
        db.execSQL(CREATE_TABLE_FUZZY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //on upgrade drop older table
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_JURUSAN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MAHASISWA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FUZZY);
        //create new table
        onCreate(db);
    }
    //method jurusan
    public long insertJurusan(JurusanModel jur) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_KD_JUR, jur.getKode());
        values.put(COL_NAMA_JUR, jur.getNama());


        // insert row
        long sequence = db.insert(TABLE_JURUSAN, null, values);


        return sequence;
    }


    public ArrayList<JurusanModel> getAllJurusan(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<JurusanModel> listJurusan = new ArrayList<>();
        String sql = " SELECT * FROM " + TABLE_JURUSAN;
        Cursor cursor = db.rawQuery(sql,null);
        Log.d("haha", "kosong" + cursor.getCount());
        if(cursor != null && cursor.moveToFirst()){

            cursor.moveToFirst();
            do{
                listJurusan.add(new JurusanModel(cursor.getString(0),cursor.getString(1),cursor.getString(2)));
            }while(cursor.moveToNext());
        }
        return listJurusan;
    }

    public List<String> getAllJurusans(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> listJurusans = new ArrayList<>();
        String sql = " SELECT * FROM " + TABLE_JURUSAN;
        Cursor cursor = db.rawQuery(sql,null);
        Log.d("haha", "kosong" + cursor.getCount());
        if(cursor != null && cursor.moveToFirst()){

            cursor.moveToFirst();
            listJurusans.add(" please select ");
            do{

                listJurusans.add(cursor.getString(2));
            }while(cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        return listJurusans;
    }



    public void deleteJurusan(String id_Jurusan){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_JURUSAN,"id = ?", new String[]{id_Jurusan});
    }

    public void updateJurusan(JurusanModel jur){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_KD_JUR, jur.getKode());
        values.put(COL_NAMA_JUR, jur.getNama());

        db.update(TABLE_JURUSAN, values,"id = ?" , new String[]{String.valueOf(jur.getId())});
        db.close();
    }

    public JurusanModel getDataJurusan(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_JURUSAN + " WHERE " + COL_ID_JUR + " = " + id ;
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        return new JurusanModel(cursor.getString(0),cursor.getString(1),cursor.getString(2));
    }


    //method mahasiswa
    public long insertMahasiswa(MahasiswaModel mhs) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_ID_MHS, mhs.getId_mhs());
        values.put(COL_NIM_MHS, mhs.getNim());
        values.put(COL_NAMA_MHS, mhs.getNama_mhs());
        values.put(COL_JURUSAN, mhs.getJrsan());
        values.put(COL_SMT_MHS, mhs.getSmt_mhs());


        // insert row
        long sequence = db.insert(TABLE_MAHASISWA, null, values);


        return sequence;
    }


    public ArrayList<MahasiswaModel> getAllMahasiswa(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<MahasiswaModel> listMahasiswa = new ArrayList<>();
        String sql = " SELECT * FROM " + TABLE_MAHASISWA;
        Cursor cursor = db.rawQuery(sql,null);
        Log.d("haha", "kosong" + cursor.getCount());
        if(cursor != null && cursor.moveToFirst()){

            cursor.moveToFirst();
            do{
                listMahasiswa.add(new MahasiswaModel(cursor.getString(0),cursor.getString(1),cursor.getString(2), cursor.getString(3),cursor.getString(4)));
            }while(cursor.moveToNext());
        }
        return listMahasiswa;
    }

    public void deleteMahasiswa(String id_mhs){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MAHASISWA,"id = ?", new String[]{id_mhs});
    }

    public void updateMahasiswa(MahasiswaModel mhs){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NIM_MHS, mhs.getNim());
        values.put(COL_NAMA_MHS, mhs.getNama_mhs());
        values.put(COL_JURUSAN, mhs.getJrsan());
        values.put(COL_SMT_MHS, mhs.getSmt_mhs());
        db.update(TABLE_MAHASISWA, values,"id = ?" , new String[]{String.valueOf(mhs.getId_mhs())});
        db.close();
    }

    public MahasiswaModel getDataMahasiswa(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_MAHASISWA + " WHERE " + COL_ID_MHS + " = " + id ;
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        return new MahasiswaModel(cursor.getString(1),cursor.getString(2),cursor.getString(3), cursor.getString(4));
    }

    public List<String> getAllMahasiswas(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> listMahasiswas = new ArrayList<>();
        String sql = " SELECT * FROM " + TABLE_MAHASISWA;
        Cursor cursor = db.rawQuery(sql,null);
        Log.d("haha", "kosong" + cursor.getCount());
        if(cursor != null && cursor.moveToFirst()){

            cursor.moveToFirst();
            listMahasiswas.add(" please select ");
            do{

                listMahasiswas.add(cursor.getString(2));
            }while(cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        return listMahasiswas;
    }


    //method fuzzy
    public long insertFuzzy(FuzzyModel fuz) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(COL_ID_FUZZY, fuz.getId_fuzzy());
        values.put(COL_NAMA_FUZZY, fuz.getNama_fuzzy());
        values.put(COL_UANG_FUZZY, fuz.getUang_fuzzy());
        values.put(COL_TANG_FUZZY, fuz.getTang_fuzzy());
        values.put(COL_IPK_FUZZY, fuz.getIpk_fuzzy());
        values.put(COL_SKOR_FUZZY, fuz.getSkor_fuzzy());
        values.put(COL_STATUS_FUZZY, fuz.getStatus_fuzzy());


        // insert row
        long sequence = db.insert(TABLE_FUZZY, null, values);


        return sequence;
    }


    public ArrayList<FuzzyModel> getAllFuzzy(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<FuzzyModel> listFuzzy = new ArrayList<>();
        String sql = " SELECT * FROM " + TABLE_FUZZY;
        Cursor cursor = db.rawQuery(sql,null);
        Log.d("haha", "kosong" + cursor.getCount());
        if(cursor != null && cursor.moveToFirst()){

            cursor.moveToFirst();
            do{
                listFuzzy.add(new FuzzyModel(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5)));
            }while(cursor.moveToNext());
        }
        return listFuzzy;
    }


    public void deleteFuzzy(String id_Fuzzy){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FUZZY,"id = ?", new String[]{id_Fuzzy});
    }

    public void updateFuzzy(FuzzyModel fuz){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_NAMA_FUZZY, fuz.getNama_fuzzy());
        values.put(COL_UANG_FUZZY, fuz.getUang_fuzzy());
        values.put(COL_TANG_FUZZY, fuz.getTang_fuzzy());
        //values.put(COL_SKOR_FUZZY, fuz.getSkor_fuzzy());
        values.put(COL_STATUS_FUZZY, fuz.getTang_fuzzy());

        db.update(TABLE_FUZZY, values,"id = ?" , new String[]{String.valueOf(fuz.getId_fuzzy())});
        db.close();
    }

    public FuzzyModel getDataFuzzy(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        //String sql1 = "select nim,nama,jurusan,semester,status from tb_mahasiswa,tb_jurusan,tb_fuzzy " +
                //"where tb_mahasiswa.nama = tb_fuzzy.nama and tb_mahasiswa.jurusan = tb_jurusan.jurusan ";
        String sql = "SELECT * FROM " + TABLE_FUZZY ;
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        return new FuzzyModel(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
    }
}
