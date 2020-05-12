package com.example.baitaplon_million;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DiemCaoAdapter extends RecyclerView.Adapter<DiemCaoAdapter.ViewHoder> {
    diemcao context;
    ArrayList<DiemCaoModel> diemCao;

    public DiemCaoAdapter(diemcao context, ArrayList<DiemCaoModel> diemCao) {
        this.context = context;
        this.diemCao = diemCao;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_diem_cao, null);
        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, final int position) {
        holder.tvTenNguoiChoi.setText(diemCao.get(position).getTen());
        holder.tvTien.setText(diemCao.get(position).getTien());
        holder.cardviewitem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder dialogXoa = new AlertDialog.Builder(view.getContext());
                dialogXoa.setMessage("Bạn có muốn xóa kỷ lục của " +"'"+diemCao.get(position).getTen() + "'" + " không ?");
                dialogXoa.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                dialogXoa.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        context.DeleteDiemCao( diemCao.get(position).getId());
                    }
                });
                dialogXoa.show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return diemCao.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder{
        CardView cardviewitem;
        TextView tvTenNguoiChoi, tvTien;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            this.cardviewitem = itemView.findViewById(R.id.cardviewitem);
            this.tvTenNguoiChoi = itemView.findViewById(R.id.tvTenNguoiChoi);
            this.tvTien = itemView.findViewById(R.id.tvTien);
        }
    }
}
