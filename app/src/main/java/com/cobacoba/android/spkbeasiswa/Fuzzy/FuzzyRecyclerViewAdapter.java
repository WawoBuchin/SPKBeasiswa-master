package com.cobacoba.android.spkbeasiswa.Fuzzy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cobacoba.android.spkbeasiswa.DBHelper;
import com.cobacoba.android.spkbeasiswa.OnItemClickListener;
import com.cobacoba.android.spkbeasiswa.R;

import java.util.ArrayList;

public class FuzzyRecyclerViewAdapter extends RecyclerView.Adapter<FuzzyRecyclerViewAdapter.ViewHolder> {
        private ArrayList<FuzzyModel> listFuzzy;
        private OnItemClickListener listener;

        Context context;

        public class ViewHolder extends  RecyclerView.ViewHolder {
            TextView tvNo,tvPeng,tvtang,tvipk,tvNama,tvskor,tvStatus;
            Button tool;
            String id;
            FuzzyModel fuzzy;
            //pengisian variabel
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                id = "";

                fuzzy = new FuzzyModel();
                tvNama = itemView.findViewById(R.id.tvNama);
                tvStatus = itemView.findViewById(R.id.tvStatus);
                tvskor = itemView.findViewById(R.id.tvSkor);
                tvtang = itemView.findViewById(R.id.tvTanggungan);
                tvipk = itemView.findViewById(R.id.tvIPK);
                tvNo = itemView.findViewById(R.id.tvNo);
                tvPeng = itemView.findViewById(R.id.tvPenghasilan);
                tool = itemView.findViewById(R.id.btncrud);
            }
        }

        public FuzzyRecyclerViewAdapter(ArrayList<FuzzyModel> listFuzzy) {
            this.listFuzzy = listFuzzy;
        }

        public void deleteItem(int position){
            listFuzzy.remove(position);
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        //tempat item
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            context = parent.getContext();

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_laporan, parent, false);
            return new ViewHolder(v);
        }

        @Override
        //set data ke tampilan
        public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {

            final DBHelper dbhelper = new DBHelper(context,null);

            viewHolder.tvNo.setText(listFuzzy.get(position).getId_fuzzy());
            viewHolder.tvNama.setText(listFuzzy.get(position).getNama_fuzzy());
            viewHolder.tvPeng.setText(listFuzzy.get(position).getUang_fuzzy());
            viewHolder.tvtang.setText(listFuzzy.get(position).getTang_fuzzy());
            viewHolder.tvipk.setText(listFuzzy.get(position).getIpk_fuzzy());
            viewHolder.tvskor.setText(listFuzzy.get(position).getSkor_fuzzy());
            viewHolder.tvStatus.setText(listFuzzy.get(position).getStatus_fuzzy());
            viewHolder.id = listFuzzy.get(position).getId_fuzzy();

           /* final Button tool = viewHolder.tool;
            viewHolder.tool.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    PopupMenu popup = new PopupMenu(context, tool);
                    popup.inflate(R.menu.menu_crud);
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.edit:
                                    Intent intent = new Intent (context, UpdateJurusan.class);
                                    intent.putExtra("id",viewHolder.id);
                                    context.startActivity(intent);
                                    //dbhelper.updateTeacher(viewHolder.teacher);
                                    //Toast.makeText(context,"edit" +viewHolder.teacher.getName()  ,Toast.LENGTH_SHORT).show();

                                    return true;
                                case R.id.delete:
                                    dbhelper.deleteJurusan(viewHolder.id);
                                    deleteItem(viewHolder.getAdapterPosition());
                                    Toast.makeText(context,"delete",Toast.LENGTH_SHORT).show();
                                    return true;
                            }
                            return false;
                        }
                    });
                    popup.show();
                }
            });*/

        }

        @Override
        public int getItemCount() {
            return listFuzzy != null ? listFuzzy.size() : 0;
        }


        public void setOnItemClickLister(OnItemClickListener listener){
            this.listener = listener;
        }


    }
