package com.cobacoba.android.spkbeasiswa.Mahasiswa;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cobacoba.android.spkbeasiswa.DBHelper;
import com.cobacoba.android.spkbeasiswa.OnItemClickListener;
import com.cobacoba.android.spkbeasiswa.R;

import java.util.ArrayList;

public class MahasiswaRecyclerViewAdapter  extends RecyclerView.Adapter<MahasiswaRecyclerViewAdapter.ViewHolder> {
    private ArrayList<MahasiswaModel> listMahasiswa;
    private OnItemClickListener listener;

    Context context;

    public class ViewHolder extends  RecyclerView.ViewHolder {
        TextView tvnim,tvnama,tvjur,tvsmt;
        Button tool;
        String id;
        MahasiswaModel mahasiswa;
        //pengisian variabel
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = "";

            mahasiswa = new MahasiswaModel();
            tvnim = itemView.findViewById(R.id.tvNIM);
            tvnama = itemView.findViewById(R.id.tvNama);
            tvjur = itemView.findViewById(R.id.tvJurusan);
            tvsmt = itemView.findViewById(R.id.tvSemester);
            tool = itemView.findViewById(R.id.btncrud);
        }
    }

    public MahasiswaRecyclerViewAdapter(ArrayList<MahasiswaModel> listMahasiswa) {
        this.listMahasiswa = listMahasiswa;
    }

    public void deleteItem(int position){
        listMahasiswa.remove(position);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    //tempat item
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        context = parent.getContext();

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mahasiswa, parent, false);
        return new MahasiswaRecyclerViewAdapter.ViewHolder(v);
    }

    @Override
    //set data ke tampilan
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {

        final DBHelper dbhelper = new DBHelper(context,null);

        viewHolder.tvnim.setText(listMahasiswa.get(position).getNim());
        viewHolder.tvnama.setText(listMahasiswa.get(position).getNama_mhs());
        viewHolder.tvjur.setText(listMahasiswa.get(position).getJrsan());
        viewHolder.tvsmt.setText(listMahasiswa.get(position).getSmt_mhs());
        viewHolder.id = listMahasiswa.get(position).getId_mhs();

        final Button tool = viewHolder.tool;
        viewHolder.tool.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(context, tool);
                popup.inflate(R.menu.menu_crud);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.edit:
                                Intent intent = new Intent (context, UpdateMahasiswa.class);
                                intent.putExtra("id",viewHolder.id);
                                context.startActivity(intent);
                                //dbhelper.updateTeacher(viewHolder.teacher);
                                //Toast.makeText(context,"edit" +viewHolder.teacher.getName()  ,Toast.LENGTH_SHORT).show();

                                return true;
                            case R.id.delete:
                                dbhelper.deleteMahasiswa(viewHolder.id);
                                deleteItem(viewHolder.getAdapterPosition());
                                Toast.makeText(context,"delete",Toast.LENGTH_SHORT).show();
                                return true;
                        }
                        return false;
                    }
                });
                popup.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listMahasiswa != null ? listMahasiswa.size() : 0;
    }


    public void setOnItemClickLister(OnItemClickListener listener){
        this.listener = listener;
    }


}