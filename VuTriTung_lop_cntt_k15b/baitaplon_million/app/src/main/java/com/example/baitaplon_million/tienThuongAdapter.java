package com.example.baitaplon_million;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class tienThuongAdapter extends RecyclerView.Adapter<tienThuongAdapter.ViewHoder>{
    int viTriCauHoi = 1;
    Context context;
    ArrayList<tienThuongModel> arr;

    public tienThuongAdapter(Context context, ArrayList<tienThuongModel> arr) {
        this.context = context;
        this.arr = arr;
    }
    public void setViTriCauHoi(int viTriCauHoi) {
        this.viTriCauHoi = viTriCauHoi;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tien_thuong, null);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        if(getItemCount()>0){
            holder.txt_TienThuong.setText(arr.get(position).getTien());
            int pos = 15 - position;
            if(pos%5 == 0){
                holder.txt_TienThuong.setTextColor(Color.parseColor("#FFFFFF"));
                holder.txt_TienThuong.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.bg_btn));

            }else {
                holder.txt_TienThuong.setTextColor(Color.parseColor("#FF9800"));
                holder.txt_TienThuong.setBackgroundColor(Color.parseColor("#6A127A"));
            }

            String khoangtrang;
            if (pos/10>0){
                khoangtrang= "       ";
            }else {
                khoangtrang= "        ";
            }
            String txtHienThi = pos+"."+khoangtrang+arr.get(position).getTien() + " VND";
            holder.txt_TienThuong.setText(txtHienThi);

            if (pos == viTriCauHoi){
                holder.txt_TienThuong.setBackgroundColor(Color.parseColor("#FF9800"));
                holder.txt_TienThuong.setTextColor(Color.parseColor("#FFFFFF"));
            }
        }
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }


    public class ViewHoder extends RecyclerView.ViewHolder{
        TextView txt_TienThuong;

        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            this.txt_TienThuong = itemView.findViewById(R.id.txt_TienThuong);
        }
    }
}
