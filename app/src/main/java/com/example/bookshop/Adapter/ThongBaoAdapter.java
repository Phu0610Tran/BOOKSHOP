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

import com.example.bookshop.Data.CreateDatabase;
import com.example.bookshop.Models.SanPham;
import com.example.bookshop.Models.ThongBao;
import com.example.bookshop.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ThongBaoAdapter extends BaseAdapter {
    private Fragment context;
    private int layout;
    public static List<ThongBao> thongBaoList;
    int id;

    public ThongBaoAdapter(Fragment context, int layout, List<ThongBao> thongBaoList) {
        this.context = context;
        this.layout = layout;
        this.thongBaoList = thongBaoList;
    }


    @Override
    public int getCount() {
        return thongBaoList.size();
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
        TextView tieude_thongbao, noidung_thongbao, thoigian_thongbao;
    }


    @Override
    public View getView(int i, View view, ViewGroup parent) {

        ThongBaoAdapter.ViewHolder holder;

        if (view == null){
            holder = new ThongBaoAdapter.ViewHolder();
            LayoutInflater inflater;
            inflater = (LayoutInflater) context.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.tieude_thongbao = (TextView) view.findViewById(R.id.tieude_thongbao);
            holder.noidung_thongbao = (TextView) view.findViewById(R.id.noidung_thongbao);
            holder.thoigian_thongbao = (TextView) view.findViewById(R.id.thoigian_thongbao);
            view.setTag(holder);
        } else {
            holder = (ThongBaoAdapter.ViewHolder) view.getTag();
        }

        ThongBao thongBao = thongBaoList.get(i);
        holder.tieude_thongbao.setText(thongBao.getTIEUDE());
        holder.noidung_thongbao.setText(thongBao.getNOIDUNG());
        holder.thoigian_thongbao.setText(thongBao.getDATE());

        return view;
    }
}
