package com.example.bookshop.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookshop.Models.SanPham;
import com.example.bookshop.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class TimKiemAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    public static List<SanPham> sanPhamList;
    int id;


    public TimKiemAdapter(Context context, int layout, List<SanPham> sanPhamList) {
        this.context = context;
        this.layout = layout;
        this.sanPhamList = sanPhamList;
    }


    @Override
    public int getCount() {
        return sanPhamList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    static class ViewHolder{
        TextView txt_TenSP, txt_GiaSP,txtSL;
        ImageView img_HinhAnh;
    }


    @Override
    public View getView(int i, View view, ViewGroup parent) {

        TimKiemAdapter.ViewHolder holder;

        if (view == null){
            holder = new TimKiemAdapter.ViewHolder();
            LayoutInflater inflater;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txt_TenSP = (TextView) view.findViewById(R.id.TenSP_TK);
            holder.txt_GiaSP = (TextView) view.findViewById(R.id.GiaSP_TK);
            holder.txtSL = view.findViewById(R.id.SLSP_TK);
            holder.img_HinhAnh = (ImageView) view.findViewById(R.id.imgHinh_TK);
            view.setTag(holder);
        } else {
            holder = (TimKiemAdapter.ViewHolder) view.getTag();
        }

        SanPham sanPham = sanPhamList.get(i);
        String gia = String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(sanPham.getGiaSP())) + " VNĐ";
        holder.txt_TenSP.setText(sanPham.getTenSP());
        holder.txt_GiaSP.setText(gia);
        holder.txtSL.setText("Sản Phẩm : " + String.valueOf(sanPham.getSl_SP()));
        id = sanPham.getMaSP();

        // chuyen byte[] -> ve bitmap
        byte[] hinhAnh = sanPham.getImageSP();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0, hinhAnh.length);
        holder.img_HinhAnh.setImageBitmap(bitmap);

        return view;
    }
}
