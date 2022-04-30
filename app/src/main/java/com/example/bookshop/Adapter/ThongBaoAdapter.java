package com.example.bookshop.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.bookshop.User_Activity.LoginActivity;
import com.example.bookshop.User_Fragment.TrangChuFragment;
import com.example.bookshop.Models.ThongBao;
import com.example.bookshop.R;

import java.util.List;

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
        TextView tieude_thongbao, noidung_thongbao,gio_bantin;
        ImageView chuaxem;
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
            holder.chuaxem = view.findViewById(R.id.chuaxem);
//            holder.thoigian_thongbao = (TextView) view.findViewById(R.id.thoigian_thongbao);
            holder.gio_bantin = (TextView) view.findViewById(R.id.gio_bantin);
            view.setTag(holder);
        } else {
            holder = (ThongBaoAdapter.ViewHolder) view.getTag();
        }

        ThongBao thongBao = thongBaoList.get(i);
        if(TrangChuFragment.database.tieudeSOSANH(LoginActivity.taiKhoan.getMATK(),thongBao.getTIEUDE()))
        {
            holder.chuaxem.setImageResource(R.drawable.chuaxem);
        }
        else
        {
            holder.chuaxem.setBackgroundResource(R.color.white);
        }
        holder.tieude_thongbao.setText(thongBao.getTIEUDE());
        holder.noidung_thongbao.setText(thongBao.getNOIDUNG());
//        holder.thoigian_thongbao.setText(thongBao.getDATE());
        holder.gio_bantin.setText(thongBao.getDATE());

        Log.e("TAG", " " + thongBao.getTIEUDE() + " = " + TrangChuFragment.database.tieudeSOSANH(LoginActivity.taiKhoan.getMATK(),thongBao.getTIEUDE()) );
        return view;
    }

}
