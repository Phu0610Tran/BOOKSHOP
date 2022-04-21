package com.example.bookshop.Adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bookshop.Models.TaiKhoan;
import com.example.bookshop.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TaiKhoanAdapter extends BaseAdapter {
    SQLiteDatabase database;

    private Context context;
    private int layout;
    public static List<TaiKhoan> taiKhoanList;
    int id;


    public TaiKhoanAdapter(Context context, int layout, List<TaiKhoan> taiKhoanList) {
        this.context = context;
        this.layout = layout;
        this.taiKhoanList = taiKhoanList;
    }

    @Override
    public int getCount() {
        return taiKhoanList.size();
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
        TextView ten_banbe,diem_banbe;
        CircleImageView img_hinhbanbe;
    }


    @Override
    public View getView(int i, View view, ViewGroup parent) {

        ViewHolder holder;

        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            holder.diem_banbe = (TextView) view.findViewById(R.id.diem_banbe);
            holder.ten_banbe = (TextView) view.findViewById(R.id.ten_banbe);
            holder.img_hinhbanbe = (CircleImageView) view.findViewById(R.id.img_hinhbanbe);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        TaiKhoan taiKhoan = taiKhoanList.get(i);
        holder.diem_banbe.setText(String.valueOf(taiKhoan.getDIEM()) + " Điểm ");
        holder.ten_banbe.setText(taiKhoan.getTENTK());
        id = taiKhoan.getMATK();

        // chuyen byte[] -> ve bitmap
        byte[] hinhAnh = taiKhoan.getHINHANH();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0, hinhAnh.length);
        holder.img_hinhbanbe.setImageBitmap(bitmap);

        return view;
    }
}
