package com.cobacoba.android.spkbeasiswa;

import android.view.View;

public interface OnItemClickListener {
    void onItemClick(View itemView, String name);
    void onItemLongCiick(View itemView, String name,int position);
}