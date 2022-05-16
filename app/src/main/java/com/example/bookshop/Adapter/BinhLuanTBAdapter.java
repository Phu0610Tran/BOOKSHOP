package com.example.bookshop.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshop.User_Fragment.TrangChuFragment;
import com.example.bookshop.Models.BinhLuan;
import com.example.bookshop.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class BinhLuanTBAdapter extends RecyclerView.Adapter<BinhLuanTBAdapter.viewholder> {

    public static ArrayList<BinhLuan> listBL;

    public BinhLuanTBAdapter() {

    }
    public BinhLuanTBAdapter(ArrayList<BinhLuan> listBL) {
        this.listBL = listBL;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.binhluantb,parent,false);

        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        BinhLuan binhLuan = listBL.get(holder.getAdapterPosition());

        Bitmap bitmap = BitmapFactory.decodeByteArray(binhLuan.getHinhBL(),0,binhLuan.getHinhBL().length);
        holder.img_Hinh_tb.setImageBitmap(bitmap);
        holder.txt_NoiDung_tb.setText(binhLuan.getNoidungBL());
        holder.txtV_ThoiGian_tb.setText(String.valueOf(binhLuan.getThoiGianBL()));
        holder.txtV_Ten_tb.setText(TrangChuFragment.database.Layten( binhLuan.getTaiKhoanBL()));
    }

    @Override
    public int getItemCount() {
        if (listBL!=null){
            return listBL.size();
        }
        return 0;
    }

    public class viewholder extends RecyclerView.ViewHolder{
        CircleImageView img_Hinh_tb;
        TextView txtV_Ten_tb,txt_NoiDung_tb,txtV_ThoiGian_tb;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            txtV_Ten_tb = itemView.findViewById(R.id.txtV_Ten_tb);
            img_Hinh_tb = itemView.findViewById(R.id.img_Hinh_tb);
            txt_NoiDung_tb = itemView.findViewById(R.id.txt_NoiDung_tb);
            txtV_ThoiGian_tb = itemView.findViewById(R.id.txtV_ThoiGian_tb);
        }
    }
}
