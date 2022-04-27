package com.example.bookshop.Adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshop.ActivityUser.Products_information_activity;
import com.example.bookshop.Models.SanPham;
import com.example.bookshop.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class SanPhamRe_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<SanPham> sanPhamList;
    private int sp_moi = 10;
    private int sp_cu = 11;

    public SanPhamRe_Adapter(List<SanPham> sanPhamList) {
        this.sanPhamList = sanPhamList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == sp_moi) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.productnew_layout, parent, false);
            return new SanPhamMoiRe_ViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_layout, parent, false);
            return new SanPhamRe_ViewHolder(view);
        }
    }


    //    @NonNull
//    @Override
//    public SanPhamRe_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.productnew_layout, parent, false);
//        return new SanPhamRe_ViewHolder(view);
//    }

    @Override
    public int getItemViewType(int position) {
        if (sanPhamList.get(position).isSpmoi()) {
            return sp_moi;
        }
        return sp_cu;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == sp_moi) {
            SanPham sanPham = sanPhamList.get(position);
            SanPhamMoiRe_ViewHolder sanPhamMoiReViewHolder = (SanPhamMoiRe_ViewHolder) holder;
            String gia = String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(sanPham.getGiaSP())) + " VNĐ";
            sanPhamMoiReViewHolder.txt_TenSP.setText(sanPham.getTenSP());
            sanPhamMoiReViewHolder.txt_GiaSP.setText(gia);

            // chuyen byte[] -> ve bitmap
            byte[] hinhAnh = sanPham.getImageSP();
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);
            sanPhamMoiReViewHolder.img_HinhAnh.setImageBitmap(bitmap);
            sanPhamMoiReViewHolder.itemView.setOnClickListener(view -> {
                Intent intent = new Intent(sanPhamMoiReViewHolder.itemView.getContext(), Products_information_activity.class);
                intent.putExtra("id", sanPham.getMaSP());
                sanPhamMoiReViewHolder.itemView.getContext().startActivity(intent);
            });
        } else {
            SanPham sanPham = sanPhamList.get(position);
            SanPhamRe_ViewHolder sanPhamReViewHolder = (SanPhamRe_ViewHolder) holder;
            String gia = String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(sanPham.getGiaSP())) + " VNĐ";
            sanPhamReViewHolder.txt_TenSP.setText(sanPham.getTenSP());
            sanPhamReViewHolder.txt_GiaSP.setText(gia);

            // chuyen byte[] -> ve bitmap
            byte[] hinhAnh = sanPham.getImageSP();
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);
            sanPhamReViewHolder.img_HinhAnh.setImageBitmap(bitmap);
            sanPhamReViewHolder.itemView.setOnClickListener(view -> {
                Intent intent = new Intent(sanPhamReViewHolder.itemView.getContext(), Products_information_activity.class);
                intent.putExtra("id", sanPham.getMaSP());
                sanPhamReViewHolder.itemView.getContext().startActivity(intent);
            });
        }
    }

//    @Override
//    public void onBindViewHolder(@NonNull SanPhamRe_ViewHolder holder, int position) {
//        SanPham sanPham = sanPhamList.get(position);
//        String gia = String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(sanPham.getGiaSP())) + " VNĐ";
//        holder.txt_TenSP.setText(sanPham.getTenSP());
//        holder.txt_GiaSP.setText(gia);
//
//        // chuyen byte[] -> ve bitmap
//        byte[] hinhAnh = sanPham.getImageSP();
//        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0, hinhAnh.length);
//        holder.img_HinhAnh.setImageBitmap(bitmap);
//        holder.itemView.setOnClickListener(view -> {
//            Intent intent = new Intent(holder.itemView.getContext(), Products_information_activity.class);
//            intent.putExtra("id", sanPham.getMaSP());
//            holder.itemView.getContext().startActivity(intent);
//        });
//    }


    @Override
    public int getItemCount() {
        if (sanPhamList != null) {
            return sanPhamList.size();
        }
        return 0;
    }

    // Sản phẩm cũ
    public class SanPhamRe_ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_TenSP, txt_GiaSP;
        ImageView img_HinhAnh;

        public SanPhamRe_ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_TenSP = (TextView) itemView.findViewById(R.id.product_name);
            txt_GiaSP = (TextView) itemView.findViewById(R.id.product_price);
            img_HinhAnh = (ImageView) itemView.findViewById(R.id.product_image);
        }
    }


    // Sản phẩm mới
    public class SanPhamMoiRe_ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_TenSP, txt_GiaSP;
        ImageView img_HinhAnh;

        public SanPhamMoiRe_ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_TenSP = (TextView) itemView.findViewById(R.id.product_name);
            txt_GiaSP = (TextView) itemView.findViewById(R.id.product_price);
            img_HinhAnh = (ImageView) itemView.findViewById(R.id.product_image);
        }
    }
}
