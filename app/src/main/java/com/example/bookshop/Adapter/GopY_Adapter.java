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

import androidx.fragment.app.Fragment;

import com.example.bookshop.Models.GopY;
import com.example.bookshop.R;

import java.util.List;

public class GopY_Adapter extends BaseAdapter {
    private Fragment context;
    private int layout;
    public static List<GopY> gopYList;

    public GopY_Adapter(Fragment context, int layout, List<GopY> gopYList) {
        this.context = context;
        this.layout = layout;
        this.gopYList = gopYList;
    }


    @Override
    public int getCount() {
        return gopYList.size();
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
        TextView ten_nguoidung, sotiendienthoai,noidung;
        ImageView imgcheck;

    }


    @Override
    public View getView(int i, View view, ViewGroup parent) {

        GopY_Adapter.ViewHolder holder;

        if (view == null){
            holder = new GopY_Adapter.ViewHolder();
            LayoutInflater inflater;
            inflater = (LayoutInflater) context.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.ten_nguoidung = (TextView) view.findViewById(R.id.ten_nguoidung);
            holder.sotiendienthoai = (TextView) view.findViewById(R.id.sotiendienthoai);
            holder.noidung = (TextView) view.findViewById(R.id.noidung);
            view.setTag(holder);
        } else {
            holder = (GopY_Adapter.ViewHolder) view.getTag();
        }

        GopY gopY = gopYList.get(i);
        holder.ten_nguoidung.setText(gopY.getTENTAIKHOAN());
        holder.sotiendienthoai.setText("0" + gopY.getSDT());
        holder.noidung.setText(gopY.getNOIDUNG());

        return view;
    }
}
