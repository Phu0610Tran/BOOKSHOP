package com.example.bookshop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bookshop.Models.HoaDon;
import com.example.bookshop.R;
import com.kofigyan.stateprogressbar.StateProgressBar;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class HoaDonAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    public static List<HoaDon> ListHoaDon;
    int id,idcthd;



    public HoaDonAdapter(Context context, int layout, List<HoaDon> ListHoaDon) {
        this.context = context;
        this.layout = layout;
        this.ListHoaDon = ListHoaDon;
    }


    @Override
    public int getCount() {
        return ListHoaDon.size();
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
        TextView txtTongTien,txtdiachi,txtghichu,textviewtt_lichsu,txtngaydat_lichsu;
        ListView listView;
    }
    @Override
    public View getView(int i, View view, ViewGroup parent) {

        HoaDonAdapter.ViewHolder holder;

        if (view == null){
            holder = new HoaDonAdapter.ViewHolder();
            LayoutInflater inflater;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txtTongTien = (TextView) view.findViewById(R.id.textviewTongTien_lichsu);
            holder.txtdiachi = (TextView) view.findViewById(R.id.textviewdc_lichsu);
            holder.txtghichu = (TextView) view.findViewById(R.id.textviewgc_lichsu);
            holder.textviewtt_lichsu = (TextView) view.findViewById(R.id.textviewtt_lichsu);
            holder.listView = (ListView) view.findViewById(R.id.listview_danhsachhoadon_lichsu);
            holder.txtngaydat_lichsu = view.findViewById(R.id.txtngaydat_lichsu);
            view.setTag(holder);
        } else {
            holder = (HoaDonAdapter.ViewHolder) view.getTag();
        }

        HoaDon hoaDon = ListHoaDon.get(i);


        holder.txtTongTien.setText(String.valueOf(NumberFormat.getNumberInstance(Locale.US).format(hoaDon.getTONGTIEN())) + " VNĐ");
        holder.txtdiachi.setText(hoaDon.getDIACHI());
        holder.txtghichu.setText(hoaDon.getGHICHU());
        String trangthai = null;
        switch (hoaDon.getTINHTRANG()) {
            case 1:
                trangthai = " Chờ duyệt ";
                break;
            case 2:
                trangthai = " Chờ lấy hàng ";
                break;
            case 3:
                trangthai = " Đang giao ";
                break;
            case 4:
                trangthai = " Đã giao ";
                break;

            case 5:
                trangthai = " Chờ nhận hàng ";
                break;
            case 6:
                trangthai = " Đã nhận hàng ";
                break;
            case 7:
                trangthai = " Đã huỷ ";
                break;
            default:
                break;

        }
        holder.textviewtt_lichsu.setText(trangthai);
        id = hoaDon.getIDHOADON();
        idcthd = hoaDon.getIDCTHOADON();
        holder.txtngaydat_lichsu.setText(hoaDon.getNgayDat());




        return view;
    }

}
